//Seminar work for subject - Advanced algorithams - Perica Ilievski 102528

public class NKings {
    public static void main(String[] args) throws Exception {
        int N = 8; // N is number of kings that shoud be placed and size of chess table(N x N)
        int maxSolutions = 100; // Maximum number of solutions, so it does not go to endless loop
        NKings kings = new NKings(N, maxSolutions); // Object from NKings class
        kings.solve(0); // Function solve() that is solving the N-Kings problem, in this situation
                        // 8-Kings problem
    }

    int N;
    int chessboard[][];
    int solutionsFound = 0;
    int maxSolutions = 0;
    boolean found;

    // Constructor
    public NKings(int N, int maxSolutions) {
        this.N = N;
        this.maxSolutions = maxSolutions;
        this.chessboard = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.chessboard[i][j] = 0; // Matrix is filling with 0, when king will be put in the chess table, it
                                           // will change to 1
            }
        }
    }

    // Function to check if a position is safe for a king
    public boolean isValid(int row, int col) {
        // Check the left side of the chessboard
        for (int i = 0; i < col; i++)
            if (chessboard[row][i] == 1)
                return false;

        // Check the diagonal above it
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (chessboard[i][j] == 1)
                return false;

        // Check the diagonal below it
        for (int i = row, j = col; j >= 0 && i < N; i++, j--)
            if (chessboard[i][j] == 1)
                return false;

        // Check above the element
        for (int i = 0; i < row; i++)
            if (chessboard[i][col] == 1)
                return false;

        // Check the right side of the chessboard
        for (int i = col + 1; i < N; i++)
            if (chessboard[row][i] == 1)
                return false;

        // Check the diagonal above it
        for (int i = row, j = col; i >= 0 && j < N; i--, j++)
            if (chessboard[i][j] == 1)
                return false;

        // Check the diagonal below it
        for (int i = row, j = col; j < N && i < N; i++, j++)
            if (chessboard[i][j] == 1)
                return false;

        // Check under the element
        for (int i = row + 1; i < N; i++)
            if (chessboard[i][col] == 1)
                return false;

        return true;
    }

    // Function to print the chessboard
    public void printSolution() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + chessboard[i][j]
                        + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Recursive function to find solutions
    public void solve(int col) {
        //Check if number of found solutions is equal to number of maximum solutions that shoud be found
        if (solutionsFound == maxSolutions) {
            return;
        }

        //Base case, check if current column is equal to N, if it is, increase the number of solutions found, print that solution and return
        if (col == N) {
            solutionsFound++;
            printSolution();
            return;
        }

        //If the above conditions are not met, the method iterates through all the rows of the current column. 
        //For each row, it calls the isValid method to check if placing a king on that position is safe. 
        //If it is safe, the method sets the corresponding element of the chessboard array to 1, representing that a king
        // is placed on that position, and then calls itself recursively with the next column. 
        //If there is not another position to place a king, chessboard[row][col] is setting to 0 - we use backtracking so we are
        //trying to find another place for the king
        //Then the loop continues to the next row, and the process repeats until a solution is found or all possibilities have been checked.

        for (int row = 0; row < N; row++) {
            if (isValid(row, col)) {
                chessboard[row][col] = 1;
                solve(col + 1);
                chessboard[row][col] = 0;
            }
        }
        
    }
}