
public class solution {
    public static boolean sudoku_solver(int[][] sudoku, int i, int k) {

        // base case
        if (i == 9) {
            return true;
        }

        // calculating next row and column
        int next_row = i;
        int next_column = k+1;
        if (k+1==9) {
            next_row = i+1;
            next_column = 0;
        }
        
        
        // work
        if (sudoku[i][k] != 0) {
            return sudoku_solver(sudoku, next_row,next_column);
        }
        for (int n = 1; n <= 9; n++) {
            if (is_safe(n, sudoku, i, k)) {
                sudoku[i][k] = n;
                if (sudoku_solver(sudoku, next_row,next_column)) {
                    return true;
                }
                // this is the step of back tracking where it realises that in this way solution
                // does not exists we
                // need to back - track
                sudoku[i][k] = 0;
            }
        }
        return false;
    }

    public static boolean is_safe(int n, int[][] sudoku, int row, int column) {
        if (horizontal(row, sudoku, n) && vertical(column, sudoku, n) && grid_wise(row, column, sudoku, n)) {
            return true;
        }
        return false;
    }

    // function to check horizontaly
    public static boolean horizontal(int row, int[][] sudoku, int n) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[row][i] == n) {
                return false;
            }
        }
        return true;
    }

    // function to check vertically
    public static boolean vertical(int column, int[][] sudoku, int n) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][column] == n) {
                return false;
            }
        }
        return true;
    }

    // function to check gridwise
    public static boolean grid_wise(int row, int column, int[][] sudoku, int n) {
        row = (row / 3) * 3;
        column = (column / 3) * 3;
        for (int i = row; i <= row + 2; i++) {
            for (int j = column; j <= column + 2; j++) {
                if (sudoku[i][j] == n) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] ques = { { 0, 0, 8, 0, 0, 0, 0, 0, 0 },
                { 4, 9, 0, 1, 5, 7, 0, 0, 2 },
                { 0, 0, 3, 0, 0, 4, 1, 9, 0 },
                { 1, 8, 5, 0, 6, 0, 0, 2, 0 },
                { 0, 0, 0, 0, 2, 0, 0, 6, 0 },
                { 9, 6, 0, 4, 0, 5, 3, 0, 0 },
                { 0, 3, 0, 0, 7, 2, 0, 0, 4 },
                { 0, 4, 9, 0, 3, 0, 0, 5, 7 },
                { 8, 2, 7, 0, 0, 9, 0, 1, 3 }
        };

        if (sudoku_solver(ques, 0, 0)) {
            System.out.println("Solution exists");
            for (int i = 0; i < ques.length; i++) {
                for (int j = 0; j < ques.length; j++) {
                    System.out.print(ques[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("Does not exists");
        }

    }
}