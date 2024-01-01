// Problem Statement: Tic-Tac-Toe Game
// Implement Tic-Tac-Toe game in Java. The game allows two players to take turns making moves on a 3x3 game board until one player
// wins or the game ends in a draw.

// Game Rules:
// The game is played on a 3x3 grid.
// Players take turns making moves.
// Player 1 is 'X', and Player 2 is 'O'.
// The game ends when a player forms a horizontal, vertical, or diagonal line of their symbol.
// If the entire grid is filled without a winner, the game is a draw.

import java.util.*;

public class TicTacToe {

    private static char[][] board = {
            {' ',' ',' '},
            {' ',' ',' '},
            {' ',' ',' '}
        };

    private static char currentPlayer = 'X';
    public static void main(String[] args) {
        playGame();
    }

    public static void playGame(){
        while(true){
            display(board);
            playerMove();

            if(isWinner()){
                display(board);
                System.out.println(currentPlayer + "is winner");
                break;
            }

            if(isFull()){
                display(board);
                System.out.println("Draw");
                break;
            }
            
            switchPlayer();
        }
    }

    public static void display(char[][] board){
        for(int row=0; row<board.length; row++){
            for(int col=0; col<board[row].length; col++){
                System.out.print("| " + board[row][col] + " ");
            }
            System.out.println("|");
        }
    }

    public static void playerMove(){
        Scanner scanner = new Scanner(System.in);
        int row;
        int col;
        while(true){
            System.out.println("player "+currentPlayer+" enter your row and column: ");
            row = scanner.nextInt()-1;
            col = scanner.nextInt()-1;
            if(isValidMove(row,col)){
                board[row][col] = currentPlayer;
                break;
            }else{
                System.out.println("Invalid move");
            }
        }
    }

    public static boolean isValidMove(int row, int col){
        return row >= 0 && row <3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    public static boolean isWinner(){
        return checkRow() || checkCol() || checkDiagonal();
    }

    public static boolean checkRow(){
        for(int i=0; i<3; i++){
            if(board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer){
                return true;
            }
        }
        return false;
    }

    public static boolean checkCol(){
        for(int i=0; i<3; i++){
            if(board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer){
                return true;
            }
        }
        return false;
    }

    public static boolean checkDiagonal(){
        if(board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer){
            return true;
        }else if(board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer){
            return true;
        }
        return false;
    }

    public static boolean isFull(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board[i][j] == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    public static void switchPlayer(){
        if(currentPlayer == 'X'){
            currentPlayer = 'O';
        }else{
            currentPlayer = 'X';
        }
    }
}

//_________________________________________________________________________________________________________________________________
// Problem Statement: Tic-Tac-Toe of Variable Size
// You are tasked with enhancing the traditional Tic-Tac-Toe game to support variable-sized game boards.
// The program should allow two players to take turns making moves on an NxN grid until one player wins or the game ends in a draw.

// Game Rules:
// The game is played on an NxN grid, where N is provided by the user.
// Players take turns making moves.
// Player 1 is 'X', and Player 2 is 'O'.
// The game ends when a player forms a horizontal, vertical, or diagonal line of their symbol.
// If the entire grid is filled without a winner, the game is a draw.

import java.util.*;

public class TicTacToeNSize {

    private static char[][] board;
    private static int n;
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of grid: ");
        n = scanner.nextInt();
        board = new char[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                board[i][j] = ' ';
            }
        }
        playGame();
    }

    public static void playGame(){
        while(true){
            display(board);
            playerMove();

            if(isWinner()){
                display(board);
                System.out.println(currentPlayer + "is winner");
                break;
            }

            if(isFull()){
                display(board);
                System.out.println("draw");
                break;
            }

            switchPlayer();
        }
    }
    
    public static void display(char[][] board) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.println("|");
        }
    }
    
    

    public static void playerMove(){
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("player "+currentPlayer+" enter your row and column: ");
            int row = scanner.nextInt()-1;
            int col = scanner.nextInt()-1;
            if(isValid(row, col)){
                board[row][col] = currentPlayer;
                break;
            }else{
                System.out.println("Invalid move");
            }
        }
    }

    public static boolean isValid(int row, int col){
        return row>=0 && row<n && col>=0 && col<n && board[row][col] == ' ';
    }

    public static boolean isWinner(){
        if(checkRow() || checkCol() || checkDiagonal()){
            return true;
        }
        return false;
    }

    public static boolean checkRow(){
        for(int i=0; i<n; i++){
            boolean win = true;
            for(int j=0; j<n; j++){
                if(board[i][j] != currentPlayer){
                    win = false;
                    break;
                } 
            }
            if(win){
                return true;
            }
        }
        return false;
    }

    public static boolean checkCol(){
        for(int i=0; i<n; i++){
            boolean win = true;
            for(int j=0; j<n; j++){
                if(board[j][i] != currentPlayer){
                    win = false;
                    break;
                } 
            }
            if(win){
                return true;
            }
        }
        return false;
    }

    public static boolean checkDiagonal(){
        boolean win1 = true;
        boolean win2 = true;
        for(int i=0; i<n; i++){
            if(board[i][i] != currentPlayer){
                win1 = false;
            }
            if(board[i][n-1-i] != currentPlayer){
                win2 = false;
            }
        }
        return win1 || win2;
    }

    public static boolean isFull(){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(board[i][j] == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    public static void switchPlayer(){
        if(currentPlayer == 'X'){
            currentPlayer = 'O';
        }else{
            currentPlayer = 'X';
        }
    }
}



//________________________________________________________________________________________________________________________________
//Problem Statement: Tic-Tac-Toe AI Game
//Create Tic-Tac-Toe game where a human player ('X') competes against an AI opponent ('O'). The game is played on a 3x3 grid.
//Design and implement the game loop, allowing players to make moves in turns.
// Implement a functional AI strategy adhering to the specified rules.
// Display the current state of the board after each move.
// Clearly communicate game outcomes, including when a player wins, the game ends in a draw, or an invalid move is attempted.
// Organize your code into functions for better readability and maintainability.
// Include comments to explain the logic and functionality of your code.

import java.util.*;
public class TicTacToeAI {
    private static char[][] board = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };

    private static char userSymbol = 'X';
    private static char aiSymbol = 'O';

    public static void main(String[] args) {
        playGame();
    }

    public static void playGame(){
        display(board);
        while(true){
            userMove();
            if(isGameFinished()) break;
            aiMove();
            if(isGameFinished()) break;
        }
    } 

    public static void display(char[][] board){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.println("|");
        }
    }

    public static void userMove(){
        System.out.println("your turn Enter your move(row and column): ");
        Scanner scanner = new Scanner(System.in);

        while(true){
            int row = scanner.nextInt()-1;
            int col = scanner.nextInt()-1;
            if(isValidMove(row, col)){
                board[row][col] = userSymbol;
                break;
            }else{
                System.out.println("Invalid Move. Try again");
            }
        }
        display(board);
    }

    public static void aiMove(){
        int bestMove[] = findBestMove();
        int row = bestMove[0];
        int col = bestMove[1];
        board[row][col] = aiSymbol;
        System.out.println("AI move");
        display(board);
    }

    public static int[] findBestMove(){

        // Check for winning moves for AI in each row
        for(int i=0; i<3; i++){
            // If there are two 'O's (AI's symbol) in a row, indicating a potential win,
            if(checkRow(i, aiSymbol) == 2){
                for(int j=0; j<3; j++){
                    if (board[i][j] == ' ') {
                        return new int[]{i, j};
                    }
                }
            }
        }

        // Check for winning moves for AI in each col   
        for(int i=0; i<3; i++){
            // If there are two 'O's (AI's symbol) in a col, indicating a potential win,
            if(checkCol(i, aiSymbol) == 2){
                for(int j=0; j<3; j++){
                    if (board[j][i] == ' ') {
                        return new int[]{j, i};
                    }
                }
            }
        }

        //Check for winning moves for AI in diagonal left to right 
        if(checkDiagonal(0, aiSymbol) == 2){
             for(int i=0; i<3; i++){
                if(board[i][i] == ' '){
                    return new int[]{i,i};
                }
             } 
        }

        //Check for winning moves for AI in diagonal right to left
        if(checkDiagonal(1, aiSymbol) == 2){
             for(int i=0; i<3; i++){
                if(board[i][2-i] == ' '){
                    return new int[]{i,2-i};
                }
             } 
        }

         // Block user from winning by checking user's potential winning moves
        // Check rows for potential user winning moves
        for(int i=0; i<3; i++){
            // If there are two 'X's (user's symbol) in a row, consider blocking the third move
            if(checkRow(i, userSymbol) == 2){
                for(int j=0; j<3; j++){
                    if (board[i][j] == ' ') {
                        return new int[]{i, j};
                    }
                }
            }
        }

        // Block user from winning by checking user's potential winning moves
        // Check columns for potential user winning moves
        for(int i=0; i<3; i++){
            // If there are two 'X's (user's symbol) in a col, consider blocking the third move
            if(checkCol(i, userSymbol) == 2){
                for(int j=0; j<3; j++){
                    if (board[j][i] == ' ') {
                        return new int[]{j, i};
                    }
                }
            }
        }

         // Block user from winning by checking user's potential winning moves
        // Check diagonal left to right for potential user winning moves
        if(checkDiagonal(0, userSymbol) == 2){
             for(int i=0; i<3; i++){
                if(board[i][i] == ' '){
                    return new int[]{i,i};
                }
             } 
        }

         // Block user from winning by checking user's potential winning moves
        // Check diagonal right to left for potential user winning moves
        if(checkDiagonal(1, userSymbol) == 2){
             for(int i=0; i<3; i++){
                if(board[i][2-i] == ' '){
                    return new int[]{i,2-i};
                }
            } 
        }

        // If no winning or blocking move for AI is found in rows, columns, or diagonals,
        // the AI chooses a random move to play on the board.
        Random random = new Random();
        int row, col;
        while(true){
            row = random.nextInt(3);
            col = random.nextInt(3);

            if(isValidMove(row, col)){
                return new int[]{row, col};
            }
        }
    }
    // Counts the occurrences of a symbol in a specified row.
    public static int checkRow(int row, char symbol){
        int count = 0;
        for(int j=0; j<3; j++){
            if(board[row][j] == symbol){
                count++;
            }
        }
        return count;
    }
    // Counts the occurrences of a symbol in a specified col.
    public static int checkCol(int col, char symbol){
        int count = 0;
        for(int i=0; i<3; i++){
            if(board[i][col] == symbol){
                count++;
            }
        }
        return count;
    }
    // Counts the occurrences of a symbol in a specified diagonal.
    public static int checkDiagonal(int diagonal, char symbol) {
        int count = 0;
        for (int i=0; i<3; i++) {
            if (diagonal == 0 && board[i][i] == symbol) {
                count++;
            }
            if (diagonal == 1 && board[i][2 - i] == symbol) {
                count++;
            }
        }
        return count;
    }

    public static boolean isGameFinished(){
        if(checkWinner(userSymbol)){
            System.out.println("--------Congratulations You won--------");
            return true;
        }else if(checkWinner(aiSymbol)){
            System.out.println("--------AI won--------");
            return true;
        }else if(isBoardFull()){
            System.out.println("--------Draw--------");
            return true;
        }
        return false;
    }

    public static boolean isValidMove(int row, int col){
        return row>=0 && row<3 && col>=0 && col<3 && board[row][col] == ' ';
    }

    public static boolean isBoardFull(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board[i][j] == ' '){
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean checkWinner(char symbol){
        //check row
        for(int i=0; i<3; i++){
            if(board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol){
                return true;
            }
        }

        //check col
        for(int i=0; i<3; i++){
            if(board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol){
                return true;
            }
        }

        //check diagonal
        if(board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol){
            return true;
        }else if(board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol){
            return true;
        }
        return false;
    }
}



//________________________________________________________________________________________________________________________________
// Problem Statement: Simple Integer Stack Implementation
// You are required to implement a simple integer stack in Java. The stack should support basic operations like push, pop, peek, and
// provide information about whether it's full or empty.

package STACK_QUEUE;
import java.util.*;
public class Stack {
    protected int[] data;
    private static final int DEFAULT_SIZE = 10;

    int ptr = -1;

    public Stack(){
        this(DEFAULT_SIZE);
    }

    public Stack(int size){
        data = new int[size];
    }

    public boolean push(int item){
        if(isFull()){
            System.out.println("Stack is full");
            return false;
        }
        ptr++;
        data[ptr] = item;
        return true;
    }

    public int pop(){
        if(isEmpty()){
            System.out.println("Stack is empty nothing to top");
        }
        int removedItem = data[ptr];
        ptr--;
        return removedItem;
    }

    public int peek(){
        if(isEmpty()){
            return -1;
        }
        return data[ptr];
    }

    public boolean isFull(){
        return ptr == data.length-1;
    }

    public boolean isEmpty(){
        return ptr == -1;
    }
}


//________________________________________________________________________________________________________________________________
// Extended Problem Statement: Dynamic Integer Stack Implementation
// Building upon the previous problem statement for a simple integer stack, you are now required to implement a dynamic integer stack.
// This new stack should automatically resize itself when attempting to push an element onto a full stack.

package STACK_QUEUE;

public class DynamicStack extends Stack{

    public DynamicStack(){
        super();
    }

    public DynamicStack(int size){
        super(size);
    }

    @Override
    public boolean push(int item){
        //this take care of it being full
        if(this.isFull()){
            int[] temp = new int[data.length*2];
            //copy all previous items in new data;
            for(int i=0; i<data.length; i++){
                temp[i] = data[i];
            }

            data = temp;
        }
        //at this point we know that there is space in the array
        //insert item
        super.push(item);
        return true;
    }
}

//________________________________________________________________________________________________________________________________
// Problem Statement: Simple Integer Queue Implementation
// You are tasked with implementing a simple integer queue in Java. The queue should support basic operations like enqueue, dequeue, 
// and provide information about whether it's full or empty.

package STACK_QUEUE;

public class Queue {
    private int[] data;
    private static final int DEFAULT_SIZE = 5;
    int end = -1;
    
    public Queue(){
        this(DEFAULT_SIZE);
    }

    public Queue(int size){
        data = new int[size];
    }

    public boolean enQueue(int item){
        if(isFull()){
            System.out.println("Queue full");
        }
        end++;
        data[end] = item;
        return true;
    }

    public int deQueue(){
        if(isEmpty()){
            System.out.println("Queue is empty");
        }
        int removed = data[0];
        for(int i=1; i<=end; i++){
            data[i-1] = data[i];
        }
        end--;
        return removed;
    }

    public void display(){
        for(int i=0; i<=end; i++){
            System.out.print(data[i]+ "<-");
        }
        System.out.println();
    }

    public boolean isFull(){
        return end == data.length-1;
    }

    public boolean isEmpty(){
        return end == -1;
    }
}

//________________________________________________________________________________________________________________________________
// Problem Statement: Circular Queue Implementation
// You are assigned the task of implementing a circular queue in Java. The circular queue should support basic operations
// like insertion, removal, and provide information about whether it's full or empty. Additionally, it should allow retrieving
// the front element without removing it.

package STACK_QUEUE;

public class CircularQueue {
    protected int[] data;
    private static final int DEFAULT_SIZE = 10;

    protected int end = 0;
    protected int front = 0;
    private int size = 0;

    public CircularQueue(){
        this(DEFAULT_SIZE);
    }

    public CircularQueue(int size) {
        this.data = new int[size];
    }

    public boolean isFull() {
        return size == data.length; // ptr is at last index
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean insert(int item) {
        if (isFull()) {
            return false;
        }
        data[end++] = item;
        end = end % data.length;
        size++;
        return true;
    }

    public int remove(){
        if (isEmpty()) {
            System.out.println("Queue is empty");
        }

        int removed = data[front++];
        front = front % data.length;
        size--;
        return removed;
    }

    public int front() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
        }
        return data[front];
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Empty");
            return;
        }
        int i = front;
        do {
            System.out.print(data[i] + " -> ");
            i++;
            i %= data.length;
        } while (i != end);
        System.out.println("END");
    }
}
//________________________________________________________________________________________________________________________________
// Problem Statement: Dynamic Queue Implementation
// You are tasked with extending the functionality of a circular queue to create a dynamic queue that automatically adjusts
// its size when it reaches capacity. This dynamic queue should inherit from the CircularQueue class and include additional methods
// to demonstrate its dynamic resizing capability.

package STACK_QUEUE;

public class DynamicQueue extends CircularQueue {
    public DynamicQueue(){
        super();
    }

    public DynamicQueue(int size){
        super(size);
    }

    @Override
    public boolean insert(int item){
        if(this.isFull()){
            int[] temp = new int[data.length*2];
            //copy all previous items in new data;
            for(int i=0; i<data.length; i++){
                temp[i] = data[(front+i)%data.length];
            }
            front = 0;
            end = data.length;
            data = temp;
        }
        super.insert(item);
        return true;
    }
}

//________________________________________________________________________________________________________________________________
//https://www.hackerrank.com/challenges/game-of-two-stacks/problem

import java.util.*;

class TwoStackGame {
  static int twoStacks(int x, int[] a, int[] b) {
    return twoStacks(x, a, b, 0, 0) - 1;
  }

  private static int twoStacks(int x, int[] a, int[] b, int sum, int count) {
    if (sum > x) {
      return count;
    }

    if (a.length == 0 || b.length == 0) {
      return count;
    }

    int ans1 = twoStacks(x, Arrays.copyOfRange(a, 1, a.length), b, sum + a[0], count + 1);
    int ans2 = twoStacks(x, a, Arrays.copyOfRange(b, 1, b.length), sum + a[0], count + 1);

    return Math.max(ans1, ans2);
  }

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int t = s.nextInt();
    for (int i = 0; i < t; i++) {
      int n = s.nextInt();
      int m = s.nextInt();
      int x = s.nextInt();
      int[] a = new int[n];
      int[] b = new int[m];
      for (int j = 0; j < n; j++) {
        a[j] = s.nextInt();
      }
      for (int j = 0; j < m; j++) {
        b[j] = s.nextInt();
      }
      System.out.println(twoStacks(x, a, b));
    }
  }

}
//________________________________________________________________________________________________________________________________
//Q: https://leetcode.com/problems/linked-list-cycle/description/
// Runtime 0ms
// Beats 100.00% of users with Java

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

 // Whenever a linked list cycle or array cycle question arises, always try this fast and slow 
 // approach because in this approach, there is the slow pointer that will move one step, and the 
 // fast pointer will move two steps. If there is a cycle, they will definitely meet.
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}

//  Calculates the length of the cycle in a linked list if a cycle exists.
//    return The length of the cycle, or 0 if no cycle is present
public int LengthCycle(ListNode head){
    ListNode fast = head;
    ListNode slow = head;
    while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                calculates the length of the cycle
                ListNode temp = slow;
                int length = 0;
                do{
                    temp = temp.next;
                    length++;
                }while(temp != slow);
                return length;
            }
        }
        return 0;
    }
}




//________________________________________________________________________________________________________________________________
//Q: https://leetcode.com/problems/linked-list-cycle-ii/description/

// Runtime 0ms
// Beats 100.00% of users with Java

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode entry = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                while(slow != entry){
                    slow = slow.next;
                    entry = entry.next;
                }
                return entry;
            }
        }
        return null;
    }
}
//________________________________________________________________________________________________________________________________
//Q: https://leetcode.com/problems/happy-number/description/

// Runtime 1ms
// Beats 81.06% of users with Java

class Solution {
   public boolean isHappy(int n) {
        int slow = n;
        int fast = n;

        do {
            slow = findSquare(slow);
            fast = findSquare(findSquare(fast));
        } while (slow != fast);

        if (slow == 1) {
            return true;
        }
        return false;
    }
    private int findSquare(int number) {
        int ans = 0;
        while (number > 0) {
            int rem = number % 10 ;
            ans += rem * rem;
            number /= 10;
        }
        return ans;
    }
}
//________________________________________________________________________________________________________________________________
//Q: https://leetcode.com/problems/middle-of-the-linked-list/description/

// Runtime 0ms
// Beats 100.00% of users with Java

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode middle = head;
        ListNode end = head;

        while(end!=null && end.next != null){
            middle = middle.next;
            end = end.next.next;
        }
        return middle;
    }
}
//________________________________________________________________________________________________________________________________
//Q: https://leetcode.com/problems/sort-list/description/

// Runtime 16ms
// Beats 13.55% of users with Java

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
           if(head == null || head.next == null){
               return head;
           }

           ListNode mid = getMid(head);
           ListNode left = sortList(head);
           ListNode right = sortList(mid);

           return merge(left, right);
    }

    ListNode merge(ListNode list1, ListNode list2){
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;
        while(list1 != null && list2 != null){
            if(list1.val < list2.val){
                tail.next = list1;
                list1 = list1.next;
                tail = tail.next;
            }else{
                tail.next = list2;
                list2 = list2.next;
                tail = tail.next;
            }
        }
        tail.next = (list1 != null) ? list1: list2;
        return dummyHead.next;
    }

    ListNode getMid(ListNode head){
        ListNode end = head;
        ListNode mid = head;
        ListNode prev = null;
        while(end != null && end.next != null){
            prev = mid;
            end = end.next.next;
            mid = mid.next;
        }
        // Disconnect the left part from the mid to avoid circular references
        if (prev != null) {
            prev.next = null; 
            }
        return mid;
    }


}

//________________________________________________________________________________________________________________________________
//Q: https://leetcode.com/problems/reverse-linked-list/description/

// Runtime 0ms
// Beats 100.00% of users with Java

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
     public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode prev = null;
        ListNode present = head;
        ListNode next = present.next;

        while (present != null) {
            present.next = prev;
            prev = present;
            present = next;
            if (next != null) {
                next = next.next;
            }
        }
        return prev;
    }
}

//________________________________________________________________________________________________________________________________
//Q: https://leetcode.com/problems/implement-queue-using-stacks/description/

// Runtime 0ms
// Beats 100.00% of users with Java

class MyQueue {
    private Stack<Integer> first;
    private Stack<Integer> second;

    public MyQueue() {
        first = new Stack<>();
        second = new Stack<>();
    }
    
    public void push(int x) {
        first.push(x);
    }
    
    public int pop() {
        while(!first.isEmpty()){
            second.push(first.pop());
        }
        int removed = second.pop();
        while(!second.isEmpty()){
            first.push(second.pop());
        }
        return removed;
    }
    
    public int peek() {
        while(!first.isEmpty()){
            second.push(first.pop());
        }
        int peeked = second.peek();
        while(!second.isEmpty()){
            first.push(second.pop());
        }
        return peeked;
    }
    
    public boolean empty() {
        return first.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
//________________________________________________________________________________________________________________________________
//Q: https://leetcode.com/problems/valid-parentheses/description/

// Runtime 1ms
// Beats 98.55% of users with Java

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack= new Stack<>();

        for(char ch : s.toCharArray()){
            if(ch == '(' || ch == '{' || ch == '['){
                stack.push(ch);
            }else{
                if (ch == ')') {
                    if (stack.isEmpty() || stack.pop() != '(') {
                        return false;
                    }
                }
                if (ch == '}') {
                    if (stack.isEmpty() || stack.pop() != '{') {
                        return false;
                    }
                }
                if (ch == ']') {
                    if (stack.isEmpty() || stack.pop() != '[') {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty(); 
    }
}
//________________________________________________________________________________________________________________________________
//Q: https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/description/

// Runtime 3ms
// Beats 34.01% of users with Java

class Solution {
    public int minAddToMakeValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(char ch : s.toCharArray()){
            if(ch == ')'){
                if(!stack.isEmpty() && stack.peek() == '('){
                    stack.pop();
                }else{
                    stack.push(ch);
                }
            }else{
                stack.push(ch);
            }
        }
        return stack.size();
    }
}