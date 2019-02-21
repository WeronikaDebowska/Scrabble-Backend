package com.codecool.scrabble.Service;

import com.codecool.scrabble.Model.Board;
import com.codecool.scrabble.Model.Cell;
import com.codecool.scrabble.Model.CellParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class MoveValidatorImpl {

    private LinkedList<Cell> newCells = new LinkedList<Cell>();
    private LinkedList<String> newWords = new LinkedList<String>();

    private Board oldBoard;
    private Board newBoard = new Board();
    private CellParams cellParams;

    @Autowired
    public MoveValidatorImpl(Board board, CellParams cellParams) {
        this.oldBoard = board;
        this.cellParams = cellParams;

    }

    public LinkedList<String> checkMoveValidity() {

        newCells.clear();
        compareBoards();
        if (noNewLettersOnBoard()) {
            return newWords;
        }
        findAllNewWords();
        return newWords;
    }

    private boolean noNewLettersOnBoard() {
        return newCells.size() == 0;
    }

    private void compareBoards() {
        for (Cell cell : newBoard.getBoard()) {
            if (hasChanged(cell)) {
                newCells.add(cell);
            }
        }
    }

    private boolean hasChanged(Cell newCell) {
        int cellIndex = newCell.getCellIndex();
        Character newLetter = newCell.getLetter();
        Character oldLetter = oldBoard.getCellByIndex(cellIndex).getLetter();
        return !(newLetter.equals(oldLetter));
    }

    public void findAllNewWords() {

        int firstNewLetterIndex;
        int lastNewLetterIndex;

        if (onlyOneLetterAdded()) {

            Cell singleLetterCell = newCells.get(0);
            int singleLetterIndex = newCells.getFirst().getCellIndex();

            if (anyLetterNextToHorizontally(singleLetterCell)) {
                findNewWord(singleLetterIndex, singleLetterIndex, WordOrientation.HORIZONTAL);
            }

            if (annyLetterNextToVertically(singleLetterCell)) {
                findNewWord(singleLetterIndex, singleLetterIndex, WordOrientation.VERTICAL);
            }

        } else {
            WordOrientation orientation = checkNewWordOrientation();
            if (!orientation.equals(WordOrientation.NONE)) {

                firstNewLetterIndex = newCells.getFirst().getCellIndex();
                lastNewLetterIndex = newCells.getLast().getCellIndex();

                findNewWord(firstNewLetterIndex, lastNewLetterIndex, orientation);
                findAdditionalWord(orientation);
            }
        }
    }

    private WordOrientation checkNewWordOrientation() {

        int firstNewLetterIndex = newCells.getFirst().getCellIndex();
        int lastNewLetterIndex = newCells.getLast().getCellIndex();

        if (allCellsInOneColumn(firstNewLetterIndex, lastNewLetterIndex)) {
            return WordOrientation.VERTICAL;
        }
        if (allCellsInOneRow(firstNewLetterIndex, lastNewLetterIndex)) {
            return WordOrientation.HORIZONTAL;
        }
        return WordOrientation.NONE;
    }

    private boolean allCellsInOneRow(int firstNewLetterIndex, int lastNewLetterIndex) {
        return lastNewLetterIndex - firstNewLetterIndex < 14;
    }

    private boolean allCellsInOneColumn(int firstNewLetterIndex, int lastNewLetterIndex) {
        return (lastNewLetterIndex - firstNewLetterIndex) % 100 == 0;
    }

    public void updateState(boolean valid) {
        System.out.println();
        trialBoardPrint();

        if (valid) {
            oldBoard = newBoard;
        }
        newCells.clear();
        newWords.clear();
    }

    private void findNewWord(int firstNewLetterIndex, int lastNewLetterIndex, WordOrientation orientation) {
        firstNewLetterIndex = findFirstLetterOfNewWordIndex(firstNewLetterIndex, orientation);
        lastNewLetterIndex = findLastLetterOfNewWordIndex(lastNewLetterIndex, orientation);
        findLettersInsideWord(firstNewLetterIndex, lastNewLetterIndex, orientation);
    }

    private boolean annyLetterNextToVertically(Cell singleLetterCell) {
        return (!singleLetterCell.inFirstRow() && oldBoard.getCellAbove(singleLetterCell).containsLetter())
                || (!singleLetterCell.inLastRow() && oldBoard.getCellBelow(singleLetterCell).containsLetter());
    }

    private int findFirstLetterOfNewWordIndex(int firstNewLetterIndex, WordOrientation orientation) {

        Cell beginningCell = newBoard.getCellByIndex(firstNewLetterIndex);

        switch (orientation) {
            case HORIZONTAL:
                if (!beginningCell.inFirstColumn()) {
                    Cell previousCell = newBoard.getCellLeft(beginningCell);
                    while (!beginningCell.inFirstColumn() && previousCell.containsLetter()) {
                        if (previousCell.containsLetter()) {
                            beginningCell = previousCell;
                            previousCell = newBoard.getCellLeft(beginningCell);
                        } else {
                            break;
                        }
                    }
                }
                return beginningCell.getCellIndex();

            case VERTICAL:
                if (!beginningCell.inFirstRow()) {
                    Cell cellAbove = newBoard.getCellAbove(beginningCell);
                    while (!beginningCell.inFirstRow() && cellAbove.containsLetter()) {
                        if (cellAbove.containsLetter()) {
                            beginningCell = cellAbove;
                            cellAbove = newBoard.getCellAbove(beginningCell);
                        } else {
                            break;
                        }
                    }
                }
                return beginningCell.getCellIndex();
        }
        return firstNewLetterIndex;
    }

    private boolean anyLetterNextToHorizontally(Cell singleLetterCell) {
        return (!singleLetterCell.inFirstColumn() && oldBoard.getCellLeft(singleLetterCell).containsLetter())
                || (!singleLetterCell.inLastColumn() && oldBoard.getCellRight(singleLetterCell).containsLetter());
    }

    private boolean onlyOneLetterAdded() {
        return newCells.size() == 1;
    }

    private int findLastLetterOfNewWordIndex(int lastNewLetterIndex, WordOrientation orientation) {

        Cell endingCell = newBoard.getCellByIndex(lastNewLetterIndex);
        switch (orientation) {
            case HORIZONTAL:
                if (!endingCell.inLastColumn()) {
                    Cell nextCell = newBoard.getCellRight(endingCell);
                    while (!endingCell.inLastColumn() && nextCell.containsLetter()) {
                        if (nextCell.containsLetter()) {
                            endingCell = nextCell;
                            nextCell = newBoard.getCellRight(endingCell);
                        } else {
                            break;
                        }
                    }
                }
                return endingCell.getCellIndex();
            case VERTICAL:
                if (!endingCell.inLastRow()) {
                    Cell nextCell = newBoard.getCellBelow(endingCell);
                    while (!endingCell.inLastRow() && nextCell.containsLetter()) {
                        if (nextCell.containsLetter()) {
                            endingCell = nextCell;
                            nextCell = newBoard.getCellBelow(endingCell);
                        } else {
                            break;
                        }
                    }
                }
                return endingCell.getCellIndex();
        }
        return lastNewLetterIndex;
    }


    public void findAdditionalWord(WordOrientation orientation) {
        LinkedList<Cell> cellsToCheck = new LinkedList<>();


        switch (orientation) {

            case HORIZONTAL:
                Cell cellAbove;
                Cell cellBellow;

                for (Cell cell : newCells) {

                    int currentIndex = cell.getCellIndex();
                    if (!cell.inFirstRow()) {
                        cellAbove = oldBoard.getCellByIndex(currentIndex - 100);
                        if (cellAbove.containsLetter()) {
                            cellsToCheck.add(cellAbove);
                            break;
                        }
                    }
                    if (!cell.inLastRow()) {
                        cellBellow = oldBoard.getCellByIndex(currentIndex + 100);
                        if (cellBellow.containsLetter()) {
                            cellsToCheck.add(cellBellow);
                        }
                    }
                }
                for (Cell cell : cellsToCheck) {
                    int cellIndex = cell.getCellIndex();
                    findNewWord(cellIndex, cellIndex, WordOrientation.VERTICAL);
                }
                break;
            case VERTICAL:
                Cell cellRight;
                Cell cellLeft;

                for (Cell cell : newCells) {

                    int currentIndex = cell.getCellIndex();
                    if (!cell.inFirstColumn()) {
                        cellLeft = oldBoard.getCellByIndex(currentIndex - 1);
                        if (cellLeft.containsLetter()) {
                            cellsToCheck.add(cellLeft);
                            break;
                        }
                    }
                    if (!cell.inLastColumn()) {
                        cellRight = oldBoard.getCellByIndex(currentIndex + 1);
                        if (cellRight.containsLetter()) {
                            cellsToCheck.add(cellRight);
                        }
                    }
                }
                for (Cell cell : cellsToCheck) {
                    int cellIndex = cell.getCellIndex();
                    findNewWord(cellIndex, cellIndex, WordOrientation.HORIZONTAL);
                }
        }
    }

    private void findLettersInsideWord(int firstNewLetterIndex, int lastNewLetterIndex, WordOrientation orientation) {
        StringBuilder newWord = new StringBuilder();
        switch (orientation) {
            case HORIZONTAL:
                for (int cellIndex = firstNewLetterIndex; cellIndex <= lastNewLetterIndex; cellIndex++) {
                    newWord.append(newBoard.getCellByIndex(cellIndex).getLetter());
                }
                newWords.add(newWord.toString().toLowerCase());
                break;
            case VERTICAL:
                for (int cellIndex = firstNewLetterIndex; cellIndex <= lastNewLetterIndex; cellIndex += 100) {

                    newWord.append(newBoard.getCellByIndex(cellIndex).getLetter());
                }
                newWords.add(newWord.toString().toLowerCase());
                break;
        }
    }

    public void clearBoard() {
        newBoard.clearAll();
        oldBoard.clearAll();
    }


    public Board setActualBoard(boolean isMoveValid) {

        if (isMoveValid) {
            return newBoard;
        }
        newBoard = oldBoard;
        return oldBoard;
    }

    public void setNewBoard(Board board) {

        Board newBoard = new Board();
        for (Cell cell : board.getBoard()) {
            int cellIndex = cell.getCellIndex();
            char cellLetter = Character.toLowerCase(cell.getLetter());
            newBoard.getCellByIndex(cellIndex).setLetter(cellLetter);
        }
        this.newBoard = newBoard;
    }

    public LinkedList<Cell> getNewCells() {
        return newCells;
    }

    public void setNewCells(LinkedList<Cell> newCells) {
        this.newCells = newCells;
    }

    public Board getOldBoard() {
        return oldBoard;
    }

    public void setOldBoard(Board oldBoard) {
        this.oldBoard = oldBoard;
    }

    public Board getNewBoard() {
        return newBoard;
    }

    private enum WordOrientation {
        VERTICAL,
        HORIZONTAL,
        NONE;       // new letters are neither in one row nor in one column
    }


    public void trialBoardPrint() {

        System.out.println("  -------- oldBoard --------       -------- newBoard --------");

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                System.out.print(" " + oldBoard.getBoard()[i * 15 + j].getLetter());
            }
            System.out.print("   ");
            for (int j = 0; j < 15; j++) {
                System.out.print(" " + newBoard.getBoard()[i * 15 + j].getLetter());
            }
            System.out.println();
        }
    }

}

