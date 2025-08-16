package com.sam.tictactoe;

import java.util.Scanner;

public class TicTacToe {
    static char[][] board = {
        {'1', '2', '3'},
        {'4', '5', '6'},
        {'7', '8', '9'}
    };

    static char currentPlayer = 'X';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean gameEnded = false;

        while (!gameEnded) {
            printBoard();
            System.out.println("Player " + currentPlayer + ", enter your move (1-9): ");
            String input = scanner.nextLine();

            if (!input.matches("[1-9]")) {
                System.out.println("Invalid input. Try again.");
                continue;
            }

            int move = Integer.parseInt(input);
            if (!placeMove(move)) {
                System.out.println("Cell already taken. Choose another.");
                continue;
            }

            if (checkWin()) {
                printBoard();
                System.out.println("🎉 Player " + currentPlayer + " wins!");
                gameEnded = true;
            } else if (checkDraw()) {
                printBoard();
                System.out.println("It's a draw!");
                gameEnded = true;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }

        scanner.close();
    }

    public static void printBoard() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.println(" " + board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            if (i < 2) System.out.println("---|---|---");
        }
        System.out.println();
    }

    public static boolean placeMove(int move) {
        int row = (move - 1) / 3;
        int col = (move - 1) % 3;

        if (board[row][col] == 'X' || board[row][col] == 'O') {
            return false;
        }

        board[row][col] = currentPlayer;
        return true;
    }

    public static boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer &&
                board[i][1] == currentPlayer &&
                board[i][2] == currentPlayer) return true;

            if (board[0][i] == currentPlayer &&
                board[1][i] == currentPlayer &&
                board[2][i] == currentPlayer) return true;
        }

        if (board[0][0] == currentPlayer &&
            board[1][1] == currentPlayer &&
            board[2][2] == currentPlayer) return true;

        if (board[0][2] == currentPlayer &&
            board[1][1] == currentPlayer &&
            board[2][0] == currentPlayer) return true;

        return false;
    }

    public static boolean checkDraw() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell != 'X' && cell != 'O') return false;
            }
        }
        return true;
    }
}
