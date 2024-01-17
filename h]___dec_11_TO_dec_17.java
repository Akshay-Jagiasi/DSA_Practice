//Question: https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
class Solution {
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) {
            return new ArrayList<>();
        }
        return solve("", digits);
    }

    private List<String> solve(String ans, String ip) {
        if(ip.isEmpty()) {
            ArrayList<String> list = new ArrayList<>();
            list.add(ans);
            return list;
        }
        int digit = ip.charAt(0) - '0';
        int i=(digit-2)*3;
        if(digit > 7) {
            i+=1;
        }
        int len = i+3;
        if(digit == 7 || digit == 9) {
            len+=1;
        }
        ArrayList<String> list = new ArrayList<>();                
        for(; i<len; i++) {
            char ch = (char)('a' + i);
            list.addAll(solve(ans+ch, ip.substring(1)));
        }
        
        return list;
    }
}

// __________________________________________________________________________________________________________________________________________________________
// Problem Statement: Unique Paths in a Grid
// You are given a rectangular grid with r rows and c columns.
// The top-left cell is the starting point, and the bottom-right cell is the destination.
// Your task is to determine the number of unique paths to reach the destination.
// You can only move down or to the right from any cell. 
// Write a function int count(int r, int c) that calculates and returns the total number of unique paths from the starting point to the destination.
//    3    2    1
//    ___________
// 3 |   |   |   |
//   |-----------|
// 2 |   |   |   |
//   |-----------|
// 1 |   |   |   |
//   |___________|

public class mazeProblem {
    public static void main(String[] args) {
        System.out.println(count(3, 3));
    }   
    
    static int count(int r, int c){
        if(r == 1 || c == 1){
            return 1;
        }
        int left = count(r-1, c);
        int right = count(r, c-1);
        return left + right;
    }
}


// __________________________________________________________________________________________________________________________________________________________
// Problem Statement: Print All Unique Paths in a Grid
// You are given a rectangular grid with r rows and c columns.
// The top-left cell is designated as the starting point, and the bottom-right cell is the destination.
// Your goal is to print all unique paths from the starting point to the destination.
// You can only move either down or to the right from any cell.
// In other words, from any cell, you can move to either the cell below or the cell to the right.
// Write a function void path(String p, int r, int c) that takes the current path p as a string and the number of rows (r) and columns (c) as input.
// The function prints all unique paths from the starting point to the destination.
// Example:
//    3    2    1
//    ___________
// 3 |   |   |   |
//   |-----------|
// 2 |   |   |   |
//   |-----------|
// 1 |   |   |   |
//   |___________|
// Print each unique path from the starting point (3,3) to the destination (1,1).
// Output:
// DDRR
// DRDR
// DRRD
// RDDR
// RDRD
// RRDD

public class mazeProblem {
    public static void main(String[] args) {
        path("", 3, 3);
    }

    static void path(String p, int r, int c){
        if(r == 1 && c == 1){
            System.out.println(p);
            return;
        }

        if(r>1){
            path(p+'D', r-1,c); //D->Down
        }

        if(c>1){
            path(p+'R', r, c-1);  //R->Right
        }
    }
}


// __________________________________________________________________________________________________________________________________________________________
// Problem Statement: Print All Paths in a Grid with Diagonal, vertical, horizontal Movements
// You are given a rectangular grid with r rows and c columns. 
// The top-left cell is designated as the starting point, and the bottom-right cell is the destination.
// Your goal is to print all unique paths from the starting point to the destination.
// You can move either vertically (V), horizontally (H), or diagonally (D) from any cell.
// Write a function pathDiagonalAlso(String p, int r, int c) that takes the current path p as a string and the number of rows (r) and columns (c) as input.
// The function prints all unique paths from the starting point to the destination.
// Example:
//    3    2    1
//    ___________
// 3 |   |   |   |
//   |-----------|
// 2 |   |   |   |
//   |-----------|
// 1 |   |   |   |
//   |___________|
// Print each unique path from the starting point (3,3) to the destination (1,1).
// Output:
// DD
// DVH
// DHV
// VDH
// VVHH
// VHD
// VHVH
// VHHV
// HDV
// HVD
// HVVH
// HVHV
// HHVV

public class mazeProblem {
    public static void main(String[] args) {
        pathDiagonalAlso("", 3, 3);
    }

    static void pathDiagonalAlso(String p, int r, int c){
        if(r == 1 && c == 1){
            System.out.println(p);
            return;
        }
        if(r>1 && c>1){
            pathDiagonalAlso(p+'D', r-1, c-1); //D-> Diagonal
        }
        if(r>1){
            pathDiagonalAlso(p+'V', r-1,c); //V->Vertical
        }

        if(c>1){
            pathDiagonalAlso(p+'H', r, c-1);  //H->Horizontal
        }
    }
}



// __________________________________________________________________________________________________________________________________________________________
// You are given a rectangular maze represented by a boolean matrix, where true represents a valid cell and false represents an obstacle.
// The top-left cell is designated as the starting point, and the bottom-right cell is the destination.
// Your goal is to print all unique paths from the starting point to the destination while adhering to the given restrictions.
// You can only move down or to the right from any valid cell (true).
//    0    1    2
//    ___________
// 0 |   |   |   |
//   |-----------|
// 1 |   |   |   |
//   |-----------|
// 2 |   |   |   |
//   |___________|
// I added this matrix here for the reference that in this problem we are solving it by
// visualizing the matrix whose start index is 0,0 and increases in ascending order so the the base condition is different here

public class mazeProblem {
    public static void main(String[] args) {
        boolean[][] maze ={
            {true, true,  true},
            {true, false, true},
            {true, true,  true}
        };
        pathRestrictions("", maze, 0, 0);
    }

    static void pathRestrictions(String p,boolean[][] maze, int r, int c){
        if(r == maze.length-1 && c == maze[0].length-1){
            System.out.println(p);
            return;
        }
        if(!maze[r][c]){
            return;   
        }
        if(r<maze.length-1){
            pathRestrictions(p+'D', maze, r+1, c); //D-> Down
        }

        if(c<maze[0].length-1){
            pathRestrictions(p+'R',maze, r, c+1); //R->Right
        }
    }
}

//output 
// DDRR
// RRDD



// __________________________________________________________________________________________________________________________________________________________
// Problem Statement: Print All Paths in a Grid with Up, Down, Left, Right Movements
// You are given a rectangular grid with r rows and c columns. 
// The top-left cell is designated as the starting point, and the bottom-right cell is the destination.
// Your goal is to print all unique paths from the starting point to the destination.
// You can move either Up (U), Down (D),  Left (L) , Right(R) from any cell.


//***IMP Note***
// As the algorithm explores paths in all four directions (up, down, left, and right), it is crucial to handle the potential risk of infinite loops.
// The backtracking mechanism, where each cell is marked and subsequently unmarked (maze[r][c] = false; followed by maze[r][c] = true;),
// ensures the algorithm's correctness and prevents revisiting the same cell indefinitely within the same path.

public class mazeProblem {
    public static void main(String[] args) {
        boolean[][] maze ={
            {true, true,  true},
            {true, true, true},
            {true, true,  true}
        };
        allPath("", maze, 0, 0);
    }

    static void allPath(String p,boolean[][] maze, int r, int c){
        if(r == maze.length-1 && c == maze[0].length-1){
            System.out.println(p);
            return;
        }
        if(!maze[r][c]){
            return;   
        }

        //I am considering this block as my path
        //we are making the block false so that we know we travelled from here 
        //this helps us to not go in the infinite recursion call
        maze[r][c] = false;

        if(r<maze.length-1){
            allPath(p+'D', maze, r+1, c); //D -> Down
        }

        if(c<maze[0].length-1){
            allPath(p+'R',maze, r, c+1); //R-> Right
        }

        if(r>0){
            allPath(p+'U', maze, r-1, c); //U-> Up
        }

        if(c>0){
            allPath(p+'L', maze, r, c-1); //L -> Left
        }
        //this line is where the function will be over 
        // so before the function gets removed, also remove the changes that are made by the function 
        maze[r][c] = true;
    }
}

//OUTPUT:
// DDRR
// DDRURD
// DDRUURDD
// DRDR
// DRRD
// DRURDD
// RDDR
// RDRD
// RDLDRR
// RRDD
// RRDLDR
// RRDLLDRR



//__________________________________________________________________________________________________________________________________________________________
// Problem Statement: Find All Paths in a Maze with Step Numbers
// You are given a rectangular maze with r rows and c columns, represented by a boolean matrix maze.
// The top-left cell is the starting point , and the bottom-right cell is the destination (r, c).
// Your goal is to find all possible paths from the starting point to the destination and print them along with the step numbers.
// You can move either Up (U), Down (D),  Left (L) , Right(R) from any cell.

public class mazeProblem {
    public static void main(String[] args) {
        boolean[][] board ={
            {true, true,  true},
            {true, true, true},
            {true, true,  true}
        };
        int[][] path = new int[board.length][board[0].length];
        allPathPrint("", board, 0, 0, path, 1);
    }

    static void allPathPrint(String p,boolean[][] maze, int r, int c, int[][] path,int step){
        if(r == maze.length-1 && c == maze[0].length-1){
            path[r][c] = step;
            for(int[] arr : path){
                System.out.println(Arrays.toString(arr));
            }
            System.out.println(p);
            System.out.println();
            return;
        }
        if(!maze[r][c]){
            return;   
        }

        //I am considering this block as my path
        //we are making the block false so that we know we travelled from here 
        //this helps us to not go in the infinite recursion call
        maze[r][c] = false;
        path[r][c] = step;
        if(r<maze.length-1){
            allPathPrint(p+'D', maze, r+1, c, path, step+1); //D -> Down
        }

        if(c<maze[0].length-1){
            allPathPrint(p+'R',maze, r, c+1, path, step+1); //R-> Right
        }

        if(r>0){
            allPathPrint(p+'U', maze, r-1, c, path, step+1); //U-> Up
        }

        if(c>0){
            allPathPrint(p+'L', maze, r, c-1, path, step+1); //L -> Left
        }
        //this line is where the function will be over 
        // so before the function gets removed, also remove the changes that are made by the function 
        maze[r][c] = true;
        path[r][c] = step;
    }
}
// OUTPUT:
// [1, 0, 0]
// [2, 0, 0]
// [3, 4, 5]
// DDRR

// [1, 0, 0]
// [2, 5, 6]
// [3, 4, 7]
// DDRURD

// [1, 6, 7]
// [2, 5, 8]
// [3, 4, 9]
// DDRUURDD

// [1, 6, 7]
// [2, 3, 8]
// [3, 4, 5]
// DRDR

// [1, 6, 7]
// [2, 3, 4]
// [5, 4, 5]
// DRRD

// [1, 4, 5]
// [2, 3, 6]
// [5, 4, 7]
// DRURDD

// [1, 2, 5]
// [2, 3, 6]
// [5, 4, 5]
// RDDR

// [1, 2, 5]
// [6, 3, 4]
// [5, 4, 5]
// RDRD

// [1, 2, 5]
// [4, 3, 4]
// [5, 6, 7]
// RDLDRR

// [1, 2, 3]
// [4, 3, 4]
// [5, 6, 5]
// RRDD

// [1, 2, 3]
// [4, 5, 4]
// [5, 6, 7]
// RRDLDR

// [1, 2, 3]
// [6, 5, 4]
// [7, 8, 9]
// RRDLLDRR
