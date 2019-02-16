package com.codecool.scrabble.Service;

import com.codecool.scrabble.Model.Board;
import com.codecool.scrabble.Model.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class MoveValidatorImpl implements MoveValidatorService {


    private LinkedList<Cell> newCells = new LinkedList<Cell>();

    private LinkedList<String> newWords = new LinkedList<String>();

    private Board oldBoard;
    private Board newBoard;


    @Autowired
    public MoveValidatorImpl(Board board) {
        this.oldBoard = board;
    }

    @Override
    public LinkedList<String> checkMoveValidity() {
        newCells.clear();

        System.out.println();
        trialBoardPrint();
        compareBoards();
        findNewWord();

        for (Cell cell : newCells) {
            System.out.print(cell.getCellIndex() + " " + cell.getLetter());
        }
        System.out.println();

        return newWords;
    }

    private void compareBoards() {

        for (Cell newCell : newBoard.getBoard()) {
            if (!newCell.getLetter().equals(oldBoard.getCellByIndex(newCell.getCellIndex()).getLetter())) {
                newCells.add(newCell);
            }
        }
    }

    private WordOrientation checkNewWordOrientation() {

        if ((newCells.getLast().getCellIndex() - newCells.getFirst().getCellIndex()) % 100 == 0) {
            return WordOrientation.VERTICAL;
        }
        if (newCells.getLast().getCellIndex() - newCells.getFirst().getCellIndex() < 14) {
//                Math.floor(newCells.getLast().getCellIndex() / 100) == Math.floor(newCells.getFirst().getCellIndex() / 100)) {
            return WordOrientation.HORIZONTAL;
        }
        return null;
    }

    public void updateState(boolean valid) {
        if (valid) {
            oldBoard = newBoard;
        }
        newCells.clear();
        newWords.clear();
    }

    public void findNewWord() {

        int firstNewLetterIndex = newCells.getFirst().getCellIndex();
        int lastNewLetterIndex = newCells.getLast().getCellIndex();

        if (onlyOneLetterAdded()) {

            Cell singleLetterCell = newCells.get(0);

            if (anyLetterNextToHorizontally(singleLetterCell)) {

                firstNewLetterIndex = findFirstLetterOfNewWordIndex(firstNewLetterIndex, WordOrientation.HORIZONTAL);
                lastNewLetterIndex = findLastLetterOfNewWordIndex(lastNewLetterIndex, WordOrientation.HORIZONTAL);
                findLettersInsideWord(firstNewLetterIndex, lastNewLetterIndex, WordOrientation.HORIZONTAL);
            }
            if (annyLetterNextToVertically(singleLetterCell)) {

                firstNewLetterIndex = findFirstLetterOfNewWordIndex(firstNewLetterIndex, WordOrientation.VERTICAL);
                lastNewLetterIndex = findLastLetterOfNewWordIndex(lastNewLetterIndex, WordOrientation.VERTICAL);
                findLettersInsideWord(firstNewLetterIndex, lastNewLetterIndex, WordOrientation.VERTICAL);

            }
        } else {

            WordOrientation orientation = checkNewWordOrientation();

            firstNewLetterIndex = findFirstLetterOfNewWordIndex(firstNewLetterIndex, orientation);
            lastNewLetterIndex = findLastLetterOfNewWordIndex(lastNewLetterIndex, orientation);
            findLettersInsideWord(firstNewLetterIndex, lastNewLetterIndex, orientation);

            findAdditionalWord(orientation);
        }
    }

    private boolean annyLetterNextToVertically(Cell singleLetterCell) {
        return (!singleLetterCell.inFirstRow() && oldBoard.getCellAbove(singleLetterCell).containsLetter())
                || (!singleLetterCell.inLastRow() && oldBoard.getCellBellow(singleLetterCell).containsLetter());
    }

    private boolean anyLetterNextToHorizontally(Cell singleLetterCell) {
        return (!singleLetterCell.inFirstColumn() && oldBoard.getCellLeft(singleLetterCell).containsLetter())
                || (!singleLetterCell.inLastColumn() && oldBoard.getCellRight(singleLetterCell).containsLetter());
    }

    private boolean onlyOneLetterAdded() {
        return newCells.size() == 1;
    }

    private int findFirstLetterOfNewWordIndex(int firstNewLetterIndex, WordOrientation orientation) {

        Cell beginningCell = newBoard.getCellByIndex(firstNewLetterIndex);
        switch (orientation) {
            case HORIZONTAL:
                if (firstNewLetterIndex % 100 > 0) {
                    int i = 1;
                    Cell previousCell = newBoard.getCellByIndex(firstNewLetterIndex - i);
                    while ((firstNewLetterIndex - i) % 100 >= 0 && previousCell.containsLetter()) {
                        previousCell = newBoard.getCellByIndex(firstNewLetterIndex - i);
                        if (previousCell.containsLetter()) {
                            beginningCell = previousCell;
                        } else {
                            break;
                        }
                        i++;
                    }
                }
                ;
                return beginningCell.getCellIndex();
            case VERTICAL:
                if (firstNewLetterIndex >= 100) {
                    int j = 1;
                    Cell previousCell = newBoard.getCellByIndex(firstNewLetterIndex - j * 100);
                    while (firstNewLetterIndex - j * 100 >= 0 && previousCell.containsLetter()) {
                        previousCell = newBoard.getCellByIndex(firstNewLetterIndex - j * 100);
                        if (previousCell.containsLetter()) {
                            beginningCell = previousCell;
                        } else {
                            break;
                        }
                        j++;
                    }
                }
                return beginningCell.getCellIndex();
        }
        return firstNewLetterIndex;
    }

    private int findLastLetterOfNewWordIndex(int lastNewLetterIndex, WordOrientation orientation) {

        Cell endingCell = newBoard.getCellByIndex(lastNewLetterIndex);
        switch (orientation) {
            case HORIZONTAL:
                if (lastNewLetterIndex % 100 < 14) {
                    int i = 1;
                    Cell nextCell = newBoard.getCellByIndex(lastNewLetterIndex + i);
                    while ((lastNewLetterIndex + i) % 100 <= 14 && nextCell.containsLetter()) {
                        nextCell = newBoard.getCellByIndex(lastNewLetterIndex + i);
                        if (nextCell.containsLetter()) {
                            endingCell = nextCell;
                        }
                        i++;
                    }
                }
                ;
                return endingCell.getCellIndex();
            case VERTICAL:
                if (lastNewLetterIndex < 1400) {
                    int j = 1;
                    Cell nextCell = newBoard.getCellByIndex(lastNewLetterIndex + j * 100);
                    while (lastNewLetterIndex + j * 100 <= 1414 && nextCell.containsLetter()) {
                        nextCell = newBoard.getCellByIndex(lastNewLetterIndex + j * 100);
                        if (nextCell.containsLetter()) {
                            endingCell = nextCell;
                        }
                        j++;
                    }
                }
                return endingCell.getCellIndex();
        }
        return lastNewLetterIndex;
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

    public void findAdditionalWord(WordOrientation orientation) {
        LinkedList<Cell> cellsToCheck = new LinkedList<>();

        int firstNewLetterIndex;
        int lastNewLetterIndex;

        switch (orientation) {

            case HORIZONTAL:
                Cell cellAbove;
                Cell cellBellow;

                for (Cell cell : newCells) {

                    int currentIndex = cell.getCellIndex();
                    if (currentIndex >= 100) {
                        cellAbove = oldBoard.getCellByIndex(currentIndex - 100);
                        if (cellAbove.containsLetter()) {
                            cellsToCheck.add(cellAbove);
                            break;
                        }
                    }

                    if (currentIndex < 1400) {
                        cellBellow = oldBoard.getCellByIndex(currentIndex + 100);
                        if (cellBellow.containsLetter()) {
                            cellsToCheck.add(cellBellow);
                        }
                    }
                }
                for (Cell cell : cellsToCheck) {
                    firstNewLetterIndex = findFirstLetterOfNewWordIndex(cell.getCellIndex(), WordOrientation.VERTICAL);
                    lastNewLetterIndex = findLastLetterOfNewWordIndex(cell.getCellIndex(), WordOrientation.VERTICAL);
                    findLettersInsideWord(firstNewLetterIndex, lastNewLetterIndex, WordOrientation.VERTICAL);
                }
                break;
            case VERTICAL:
                Cell cellRight;
                Cell cellLeft;

                for (Cell cell : newCells) {

                    int currentIndex = cell.getCellIndex();
                    if (currentIndex % 100 > 0) {
                        cellLeft = oldBoard.getCellByIndex(currentIndex - 1);
                        if (cellLeft.containsLetter()) {
                            cellsToCheck.add(cellLeft);
                            break;
                        }
                    }
                    if (currentIndex % 100 < 14) {
                        cellRight = oldBoard.getCellByIndex(currentIndex + 1);
                        if (cellRight.containsLetter()) {
                            cellsToCheck.add(cellRight);
                        }
                    }
                }
                for (Cell cell : cellsToCheck) {
                    firstNewLetterIndex = findFirstLetterOfNewWordIndex(cell.getCellIndex(), WordOrientation.HORIZONTAL);
                    lastNewLetterIndex = findLastLetterOfNewWordIndex(cell.getCellIndex(), WordOrientation.HORIZONTAL);
                    findLettersInsideWord(firstNewLetterIndex, lastNewLetterIndex, WordOrientation.HORIZONTAL);
                }
        }

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

    public void setNewBoard(Board newBoard) {
        this.newBoard = newBoard;
    }

    private enum WordOrientation {
        VERTICAL,
        HORIZONTAL;
    }


    private void trialBoardPrint() {

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

