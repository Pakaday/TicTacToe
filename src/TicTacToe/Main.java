package TicTacToe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        mainGame();
    }

    public static void mainGame() {
        String[][] board = initializeBoard();
        drawBoard(board);

        String currentPlayer = "X";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Player " + currentPlayer + "'s turn:");
            int[] move = getPlayerMove(scanner);
            int row = move[0];
            int col = move[1];

            if (board[row][col].equals(" ")) {
                board[row][col] = currentPlayer;
                drawBoard(board);
            } else {
                System.out.println("That spot is taken. Try again.");
                continue;
            }

            String winner = checkWinner(board);
            if (winner != (null)) {
                System.out.println("Player " + winner + " wins!");
                break;
            }

            if (isBoardFull(board)) {
                System.out.println("It's a draw!");
                break;
            }

            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        }

        System.out.println("Do you want to play again? (y/n): ");
        String response = scanner.next();
        if (response.equalsIgnoreCase("y")) {
            mainGame();
        } else {
            System.out.println("Goodbye!");
        }
    }

    public static void drawBoard(String[][] board) {
        for (int i = 0; i < board.length; i++) {
            StringBuilder rowBuilder = new StringBuilder();
            for (int j = 0; j < board.length; j++) {
                rowBuilder.append(board[i][j]);
                if (j < board[i].length - 1) {
                    rowBuilder.append(" | ");
                }
            }

            System.out.println(rowBuilder);
            if ( i < board.length - 1) {
                System.out.println("---------");
            }
        }
    }

    public static String[][] initializeBoard() {
        String[][] board = new String[3][3];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = " ";
            }
        }
        return board;
    }

    public static int[] getPlayerMove(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter row 1, 2 or 3: ");
                int row = scanner.nextInt() - 1;
                System.out.print("Enter column 1, 2 or 3: ");
                int col = scanner.nextInt() - 1;
                if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
                    return new int[]{row, col};
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 3");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
    }

    public static String checkWinner(String[][] board) {
        int[][][] winningConditions = {
                // rows
                {{0, 0}, {0, 1}, {0, 2}},
                {{1, 0}, {1, 1}, {1, 2}},
                {{2, 0}, {2, 1}, {2, 2}},
                // columns
                {{0, 0}, {1, 0}, {2, 0}},
                {{0, 1}, {1, 1}, {2, 1}},
                {{0, 2}, {1, 2}, {2, 2}},
                // diagonals
                {{0, 0}, {1, 1}, {2, 2}},
                {{0, 2}, {1, 1}, {2, 0}}
        };

        for (String player : new String[]{"X", "O"}) {
            for (int[][] condition : winningConditions) {
                boolean win = true;
                for (int[] position : condition) {
                    int row = position[0];
                    int col = position[1];
                    if (!board[row][col].equals(player)) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    return player;
                }
            }
        }
        return null;
    }

    public static boolean isBoardFull(String[][] board) {
        for (String[] row : board) {
            for (String cell : row) {
                if (cell.equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}