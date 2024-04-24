public class SudokuSolver {
    public static void main(String[] args) {
        int[][] board = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        System.out.println("Original Sudoku board:");
        printBoard(board);
        System.out.println("\nSolving...\n");

        if (solve(board)) {
            System.out.println("Solved Sudoku board:");
            printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }
    }

    static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("- - - - - - - - - - - - ");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print(" | ");
                }
                if (j == 8) {
                    System.out.println(board[i][j]);
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
        }
    }

    static boolean solve(int[][] board) {
        int[] find = findEmpty(board);
        if (find == null) {
            return true;
        } else {
            int row = find[0];
            int col = find[1];
            
            for (int i = 1; i <= 9; i++) {
                if (isValid(board, i, row, col)) {
                    board[row][col] = i;

                    if (solve(board)) {
                        return true;
                    }

                    board[row][col] = 0;
                }
            }
            return false;
        }
    }

    static int[] findEmpty(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    static boolean isValid(int[][] board, int num, int row, int col) {
        // Check row
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num && i != col) {
                return false;
            }
        }

        // Check column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num && i != row) {
                return false;
            }
        }

        // Check box
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (board[i][j] == num && i != row && j != col) {
                    return false;
                }
            }
        }
        return true;
    }
}
