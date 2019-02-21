package com.codecool.scrabble.Model;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CellParams {

    // map with a cellIndex as a key and letterBonus and wordBonus in value list
    private HashMap<Integer, Integer[]> cellsBonuses = new HashMap<Integer, Integer[]>();

    public CellParams() {
        createCellBonusesMap();
    }

    private void createCellBonusesMap() {

        // ------------- row 0 ------------
        cellsBonuses.put(0, new Integer[]{1, 3});
        cellsBonuses.put(1, new Integer[]{1, 1});
        cellsBonuses.put(2, new Integer[]{1, 1});
        cellsBonuses.put(3, new Integer[]{1, 1});
        cellsBonuses.put(4, new Integer[]{2, 1});
        cellsBonuses.put(5, new Integer[]{1, 1});
        cellsBonuses.put(6, new Integer[]{1, 1});
        cellsBonuses.put(7, new Integer[]{1, 3});
        cellsBonuses.put(8, new Integer[]{1, 1});
        cellsBonuses.put(9, new Integer[]{1, 1});
        cellsBonuses.put(10, new Integer[]{1, 1});
        cellsBonuses.put(11, new Integer[]{2, 1});
        cellsBonuses.put(12, new Integer[]{1, 1});
        cellsBonuses.put(13, new Integer[]{1, 1});
        cellsBonuses.put(14, new Integer[]{1, 3});

        // ------------- row 1 ------------
        cellsBonuses.put(100, new Integer[]{1, 1});
        cellsBonuses.put(101, new Integer[]{1, 2});
        cellsBonuses.put(102, new Integer[]{1, 1});
        cellsBonuses.put(103, new Integer[]{1, 1});
        cellsBonuses.put(104, new Integer[]{1, 1});
        cellsBonuses.put(105, new Integer[]{3, 1});
        cellsBonuses.put(106, new Integer[]{1, 1});
        cellsBonuses.put(107, new Integer[]{1, 1});
        cellsBonuses.put(108, new Integer[]{1, 1});
        cellsBonuses.put(109, new Integer[]{3, 1});
        cellsBonuses.put(110, new Integer[]{1, 1});
        cellsBonuses.put(111, new Integer[]{1, 1});
        cellsBonuses.put(112, new Integer[]{1, 1});
        cellsBonuses.put(113, new Integer[]{1, 2});
        cellsBonuses.put(114, new Integer[]{1, 1});

        // ------------- row 2 ------------
        cellsBonuses.put(200, new Integer[]{1, 1});
        cellsBonuses.put(201, new Integer[]{1, 1});
        cellsBonuses.put(202, new Integer[]{1, 2});
        cellsBonuses.put(203, new Integer[]{1, 1});
        cellsBonuses.put(204, new Integer[]{1, 1});
        cellsBonuses.put(205, new Integer[]{1, 1});
        cellsBonuses.put(206, new Integer[]{2, 1});
        cellsBonuses.put(207, new Integer[]{1, 1});
        cellsBonuses.put(208, new Integer[]{2, 1});
        cellsBonuses.put(209, new Integer[]{1, 1});
        cellsBonuses.put(210, new Integer[]{1, 1});
        cellsBonuses.put(211, new Integer[]{1, 1});
        cellsBonuses.put(212, new Integer[]{1, 1});
        cellsBonuses.put(213, new Integer[]{1, 2});
        cellsBonuses.put(214, new Integer[]{1, 1});

        // ------------- row 3 ------------
        cellsBonuses.put(300, new Integer[]{2, 1});
        cellsBonuses.put(301, new Integer[]{1, 1});
        cellsBonuses.put(302, new Integer[]{1, 1});
        cellsBonuses.put(303, new Integer[]{1, 2});
        cellsBonuses.put(304, new Integer[]{1, 1});
        cellsBonuses.put(305, new Integer[]{1, 1});
        cellsBonuses.put(306, new Integer[]{1, 1});
        cellsBonuses.put(307, new Integer[]{2, 1});
        cellsBonuses.put(308, new Integer[]{1, 1});
        cellsBonuses.put(309, new Integer[]{1, 1});
        cellsBonuses.put(310, new Integer[]{1, 1});
        cellsBonuses.put(311, new Integer[]{1, 2});
        cellsBonuses.put(312, new Integer[]{1, 1});
        cellsBonuses.put(313, new Integer[]{1, 1});
        cellsBonuses.put(314, new Integer[]{2, 1});

        // ------------- row 4 ------------
        cellsBonuses.put(400, new Integer[]{1, 1});
        cellsBonuses.put(401, new Integer[]{1, 1});
        cellsBonuses.put(402, new Integer[]{1, 1});
        cellsBonuses.put(403, new Integer[]{1, 1});
        cellsBonuses.put(404, new Integer[]{1, 2});
        cellsBonuses.put(405, new Integer[]{1, 1});
        cellsBonuses.put(406, new Integer[]{1, 1});
        cellsBonuses.put(407, new Integer[]{1, 1});
        cellsBonuses.put(408, new Integer[]{1, 1});
        cellsBonuses.put(409, new Integer[]{1, 1});
        cellsBonuses.put(410, new Integer[]{1, 2});
        cellsBonuses.put(411, new Integer[]{1, 1});
        cellsBonuses.put(412, new Integer[]{1, 1});
        cellsBonuses.put(413, new Integer[]{1, 1});
        cellsBonuses.put(414, new Integer[]{1, 1});

        // ------------- row 5 ------------
        cellsBonuses.put(500, new Integer[]{1, 1});
        cellsBonuses.put(501, new Integer[]{3, 1});
        cellsBonuses.put(502, new Integer[]{1, 1});
        cellsBonuses.put(503, new Integer[]{1, 1});
        cellsBonuses.put(504, new Integer[]{1, 1});
        cellsBonuses.put(505, new Integer[]{3, 1});
        cellsBonuses.put(506, new Integer[]{1, 1});
        cellsBonuses.put(507, new Integer[]{1, 1});
        cellsBonuses.put(508, new Integer[]{1, 1});
        cellsBonuses.put(509, new Integer[]{3, 1});
        cellsBonuses.put(510, new Integer[]{1, 1});
        cellsBonuses.put(511, new Integer[]{1, 1});
        cellsBonuses.put(512, new Integer[]{1, 1});
        cellsBonuses.put(513, new Integer[]{3, 1});
        cellsBonuses.put(514, new Integer[]{1, 1});

        // ------------- row 6 ------------
        cellsBonuses.put(600, new Integer[]{1, 1});
        cellsBonuses.put(601, new Integer[]{1, 1});
        cellsBonuses.put(602, new Integer[]{2, 1});
        cellsBonuses.put(603, new Integer[]{1, 1});
        cellsBonuses.put(604, new Integer[]{1, 1});
        cellsBonuses.put(605, new Integer[]{1, 1});
        cellsBonuses.put(606, new Integer[]{2, 1});
        cellsBonuses.put(607, new Integer[]{1, 1});
        cellsBonuses.put(608, new Integer[]{2, 1});
        cellsBonuses.put(609, new Integer[]{1, 1});
        cellsBonuses.put(610, new Integer[]{1, 1});
        cellsBonuses.put(611, new Integer[]{1, 1});
        cellsBonuses.put(612, new Integer[]{2, 1});
        cellsBonuses.put(613, new Integer[]{1, 1});
        cellsBonuses.put(614, new Integer[]{1, 1});

        // ------------- row 7 ------------
        cellsBonuses.put(700, new Integer[]{1, 3});
        cellsBonuses.put(701, new Integer[]{1, 1});
        cellsBonuses.put(702, new Integer[]{1, 1});
        cellsBonuses.put(703, new Integer[]{2, 1});
        cellsBonuses.put(704, new Integer[]{1, 1});
        cellsBonuses.put(705, new Integer[]{1, 1});
        cellsBonuses.put(706, new Integer[]{1, 1});
        cellsBonuses.put(707, new Integer[]{1, 3});
        cellsBonuses.put(708, new Integer[]{1, 1});
        cellsBonuses.put(709, new Integer[]{1, 1});
        cellsBonuses.put(710, new Integer[]{1, 1});
        cellsBonuses.put(711, new Integer[]{2, 1});
        cellsBonuses.put(712, new Integer[]{1, 1});
        cellsBonuses.put(713, new Integer[]{1, 1});
        cellsBonuses.put(714, new Integer[]{1, 3});

        // ------------- row 8 ------------
        cellsBonuses.put(800, new Integer[]{1, 1});
        cellsBonuses.put(801, new Integer[]{1, 1});
        cellsBonuses.put(802, new Integer[]{2, 1});
        cellsBonuses.put(803, new Integer[]{1, 1});
        cellsBonuses.put(804, new Integer[]{1, 1});
        cellsBonuses.put(805, new Integer[]{1, 1});
        cellsBonuses.put(806, new Integer[]{2, 1});
        cellsBonuses.put(807, new Integer[]{1, 1});
        cellsBonuses.put(808, new Integer[]{2, 1});
        cellsBonuses.put(809, new Integer[]{1, 1});
        cellsBonuses.put(810, new Integer[]{1, 1});
        cellsBonuses.put(811, new Integer[]{1, 1});
        cellsBonuses.put(812, new Integer[]{2, 1});
        cellsBonuses.put(813, new Integer[]{1, 1});
        cellsBonuses.put(814, new Integer[]{1, 1});

        // ------------- row 9 ------------
        cellsBonuses.put(900, new Integer[]{1, 1});
        cellsBonuses.put(901, new Integer[]{3, 1});
        cellsBonuses.put(902, new Integer[]{1, 1});
        cellsBonuses.put(903, new Integer[]{1, 1});
        cellsBonuses.put(904, new Integer[]{1, 1});
        cellsBonuses.put(905, new Integer[]{3, 1});
        cellsBonuses.put(906, new Integer[]{1, 1});
        cellsBonuses.put(907, new Integer[]{1, 1});
        cellsBonuses.put(908, new Integer[]{1, 1});
        cellsBonuses.put(909, new Integer[]{3, 1});
        cellsBonuses.put(910, new Integer[]{1, 1});
        cellsBonuses.put(911, new Integer[]{1, 1});
        cellsBonuses.put(912, new Integer[]{1, 1});
        cellsBonuses.put(913, new Integer[]{3, 1});
        cellsBonuses.put(914, new Integer[]{1, 1});

        // ------------- row 10 ------------
        cellsBonuses.put(1000, new Integer[]{2, 1});
        cellsBonuses.put(1001, new Integer[]{1, 1});
        cellsBonuses.put(1002, new Integer[]{1, 1});
        cellsBonuses.put(1003, new Integer[]{1, 1});
        cellsBonuses.put(1004, new Integer[]{1, 2});
        cellsBonuses.put(1005, new Integer[]{1, 1});
        cellsBonuses.put(1006, new Integer[]{1, 1});
        cellsBonuses.put(1007, new Integer[]{2, 1});
        cellsBonuses.put(1008, new Integer[]{1, 1});
        cellsBonuses.put(1009, new Integer[]{1, 1});
        cellsBonuses.put(1010, new Integer[]{1, 2});
        cellsBonuses.put(1011, new Integer[]{1, 1});
        cellsBonuses.put(1012, new Integer[]{1, 1});
        cellsBonuses.put(1013, new Integer[]{1, 1});
        cellsBonuses.put(1014, new Integer[]{2, 1});

        // ------------- row 11 ------------
        cellsBonuses.put(1100, new Integer[]{1, 1});
        cellsBonuses.put(1101, new Integer[]{1, 1});
        cellsBonuses.put(1102, new Integer[]{1, 1});
        cellsBonuses.put(1103, new Integer[]{1, 2});
        cellsBonuses.put(1104, new Integer[]{1, 1});
        cellsBonuses.put(1105, new Integer[]{1, 1});
        cellsBonuses.put(1106, new Integer[]{1, 1});
        cellsBonuses.put(1107, new Integer[]{1, 1});
        cellsBonuses.put(1108, new Integer[]{1, 1});
        cellsBonuses.put(1109, new Integer[]{1, 1});
        cellsBonuses.put(1110, new Integer[]{1, 1});
        cellsBonuses.put(1111, new Integer[]{1, 2});
        cellsBonuses.put(1112, new Integer[]{1, 1});
        cellsBonuses.put(1113, new Integer[]{1, 1});
        cellsBonuses.put(1114, new Integer[]{1, 1});

        // ------------- row 12 ------------
        cellsBonuses.put(1200, new Integer[]{1, 1});
        cellsBonuses.put(1201, new Integer[]{1, 1});
        cellsBonuses.put(1202, new Integer[]{1, 2});
        cellsBonuses.put(1203, new Integer[]{1, 1});
        cellsBonuses.put(1204, new Integer[]{1, 1});
        cellsBonuses.put(1205, new Integer[]{1, 1});
        cellsBonuses.put(1206, new Integer[]{2, 1});
        cellsBonuses.put(1207, new Integer[]{1, 1});
        cellsBonuses.put(1208, new Integer[]{2, 1});
        cellsBonuses.put(1209, new Integer[]{1, 1});
        cellsBonuses.put(1210, new Integer[]{1, 1});
        cellsBonuses.put(1211, new Integer[]{1, 1});
        cellsBonuses.put(1212, new Integer[]{1, 2});
        cellsBonuses.put(1213, new Integer[]{1, 1});
        cellsBonuses.put(1214, new Integer[]{1, 1});

        // ------------- row 13 ------------
        cellsBonuses.put(1300, new Integer[]{1, 1});
        cellsBonuses.put(1301, new Integer[]{1, 2});
        cellsBonuses.put(1302, new Integer[]{1, 1});
        cellsBonuses.put(1303, new Integer[]{1, 1});
        cellsBonuses.put(1304, new Integer[]{1, 1});
        cellsBonuses.put(1305, new Integer[]{1, 1});
        cellsBonuses.put(1306, new Integer[]{1, 1});
        cellsBonuses.put(1307, new Integer[]{1, 1});
        cellsBonuses.put(1308, new Integer[]{1, 1});
        cellsBonuses.put(1309, new Integer[]{1, 1});
        cellsBonuses.put(1310, new Integer[]{1, 1});
        cellsBonuses.put(1311, new Integer[]{1, 1});
        cellsBonuses.put(1312, new Integer[]{1, 1});
        cellsBonuses.put(1313, new Integer[]{1, 2});
        cellsBonuses.put(1314, new Integer[]{1, 1});

        // ------------- row 14 ------------
        cellsBonuses.put(1400, new Integer[]{1, 3});
        cellsBonuses.put(1401, new Integer[]{1, 1});
        cellsBonuses.put(1402, new Integer[]{1, 1});
        cellsBonuses.put(1403, new Integer[]{1, 1});
        cellsBonuses.put(1404, new Integer[]{2, 1});
        cellsBonuses.put(1405, new Integer[]{3, 1});
        cellsBonuses.put(1406, new Integer[]{1, 1});
        cellsBonuses.put(1407, new Integer[]{1, 3});
        cellsBonuses.put(1408, new Integer[]{1, 1});
        cellsBonuses.put(1409, new Integer[]{3, 1});
        cellsBonuses.put(1410, new Integer[]{1, 1});
        cellsBonuses.put(1411, new Integer[]{2, 1});
        cellsBonuses.put(1412, new Integer[]{1, 1});
        cellsBonuses.put(1413, new Integer[]{1, 1});
        cellsBonuses.put(1414, new Integer[]{1, 3});
    }


    public int getLetterBonus(int index) {
        return cellsBonuses.get(index)[0];
    }

    public int getWordBonus(int index) {
        return cellsBonuses.get(index)[1];
    }

}


