//Q:  Problem Statement: N-Queens Puzzle
//You are given a chessboard of size N x N, and your task is to place N queens on the board in such a way that no two queens can attack each other.
//A queen can attack another queen if they share the same row, column, or diagonal.
//Write a program that solves the N-Queens puzzle and returns the total number of valid arrangements.
//Additionally, display each valid arrangement on the chessboard.

public class NQueen {
    public static void main(String[] args) { 
        int n = 10; 
        boolean[][] board = new boolean[n][n];
        System.out.println(queens(board, 0)); 
    }

    static int queens(boolean[][] board, int row) { 
        if (row == board.length) { // If all rows are filled, a valid arrangement is found
            display(board); // Display the current arrangement of queens on the board
            System.out.println(); // Move to the next line for better readability
            return 1; // Return 1 to indicate a valid solution has been found
        }

        int count = 0; // Counter to keep track of the number of valid solutions

        // placing the queen and checking for every row and col
        for (int col = 0; col < board.length; col++) {
            // place the queen if it is safe
            if(isSafe(board, row, col)) { // Check if placing a queen at the current position is safe
                board[row][col] = true; // Place the queen on the board
                count += queens(board, row + 1); // Recursively call queens for the next row
                board[row][col] = false; // Backtrack: remove the queen for exploring other possibilities
            }
        }

        return count; // Return the total count of valid solutions
    }

    private static boolean isSafe(boolean[][] board, int row, int col) { // Check if it's safe to place a queen at a given position
        // check vertical row
        for (int i = 0; i < row; i++) { 
            if (board[i][col]) {
                return false; // If there is a queen, it's not safe
            }
        }

        int n = board.length-1;
        int i = row;
        int j = col;
        //check diagonal left 
         while(i>0 && j>0){
            if(board[--i][--j]){
                return false;
            }
        }

        i = row; //resetting i
        j = col; //resetting j
        //check diagonal right
        while(i>0 && j<n){
            if(board[--i][++j]){
                return false;
            }
        }

        return true; // If no issues found, it's safe to place a queen
    }

    private static void display(boolean[][] board) { // Display the current arrangement of queens on the board
        for(boolean[] row : board) {
            for(boolean element : row) {
                if (element) {
                    System.out.print("Q "); 
                } else {
                    System.out.print("X "); 
                }
            }
            System.out.println(); // Move to the next line for the next row
        }
    }
}



//________________________________________________________________________________________________________________________________
Q: https://leetcode.com/problems/n-queens/description/

// Runtime 2ms
// Beats 88.32% of users with Java

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        char[][] board = new char[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                board[i][j] = '.'; 
            }
        }

        queens(board, 0, solutions);
        return solutions;
    }

    private void queens(char[][] board, int row, List<List<String>> solutions){
        if(row == board.length){
            solutions.add(buildSolution(board));
            return;
        }

        for(int col=0; col<board.length; col++){
            if(isSafe(board, row, col)){ //check if placing the queen at current position is safe
                board[row][col] = 'Q'; //if its safe means isSafe function is true then place the queen at current position
                queens(board, row+1, solutions); //recursive call for the next row
                board[row][col] = '.'; //Backtracking: remove the Queen for exploring other possibilities
            }
        }
    }

    private boolean isSafe(char[][] board,int row,int col){
        int n = board.length-1;
        //check vertical
        for(int i = 0; i<row; i++){
            if(board[i][col] == 'Q'){
                return false;
            }
        }

        int i = row;
        int j = col;
        //check left diagonal
        while(i>0 && j>0){
            if(board[--i][--j] == 'Q'){
                return false;
            }
        }

        i = row; //resetting i 
        j = col; //resetting j 
        //check right diagonal
        while(i>0 && j<n){
            if(board[--i][++j] == 'Q'){
                return false;
            }
        }
        return true;
    }

    private List<String> buildSolution(char[][] board){
        List<String> solution = new ArrayList<>();
        for(char[] row: board){ //iterate the char[][] board 
            solution.add(new String(row)); //here converting each row into the string and adding in the solutions list 
        } 
        return solution;
    }
}


//_____________________________________________________________________________________________________________________________________________________
Q: https://leetcode.com/problems/n-queens-ii/description/

// Runtime 2ms
// Beats 69.73% of users with Java


class Solution {
    public int totalNQueens(int n) {
        boolean[][] board = new boolean[n][n];
        return queens(board, 0);
    }

    static int queens(boolean[][] board, int row) { 
        if (row == board.length) { // If all rows are filled, a valid arrangement is found
            return 1; // Return 1 to indicate a valid solution has been found
        }

        int count = 0; // Counter to keep track of the number of valid solutions

        // placing the queen and checking for every row and col
        for (int col = 0; col < board.length; col++) {
            // place the queen if it is safe
            if(isSafe(board, row, col)) { // Check if placing a queen at the current position is safe
                board[row][col] = true; // Place the queen on the board
                count += queens(board, row + 1); // Recursively call queens for the next row
                board[row][col] = false; // Backtrack: remove the queen for exploring other possibilities
            }
        }

        return count; // Return the total count of valid solutions
    }

    private static boolean isSafe(boolean[][] board, int row, int col) { // Check if it's safe to place a queen at a given position
        // check vertical row
        for (int i = 0; i < row; i++) { // Check the column to ensure no queen in the same column in previous rows
            if (board[i][col]) {
                return false; // If there is a queen, it's not safe
            }
        }

        int n = board.length-1;
        int i = row;
        int j = col;
        //check diagonal left 
         while(i>0 && j>0){
            if(board[--i][--j]){
                return false;
            }
        }

        i = row; //resetting i
        j = col; //resetting j
        //check diagonal right
        while(i>0 && j<n){
            if(board[--i][++j]){
                return false;
            }
        }

        return true; // If no issues found, it's safe to place a queen
    }
}


//________________________________________________________________________________________________________________________________________________________________
// Problem Statement:
// You are given an empty N x N chessboard. The task is to place N knights on the board in such a way that no two knights can attack each other.
// A knight can attack another knight if it is placed in one of the squares where it can move according to the standard rules of chess.
// Specifically, a knight can attack another knight if it is positioned two squares horizontally and one square vertically away,
// or two squares vertically and one square horizontally away.
public class NKnights {
    public static void main(String[] args) {
        int n = 4;
        boolean[][] board = new boolean[n][n];
        knight(board, 0, 0, n);
    }

    static void knight(boolean[][] board, int row, int col, int knights) {
        if (knights == 0) { // if knights becomes zero means all the required knights are placed at safe position
            display(board); // display that positions in matrix
            System.out.println();
            return;
        }

        if (row == board.length -1 && col == board.length) {//The condition is checking if the current position is at the bottom-right corner of the board.
                                                           // If this condition is true,it means that the entire board has been traversed, and the function
                                                           // returns without further recursive calls.
            return; 
        }

        if (col == board.length) { 
            knight(board, row + 1, 0, knights);
            return;
        }

        if (isSafe(board, row, col)) {
            board[row][col] = true;
            knight(board, row, col + 1, knights - 1); //recursive call for the next col
            board[row][col] = false; //backtracking: remove the knights for exploring other possibilities
        }

        knight(board, row, col + 1, knights);
    }

    private static boolean isSafe(boolean[][] board, int row, int col) {
        // this will check 2 steps up then 1 step left
        if (isValid(board, row - 2, col - 1)) {
            if (board[row - 2][col - 1]) {
                return false;
            }
        }

        // this will check 1 step up then 2 steps left
        if (isValid(board, row - 1, col - 2)) {
            if (board[row - 1][col - 2]) {
                return false;
            }
        }

        // this will check 2 steps up then one step right
        if (isValid(board, row - 2, col + 1)) {
            if (board[row - 2][col + 1]) {
                return false;
            }
        }

        //this will check 1 step up then 2 step right
        if (isValid(board, row - 1, col + 2)) {
            if (board[row - 1][col + 2]) {
                return false;
            }
        }
        // not writing here for the down check because traversing from up to down
        // through recursive so we know there are no knights down there so thats why no need to check

        // if reached here means the place is safe so place the knight at current position
        return true;
    }

    //this function will make sure that check happens in the bounds
    static boolean isValid(boolean[][] board, int row, int col) {
        if (row >= 0 && row < board.length && col >= 0 && col < board.length) {
            return true; 
        }
        return false;
    }

    private static void display(boolean[][] board) {
        for(boolean[] row : board) {
            for(boolean element : row) {
                if (element) {
                    System.out.print("K ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }
}



//_______________________________________________________________________________________________________________________________
Q: https://leetcode.com/problems/check-knight-tour-configuration/description/

// Runtime 1ms
// Beats 100.00% of users with Java

class Solution {
    public boolean checkValidGrid(int[][] grid) {
        if(grid[0][0] != 0){
            return false;
        }
        return check(grid,0,0,0);
    }

    private boolean check(int[][] grid, int row, int col, int num){
        int n = grid.length;
        if(grid[row][col] == n*n-1){
            return true;
        }
        int i;
        int j;

        //2 steps up then 1 left
        i = row-2;
        j = col-1;
        if(i>=0 && j>=0){
            if(grid[i][j] == num+1){
                return check(grid, i, j, num+1);
            }
        }

        //2 steps up then 1 step right
        i = row-2;
        j = col+1;
        if(i>=0 && j<n){
             if(grid[i][j] == num+1){
                return check(grid, i, j, num+1);
            }
        }

        //1 step up 2 steps left
        i = row-1;
        j = col-2;
        if(i>=0 && j>=0){
             if(grid[i][j] == num+1){
                return check(grid, i, j, num+1);
            }
        }

        //1 step up 2 steps right
        i = row-1;
        j = col+2;
        if(i>=0 && j<n){
             if(grid[i][j] == num+1){
                return check(grid, i, j, num+1);
            }
        }

        //2 steps down 1 step left
        i = row+2;
        j = col-1;
        if(i<n && j>=0){
            if(grid[i][j] == num+1){
                return check(grid, i, j, num+1);
            }
        }

        //2 steps down 1 step right
        i = row+2;
        j = col+1;
        if(i<n && j<n){
            if(grid[i][j] == num+1){
                return check(grid, i, j, num+1);
            }
        } 

        //1 step down 2 steps left
        i = row + 1;
        j = col - 2;
        if(i<n && j>=0){
            if(grid[i][j] == num+1){
                return check(grid, i, j, num+1);
            }
        }

        //1 step down 2 steps right
        i = row + 1;
        j = col + 2;
        if(i<n && j<n){
            if(grid[i][j] == num+1){
                return check(grid, i, j, num+1);
            }
        }  
        return false;
    }
}

//_____________________________________________________________________________________________________________________________________________________
//Q: https://leetcode.com/problems/valid-sudoku/submissions/1125923913/

// Runtime 1ms
// Beats 100.00% of users with Java

class Solution {
    public boolean isValidSudoku(char[][] board) {
        for(int i=0; i<9; i++){   // Iterate through each cell in the board
            for(int j=0; j<9;j++){
                
                if(board[i][j] == '.'){   //If cell is empty move to next cell
                    continue;
                }
                char num = board[i][j];// Save the current value in the cell 
                board[i][j] = '.';   //temporarily empty it for validation
                if(isValid(board,i,j,num) == false){  // // Check if placing 'num' in the current cell is valid
                    return false;
                }
                board[i][j] = num; //Backtracking: restore the original value in the cell
            }            
        }
        // If all cells are valid, the Sudoku board is valid
        return true;
    }

    public boolean isValid(char[][] board, int row, int col, int num){
        //check vertical
        for(int i=0; i<9; i++){
            if(board[i][col] == num){
                return false;
            }
        }

        //check horizontal
        for(int j=0; j<9; j++){
            if(board[row][j] == num){
                if(board[row][j] == num){
                    return false;
                }
            }
        }

        //check 3x3 subgrid
        int sRow = row/3*3;
        int sCol = col/3*3;
        for(int i=sRow; i<sRow+3; i++){
            for(int j=sCol; j<sCol+3; j++){
                if(board[i][j] == num){
                    return false;
                }
            }
        }

        return true;
    }
}