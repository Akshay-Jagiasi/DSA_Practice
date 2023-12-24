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
//Q: https://leetcode.com/problems/valid-sudoku/description/

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
                if(isValid(board,i,j,num) == false){  // Check if placing 'num' in the current cell is valid
                    return false;
                }
                board[i][j] = num; //restore the original value in the cell
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

//___________________________________________________________________________________________________________________________________________________
//Q: https://leetcode.com/problems/sudoku-solver/description/

// Runtime 6ms
// Beats 76.84% of users with Java

class Solution {
    static int check = 0;
    public void solveSudoku(char[][] board) {
        solve(board,0,0);
        check = 0;
    }

    public void solve(char[][] board,int row,int col){
        if(row == 9){// Base case: If we have reached the end of the board (row 9)
            check = 1;// set check to 1 and return
            return;
        }
        if(board[row][col] != '.'){
            if(col != 8){
                solve(board, row, col+1);
            }else{
                solve(board, row+1, 0);
            }
        }else{
            // Try placing numbers from '1' to '9' in the empty cell
            for(char ch='1'; ch<='9'; ch++){
                if(isValid(board,row,col,ch)){
                    board[row][col] = ch;
                    if(col != 8){
                        solve(board, row, col+1);
                    }else{
                        solve(board, row+1, 0);
                    }
                    if(check == 1){
                        return;
                    }
                    // If placing 'ch' didn't lead to a solution, backtrack by resetting the cell to '.'
                    board[row][col] = '.'; //backtracking
                }
            }
        }
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


//__________________________________________________________________________________________________________________________________________________
//Q: Design a singly linked list class (LL) to perform the following operations:

// Insert Operations:
// insertFirst(int value): Inserts a new node with the given value at the beginning of the linked list.
// insertLast(int value): Inserts a new node with the given value at the end of the linked list.
// insertAtPosition(int value, int index): Inserts a new node with the given value at the specified index in the linked list.

// Delete Operations:
// deleteFirst(): Removes the first node from the linked list.
// deleteLast(): Removes the last node from the linked list.
// deleteAtIndex(int index): Removes the node at the specified index from the linked list.

// Search Operation:
// find(int value): Searches for the first occurrence of the given value in the linked list. If found, return the index; otherwise, return -1.

// Display and Size:
// display(): Prints the elements of the linked list.
// size(): Returns the size of the linked list.

package LinkedList;

public class LL{

    private Node head;
    private Node tail;
    private int size;

    public void insertFirst(int value){
        Node node = new Node(value);
        node.next = head;
        if(tail == null){
            tail = node;
        }
        head = node;
        size++;
    }

    public void insertLast(int value){
        Node node = new Node(value);
        if(tail == null){
            insertFirst(value);
            return;
        }
        tail.next = node;
        tail = node;
        size++;
    }

    public void insertAtPosition(int value, int index){
        if(index == 0){
            insertFirst(value);
            return;
        }
        Node temp = head;
        for(int i=1; i < index; i++){
            temp = temp.next;
        }
        Node node = new Node(value, temp.next);
        temp.next = node;
        size++;
    }
    
    public void deleteFirst(){
        if(head == null){
            System.out.println("Nothing to delete");
            return;
        }
        int value = head.value;
        head = head.next;
        size--;
        System.out.println("First value is removed and the value is: "+value);
    }

    public void deleteLast(){
        if(head == null){
            System.out.println("Nothing to delete");
            return;
        }
        if(size <= 1){
            deleteFirst();
        }
        Node secondLast = get(size-2);
        int val = tail.value;
        tail = secondLast;
        tail.next = null;
        size--;
        System.out.println("Last value is removed and the value is: "+val);
    }

    public void deleteAtIndex(int index){
        if(index == 0){
            deleteFirst();
        }
        if(index == size-1){
            deleteLast();
        }

        Node prev = get(index-1);
        int val = prev.next.value;
        prev.next = prev.next.next;
        size--;
        System.out.println("Value at index "+index+" is removed and the value is: "+val);
    }

    public void find(int value) {
        Node node = head;
        int index = 0;
    
        while (node != null) {
            if (node.value == value) {
                System.out.println("Value " + value + " found at index " + index);
                return;
            }
            node = node.next;
            index++;
        }
        System.out.println("Value " + value + " not found in the list");
    }
    

    public Node get(int index){
        Node node = head;
        for(int i=0; i<index; i++){
            node = node. next;
        }
        return node;
    }

    public void display(){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.value + "->");
            temp = temp.next;
        }
        System.out.print("end");
        System.out.println();
    }

    public void size(){
        System.out.println("size of the LinkedList is: " + size);
    }

    class Node{
        private int value;
        private Node next;

        public Node (int value){
            this.value = value;
        }

        public Node (int value, Node next){
            this.value = value;
            this.next = next;
        }

    }   
}


//_____________________________________________________________________________________________________________________________________________________________________
//Q: Problem Description: Design a Java class DLL to perform operations on a doubly linked list. The class should support the following operations:

// Insert Operations:
// insertFirst(int value): Inserts a new node with the given value at the beginning of the doubly linked list.
// insertLast(int value): Inserts a new node with the given value at the end of the doubly linked list.
// insertAtIndex(int value, int index): Inserts a new node with the given value at the specified index in the doubly linked list.

// Search Operation:
// find(int value): Searches for the first occurrence of the given value in the doubly linked list. If found, return the index; otherwise, return -1.

// Insert After Value Operation:
// insertAfterValue(int afterValue, int actualValue): Inserts a new node with the specified value (actualValue) immediately after the first occurrence of a node with the given value (afterValue). If the value is not found, no insertion is performed.

// Delete Operations:
// deleteFirst(): Removes the first node from the doubly linked list.
// deleteLast(): Removes the last node from the doubly linked list.
// deleteAtIndex(int index): Removes the node at the specified index from the doubly linked list.

// Display Operations:
// display(): Prints the elements of the doubly linked list from the beginning to the end.
// displayReverse(): Prints the elements of the doubly linked list in reverse order.

// Size Operation:
// size(): Returns the size of the doubly linked list.
// The class should handle edge cases, such as deleting from an empty list or attempting to insert after a value that is not present in the list.

package LinkedList;

public class DLL {
    private Node head;
    private Node tail;
    private int size;

    public void insertFirst(int value){
        Node node = new Node(value);
        node.next = head;
        node.prev = null;
        if(head != null){
            head.prev = node;
        }
        if(tail == null){
            tail = node;
        }
        head = node;
        size++;
    }

    public void insertLast(int value){
        Node node = new Node(value);
        if(tail == null){
            insertFirst(value);
            return;
        }
        tail.next = node;
        node.next = null;
        node.prev = tail;
        tail = node;
        size++;
    }

    public void insertAtIndex(int value, int index){
        if(index == 0){
            insertFirst(value);
            return;
        }
        Node temp = head;
        for(int i=1; i<index; i++){
            temp = temp.next;
        }
        Node node = new Node(value);
        node.next = temp.next;
        node.prev = temp;
        if(temp.next != null){
            temp.next.prev = node;
        }
        temp.next = node;
        size++;
    }

    //Inserts a new node with the specified value (actualValue) immediately after the first occurrence of a node with the given value (afterValue).
    //If the value is not found, no insertion is performed.
    //afterValue: The value after which the new node should be inserted.
    //actualValue: The value to be inserted after the node with afterValue.
    public void insertAfterValue(int afterValue, int actualValue) {
        // Check if the list is empty
        if (head == null) {
            System.out.println("Error: Cannot insert after value in an empty list.");
            return;
        }
        // Find the index of the node with the specified value
        int index = find(afterValue);
        if (index != -1) {
            // Call insertAtIndex to insert the actualValue after the found node
            // Increment the index by 1 to insert after the node, not before
            insertAtIndex(actualValue, index + 1);
        }
    }
    
    public int find(int value) {
        Node node = head;
        int index = 0;
    
        while (node != null) {
            if (node.value == value) {
                return index;
            }
            node = node.next;
            index++;
        }
        return -1;
    }

    public void deleteFirst(){
        if(head == null){
            System.out.println("Nothing to delete");
            return;
        }
        int value = head.value;
        if(head == tail){
            head = tail = null;
        }else{
            head = head.next;
            head.prev = null;
        }
        size--;
        System.out.println("Deleted value: "+value);
    }

    public void deleteLast(){
       if(head == null){
            System.out.println("Nothing to delete");
            return;
        }
        int value = tail.value;
        if(head == tail){
            head = tail = null;
        }else{
            tail = tail.prev;
            tail.next = null; 
        }
        size--;
        System.out.println("Deleted value: "+value);
    }

    public void deleteAtIndex(int index){
        if(index == 0){
            deleteFirst();
            return;
        }
        if(index == size-1){
            deleteLast();
            return;
        }
        Node temp = head;
        for(int i=0; i<index-1; i++){
            temp = temp.next;
        }
        temp.next = temp.next.next;
        temp.next.prev = temp;
    }

    public void display(){
        Node temp = head;

        while(temp != null){
            System.out.print(temp.value + "->");
            temp = temp.next;
        }
        System.out.print("end ");
    }

    public void displayReverse(){
        Node temp2 = tail;
        while(temp2 != null){
            System.out.print(temp2.value + "->");
            temp2 = temp2.prev;
        }
        System.out.print("start");
        System.out.println();
    }
    public void size(){
        System.out.println("The size of the list is "+size);
    }

    public class Node{
        private int value;
        private Node next;
        private Node prev;

        public Node(int value){
            this.value = value;
        }
    }
}


//____________________________________________________________________________________________________________________________________________________________________
//Q: Problem Description:Design a Java class CLL to perform operations on a circular linked list. The class should support the following operations:

// Insert Operation:
// insert(int value): Inserts a new node with the given value into the circular linked list. If the list is empty, the new node becomes both the head and the tail.

// Delete Operation:
// delete(int value): Deletes the first occurrence of a node with the given value from the circular linked list. If the value is found, remove the node; otherwise, no action is taken.

// Display Operation
// display(): Prints the elements of the circular linked list.
// The class should handle edge cases, such as deleting from an empty list.



package LinkedList;

public class CLL {
    private Node head;
    private Node tail;

    public void insert(int value){
        Node node = new Node(value);
        if(head == null){
            head = node;
            tail = node;
            return; 
        }
        node.next = head;
        tail.next = node;
        tail = node;
    }

    public void delete(int value){
        if(head.value == value){
            tail.next = head.next;
            head = head.next; // Update head
            return;
        }
    
        Node temp = head;
        while(temp.next != head){
            if(temp.next.value == value){
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
    }

    public void display(){
        if(head == null){
            System.out.println("Nothing to display");
        }
        Node temp = head;
        do{
            System.out.print(temp.value + "->");
            temp = temp.next;
        }while(temp != head);
        System.out.println();
    }

    public class Node{
        private int value;
        private Node next;

        public Node(int value){
            this.value = value;
        }
    }
}