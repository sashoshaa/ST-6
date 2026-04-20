package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import org.junit.jupiter.api.Test;

class PanelAndProgramTest {

    @Test
    void mainHeadlessExitsEarly() {
        assertDoesNotThrow(() -> Program.main(new String[0]));
    }

    @Test
    void showEndDialogHeadless() {
        assertDoesNotThrow(() -> {
            TicTacToePanel.showEndDialog(State.XWIN);
            TicTacToePanel.showEndDialog(State.OWIN);
            TicTacToePanel.showEndDialog(State.DRAW);
        });
    }

    @Test
    void panelCreatesAndFirstClick() {
        TicTacToePanel panel = new TicTacToePanel(new GridLayout(3, 3));
        TicTacToeCell c0 = panel.getCell(0);
        c0.doClick();
    }

    @Test
    void panelSeveralClicks() {
        TicTacToePanel panel = new TicTacToePanel(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            panel.getCell(i).doClick();
        }
    }

    @Test
    void actionEventDirect() {
        TicTacToePanel panel = new TicTacToePanel(new GridLayout(3, 3));
        TicTacToeCell c = panel.getCell(4);
        ActionEvent ev = new ActionEvent(c, ActionEvent.ACTION_PERFORMED, "click");
        panel.actionPerformed(ev);
    }
}
