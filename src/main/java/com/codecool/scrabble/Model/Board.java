package com.codecool.scrabble.Model;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Board {

    private int size = 15;
    private Cell[] board = new Cell[size * size];

    public Board() {
        createBoard();
        setBonuses();
    }

    private void createBoard() {
        int index = 0;
        int k = 0;

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                board[k] = new Cell();
                board[k].setCellIndex(index + j);
                k++;
            }
            index += 100;
        }
    }

    private void setBonuses() {

        //set Letter bonuses
        //2
        setLetterBonus(4, 2);
        setLetterBonus(11, 2);
        setLetterBonus(206, 2);
        setLetterBonus(208, 2);
        setLetterBonus(300, 2);
        setLetterBonus(307, 2);
        setLetterBonus(314, 2);
        setLetterBonus(602, 2);
        setLetterBonus(606, 2);
        setLetterBonus(608, 2);
        setLetterBonus(612, 2);
        setLetterBonus(703, 2);
        setLetterBonus(711, 2);
        setLetterBonus(802, 2);
        setLetterBonus(806, 2);
        setLetterBonus(808, 2);
        setLetterBonus(812, 2);
        setLetterBonus(1100, 2);
        setLetterBonus(1107, 2);
        setLetterBonus(1114, 2);
        setLetterBonus(1206, 2);
        setLetterBonus(1208, 2);
        setLetterBonus(1404, 2);
        setLetterBonus(1411, 2);

        //3

        setLetterBonus(105, 3);
        setLetterBonus(109, 3);
        setLetterBonus(501, 3);
        setLetterBonus(505, 3);
        setLetterBonus(509, 3);
        setLetterBonus(513, 3);
        setLetterBonus(901, 3);
        setLetterBonus(905, 3);
        setLetterBonus(909, 3);
        setLetterBonus(913, 3);
        setLetterBonus(1405, 3);
        setLetterBonus(1409, 3);

        //set word bonuses
        //2
        setWordBonus(101, 2);
        setWordBonus(113, 2);
        setWordBonus(202, 2);
        setWordBonus(212, 2);
        setWordBonus(303, 2);
        setWordBonus(311, 2);
        setWordBonus(404, 2);
        setWordBonus(410, 2);

        setWordBonus(1004, 2);
        setWordBonus(1010, 2);
        setWordBonus(1103, 2);
        setWordBonus(1111, 2);
        setWordBonus(1202, 2);
        setWordBonus(1212, 2);
        setWordBonus(1301, 2);
        setWordBonus(1313, 2);

        //3
        setWordBonus(0, 3);
        setWordBonus(7, 3);
        setWordBonus(14, 3);
        setWordBonus(700, 3);
        setWordBonus(714, 3);
        setWordBonus(1400, 3);
        setWordBonus(1407, 3);
        setWordBonus(1414, 3);
    }


    public Cell getCellByIndex(int index) {

        for (Cell cell : board) {
            if (cell.getCellIndex() == index) {
                return cell;
            }
        }

        return null;
//        return (Cell) Arrays.stream(board)
//                .filter(c -> c.getCellIndex() == index);
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

    public void printBoard() {
        Arrays.stream(board)
                .peek(c -> System.out.println(c.getCellIndex()));
    }

}
