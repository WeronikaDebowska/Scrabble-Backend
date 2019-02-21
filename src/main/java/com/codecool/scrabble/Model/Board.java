package com.codecool.scrabble.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Board {

    private int size = 15;
    private Cell[] board = new Cell[size * size];
    private int indexInc = 100;

//    private CellParams cellParams;

    @Autowired
    public Board() {
//        this.cellParams = cellParams;
        createBoard();
        setBonuses();
    }

    private void createBoard() {

        int i = 0;
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                int cellIndex = row * indexInc + column;
//                    board[i] = new Cell(cellIndex, cellParams.getLetterBonus(cellIndex), cellParams.getWordBonus(cellIndex));
                board[i] = new Cell();
                board[i].setCellIndex(cellIndex);
                i++;
            }
        }
    }

    private void setBonuses() {

        int[] cellsIndexesWithDoubleLetterBonus = new int[]{4, 11, 206, 208, 300, 307, 314, 602, 606, 608, 612,
                703, 711, 802, 806, 808, 812, 1100, 1107, 1114, 1206, 1208, 1404, 1411};

        int[] cellIndexesWithTripleLetterBonus = new int[]{105, 109, 501, 505, 509, 513, 901, 905, 909, 913, 1405, 1409};

        int[] cellIndexesWithDoubleWordBonus = new int[]{101, 113, 202, 212, 303, 311, 404, 410, 1004, 1010, 1103,
                1111, 1202, 1212, 1301, 1313};

        int[] cellIndexesWithTripleWordBonus = new int[]{0, 7, 14, 700, 714, 1400, 1407, 1414};

        for (int index : cellsIndexesWithDoubleLetterBonus) {
            getCellByIndex(index).setLetterBonus(2);
        }

        for (int index : cellIndexesWithTripleLetterBonus) {
            getCellByIndex(index).setLetterBonus(3);
        }

        for (int index : cellIndexesWithDoubleWordBonus) {
            getCellByIndex(index).setWordBonus(2);
        }

        for (int index : cellIndexesWithTripleWordBonus) {
            getCellByIndex(index).setWordBonus(3);
        }
    }


    public Cell getCellByIndex(int index) {

        for (Cell cell : board) {               //TODO
            if (cell.getCellIndex() == index) {
                return cell;
            }
        }
        return null;
    }

    public Cell getCellAbove(Cell cell) {
        return this.getCellByIndex(cell.getCellIndex() - indexInc);
    }

    public Cell getCellBelow(Cell cell) {
        return this.getCellByIndex(cell.getCellIndex() + indexInc);
    }

    public Cell getCellRight(Cell cell) {
        return this.getCellByIndex(cell.getCellIndex() + 1);
    }

    public Cell getCellLeft(Cell cell) {
        return this.getCellByIndex(cell.getCellIndex() - 1);
    }


    public int getLetterBonus(int index) {
        Cell cell = getCellByIndex(index);
        return cell.getLetterBonus();
    }

    public int getWordBonus(int index) {
        Cell cell = getCellByIndex(index);
        return cell.getWordBonus();
    }

    public void setLetterBonus(int index, int bonus) {
        getCellByIndex(index).setLetterBonus(bonus);
    }

    public void setWordBonus(int index, int bonus) {
        getCellByIndex(index).setWordBonus(bonus);
    }


    public Cell[] getBoard() {
        return board;
    }

    public void setBoard(Cell[] board) {
        this.board = board;
    }

    public void clearAll() {
        for (Cell cell : board) {
            cell.setLetter('-');
        }
    }
}
