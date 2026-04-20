package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class UtilityAndCellTest {

    @Test
    void utilityPrintCharBoard() {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(buf);
        PrintStream old = System.out;
        System.setOut(ps);
        char[] b = new char[] {'X', 'O', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        Utility.print(b);
        System.setOut(old);
        assertTrue(buf.toString().contains("X"));
    }

    @Test
    void utilityPrintIntBoard() {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(buf);
        PrintStream old = System.out;
        System.setOut(ps);
        int[] a = new int[9];
        for (int i = 0; i < 9; i++) {
            a[i] = i;
        }
        Utility.print(a);
        System.setOut(old);
        assertTrue(buf.toString().contains("0"));
    }

    @Test
    void utilityPrintMoveList() {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(buf);
        PrintStream old = System.out;
        System.setOut(ps);
        ArrayList<Integer> m = new ArrayList<>();
        m.add(3);
        m.add(7);
        Utility.print(m);
        System.setOut(old);
        assertTrue(buf.toString().contains("3"));
    }

    @Test
    void ticTacToeCellProps() {
        TicTacToeCell cell = new TicTacToeCell(5, 2, 1);
        assertEquals(5, cell.getNum());
        assertEquals(2, cell.getCol());
        assertEquals(1, cell.getRow());
        assertEquals(' ', cell.getMarker());
        cell.setMarker("X");
        assertEquals('X', cell.getMarker());
    }

    @Test
    void stateEnumValues() {
        State[] v = State.values();
        assertEquals(4, v.length);
    }
}
