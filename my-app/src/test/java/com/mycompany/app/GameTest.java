package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
        Game.DEBUG = false;
    }

    @Test
    void emptyBoardIsPlaying() {
        game.symbol = 'X';
        assertEquals(State.PLAYING, game.checkState(game.board));
    }

    @ParameterizedTest
    @CsvSource({
        "0,1,2,X",
        "3,4,5,O",
        "6,7,8,X",
        "0,3,6,O",
        "1,4,7,X",
        "2,5,8,O",
        "0,4,8,X",
        "2,4,6,O"
    })
    void detectsWinForSymbol(int a, int b, int c, char sym) {
        fillSpaces();
        game.board[a] = sym;
        game.board[b] = sym;
        game.board[c] = sym;
        game.symbol = sym;
        State expected = sym == 'X' ? State.XWIN : State.OWIN;
        assertEquals(expected, game.checkState(game.board));
    }

    private void fillSpaces() {
        for (int i = 0; i < 9; i++) {
            game.board[i] = ' ';
        }
    }

    @Test
    void fullBoardNoWinIsDraw() {
        char[] b = game.board;
        // X O X / O X O / O X O — заполненная доска без победителя
        b[0] = 'X';
        b[1] = 'O';
        b[2] = 'X';
        b[3] = 'O';
        b[4] = 'X';
        b[5] = 'O';
        b[6] = 'O';
        b[7] = 'X';
        b[8] = 'O';
        game.symbol = 'O';
        assertEquals(State.DRAW, game.checkState(b));
    }

    @Test
    void generateMovesListsEmptyCells() {
        game.board[0] = 'X';
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertEquals(8, moves.size());
        assertTrue(moves.contains(1));
    }

    @Test
    void evaluateWinForMaxPlayer() {
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = 'X';
        game.symbol = 'X';
        assertEquals(Game.INF, game.evaluatePosition(game.board, game.player1));
    }

    @Test
    void evaluateLossForMaxPlayer() {
        game.board[0] = 'O';
        game.board[1] = 'O';
        game.board[2] = 'O';
        game.symbol = 'O';
        assertEquals(-Game.INF, game.evaluatePosition(game.board, game.player1));
    }

    @Test
    void evaluateDraw() {
        char[] b = game.board;
        b[0] = 'X';
        b[1] = 'O';
        b[2] = 'X';
        b[3] = 'O';
        b[4] = 'X';
        b[5] = 'O';
        b[6] = 'O';
        b[7] = 'X';
        b[8] = 'O';
        game.symbol = 'O';
        assertEquals(0, game.evaluatePosition(game.board, game.player1));
    }

    @Test
    void evaluateNonTerminal() {
        assertEquals(-1, game.evaluatePosition(game.board, game.player1));
    }

    @Test
    void miniMaxChoosesMoveMidGame() {
        game.board[0] = 'X';
        game.board[1] = 'O';
        game.board[3] = 'O';
        game.board[4] = 'X';
        int m = game.MiniMax(game.board, game.player2);
        assertTrue(m >= 1 && m <= 9);
    }

    @Test
    void miniMaxOneCellLeft() {
        for (int i = 0; i < 9; i++) {
            game.board[i] = i == 4 ? ' ' : (i % 2 == 0 ? 'X' : 'O');
        }
        game.board[4] = ' ';
        int m = game.MiniMax(game.board, game.player2);
        assertEquals(5, m);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8})
    void maxMoveDepth(int empty) {
        for (int i = 0; i < 9; i++) {
            game.board[i] = i == empty ? ' ' : 'X';
        }
        game.symbol = 'X';
        game.MaxMove(game.board, game.player1);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void minMoveBranches(int n) {
        fillSpaces();
        for (int i = 0; i < n; i++) {
            game.board[i] = 'X';
        }
        game.symbol = 'O';
        game.MinMove(game.board, game.player2);
    }

    @Test
    void playersInitialized() {
        assertEquals('X', game.player1.symbol);
        assertEquals('O', game.player2.symbol);
    }

    @Test
    void infConstant() {
        assertEquals(100, Game.INF);
    }
}
