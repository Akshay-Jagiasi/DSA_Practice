// Problem: Automatic Tree Balancing Challenge
// Imagine you're working with a binary search tree, and the efficient retrieval of elements is a key concern.
// The challenge is that as you insert elements, the tree might become unbalanced, leading to suboptimal search times.
// Your task is to create a Java implementation of a self-balancing AVL tree.
// Design a Java AVL tree capable of automatically balancing itself upon element insertion.

package tree;
import java.util.*;;
public class AVL {    
    private class Node{
       private int value;
       private int height;
       private Node left;
       private Node right;
       
       public Node(int value){
        this.value = value;
       }

       public int getValue(){
        return value;
       }
    }
    private Node root;

    public AVL(){
        
    }

    public int height(){
        return height(root);
    }

    public int height(Node node){
        if(node == null){
            return -1;
        }
        return node.height;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public void insert(int value){
        root = insert(value, root);
    }
    private Node insert(int value, Node node) {
        if (node == null) {
          node = new Node(value);
          return node;
        }
    
        if (value < node.value) {
          node.left = insert(value, node.left);
        }
    
        if (value > node.value) {
          node.right = insert(value, node.right);
        }
    
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return rotate(node);
    }

    private Node rotate(Node node) {
        if (height(node.left) - height(node.right) > 1) {
          // left heavy
          if(height(node.left.left) - height(node.left.right) > 0) {
            // left left case
            return rightRotate(node);
          }
          if(height(node.left.left) - height(node.left.right) < 0) {
            // left right case
            node.left = leftRotate(node.left);
            return rightRotate(node);
          }
        }
    
        if (height(node.left) - height(node.right) < -1) {
          // right heavy
          if(height(node.right.left) - height(node.right.right) < 0) {
            // right right case
            return leftRotate(node);
          }
          if(height(node.right.left) - height(node.right.right) > 0) {
            // right left case
            node.right = rightRotate(node.right);
            return leftRotate(node);
          }
        }
    
        return node;
      }
    
      public Node rightRotate(Node p) {
        Node c = p.left;
        Node t = c.right;
    
        c.right = p;
        p.left = t;
        
        p.height = Math.max(height(p.left), height(p.right)) + 1;
        c.height = Math.max(height(c.left), height(c.right)) + 1;
    
        return c;
      }
    
      public Node leftRotate(Node c) {
        Node p = c.right;
        Node t = p.left;
    
        p.left = c;
        c.right = t;
        
        p.height = Math.max(height(p.left), height(p.right)) + 1;
        c.height = Math.max(height(c.left), height(c.right)) + 1;
    
        return p;
      }

    public void populate(int[] nums){
        for(int i=0; i<nums.length; i++){
            this.insert(nums[i]);
        }
    }

    public void populateSorted(int[] nums){
        populateSorted(nums, 0, nums.length);
    }

    private void populateSorted(int[] nums, int start, int end ){
        if(start>=end){
            return;
        }
        int mid = (start + end)/2;
        this.insert(nums[mid]);
        populateSorted(nums, start, mid);
        populateSorted(nums, mid+1, end);
    }

    public boolean balanced(){
        return balanced(root);
    }

    private boolean balanced(Node node){
        if(node == null){
            return true;
        }
        return Math.abs(height(node.left) - height(node.right)) <= 1 && balanced(node.left) && balanced(node.right);
    }

    public void display() {
        display(root, "");
    }
    
    private void display(Node node, String indent) {
        if (node == null) {
            return;
        }
    
        display(node.right, indent +"        ");
        System.out.println(indent + "|──────>" + node.value);
        display(node.left, indent + "        ");
    }
    

    public static void main(String[] args) {
        AVL tree = new AVL();
        for(int i=0; i<1000; i++){
            tree.insert(i);
        }      
        tree.display();
        System.out.println(tree.height());
    }
}



//____________________________________________________________________________________________________________________
// Problem Statement:
// Implement a Segment Tree data structure to efficiently handle two operations on an array:

// Query Operation:
// Given indices qsi and qei, find the sum of elements in the range [qsi, qei] of the original array.

// Update Operation:
// Given an index index and a new value value, update the element at the specified index in the original array. Ensure the segment tree is updated accordingly.

package tree;

public class SegmentTree {
    private static class Node{
        int data;
        int startInterval;
        int endInterval;
        Node left;
        Node right;

        public Node(int startInterval, int endInterval){
            this.startInterval = startInterval;
            this.endInterval = endInterval;
        }
    }

    Node root;

    public SegmentTree(int[] arr){
        //create a tree using this array
        this.root = constructTree(arr, 0, arr.length-1);
    }

    public Node constructTree(int[] arr, int start, int end){
        if(start == end){
            Node leaf = new Node(start, end);
            leaf.data = arr[start];
            return leaf;
        }

        //create new node with index you are at 
        Node node = new Node(start, end);
        int mid = (start+end)/2;

        node.left = this.constructTree(arr, start, mid);
        node.right = this.constructTree(arr, mid+1, end);

        node.data = node.left.data + node.right.data;
        return node;
    }

    public void display(){
        display(this.root);
    }

    private void display(Node node){
        String str = "";

        if(node.left != null){
            str = str + "Interval=[" + node.left.startInterval + "-" + node.left.endInterval + "] and data: " + node.left.data + " => ";
        }else{
            str = str + "No left child";
        }

        //for current node 
        str = str + "Interval=[" + node.startInterval + "-" + node.endInterval + "] and data: " + node.data + " <= ";

        if(node.right != null){
            str = str + "Interval=[" + node.right.startInterval + "-" + node.right.endInterval + "] and data: " + node.right.data;
        }else{
            str = str + "No right child";
        }

        System.out.println(str + "\n");

        //call recursion
        if(node.left != null){
            display(node.left);
        }

        if(node.right != null){
            display(node.right);
        }
    }

    //query
    public int query(int qsi, int qei){
        return this.query(this.root, qsi, qei);
    }

    private int query(Node node, int qsi, int qei){
        if(node.startInterval >= qsi && node.endInterval <= qei){
            //node is completely lying inside query
            return node.data;
        }else if(node.startInterval > qei || node.endInterval < qsi){
            //completely outside
            return 0;
        }else{
            // partial overlap with the query, recursion on both left and right
            return this.query(node.left, qsi, qei) + this.query(node.right, qsi, qei);
        }
    }

    //update
    public void update(int index, int value){
        this.root.data = update(this.root, index, value);
    }
    
    private int update(Node node, int index, int value){
        if(index >= node.startInterval && index <= node.endInterval){
            if(index == node.startInterval && index == node.endInterval){
                node.data = value;
                return node.data;
            }else{
                int leftAns = update(node.left, index, value);
                int rightAns = update(node.right, index, value);

                node.data = leftAns + rightAns;
                return node.data;
            }
        } 

        return node.data;
    }

    public static void main(String[] args) {
        int[] arr = {3, 8, 6, 7, -2, -8, 4, 9};
        SegmentTree segmentTree = new SegmentTree(arr);
        int qsi = 1;
        int qei = 6;
        System.out.println("Segment Tree Structure:\n");
        segmentTree.display();
        System.out.println("The sum of the range from "+qsi+" to "+qei+" => "+segmentTree.query(qsi,qei) +"\n");
        segmentTree.update(3,100);
        System.out.println("___________________________________________________________________________________________________");
        System.out.println("Segment Tree Structure after the UPDATE:\n");
        segmentTree.display();
        System.out.println("The sum of the range from "+qsi+" to "+qei+" after the update => "+segmentTree.query(qsi,qei));
    }
}


//OUTPUT: 
// Segment Tree Structure:

// Interval=[0-3] and data: 24 => Interval=[0-7] and data: 27 <= Interval=[4-7] and data: 3
// Interval=[0-1] and data: 11 => Interval=[0-3] and data: 24 <= Interval=[2-3] and data: 13
// Interval=[0-0] and data: 3 => Interval=[0-1] and data: 11 <= Interval=[1-1] and data: 8
// No left childInterval=[0-0] and data: 3 <= No right child
// No left childInterval=[1-1] and data: 8 <= No right child
// Interval=[2-2] and data: 6 => Interval=[2-3] and data: 13 <= Interval=[3-3] and data: 7
// No left childInterval=[2-2] and data: 6 <= No right child
// No left childInterval=[3-3] and data: 7 <= No right child
// Interval=[4-5] and data: -10 => Interval=[4-7] and data: 3 <= Interval=[6-7] and data: 13
// Interval=[4-4] and data: -2 => Interval=[4-5] and data: -10 <= Interval=[5-5] and data: -8
// No left childInterval=[4-4] and data: -2 <= No right child
// No left childInterval=[5-5] and data: -8 <= No right child
// Interval=[6-6] and data: 4 => Interval=[6-7] and data: 13 <= Interval=[7-7] and data: 9
// No left childInterval=[6-6] and data: 4 <= No right child
// No left childInterval=[7-7] and data: 9 <= No right child

// The sum of the range from 1 to 6 => 15

// ___________________________________________________________________________________________________
// Segment Tree Structure after the UPDATE:

// Interval=[0-3] and data: 117 => Interval=[0-7] and data: 120 <= Interval=[4-7] and data: 3
// Interval=[0-1] and data: 11 => Interval=[0-3] and data: 117 <= Interval=[2-3] and data: 106
// Interval=[0-0] and data: 3 => Interval=[0-1] and data: 11 <= Interval=[1-1] and data: 8
// No left childInterval=[0-0] and data: 3 <= No right child
// No left childInterval=[1-1] and data: 8 <= No right child
// Interval=[2-2] and data: 6 => Interval=[2-3] and data: 106 <= Interval=[3-3] and data: 100
// No left childInterval=[2-2] and data: 6 <= No right child
// No left childInterval=[3-3] and data: 100 <= No right child
// Interval=[4-5] and data: -10 => Interval=[4-7] and data: 3 <= Interval=[6-7] and data: 13
// Interval=[4-4] and data: -2 => Interval=[4-5] and data: -10 <= Interval=[5-5] and data: -8
// No left childInterval=[4-4] and data: -2 <= No right child
// No left childInterval=[5-5] and data: -8 <= No right child
// Interval=[6-6] and data: 4 => Interval=[6-7] and data: 13 <= Interval=[7-7] and data: 9
// No left childInterval=[6-6] and data: 4 <= No right child
// No left childInterval=[7-7] and data: 9 <= No right child

// The sum of the range from 1 to 6 after the update => 108



//____________________________________________________________________________________________________________________
// ## Buffer.java
// - Demonstrates the use of `StringBuffer` for efficient string manipulation.
// - Shows various operations like `append`, `insert`, `replace`, `delete`, and `reverse`.
// - Includes examples of removing whitespaces, splitting strings, and rounding off numbers.

// ## RandomString.java
// - Generates a random string of a specified size using `StringBuffer`.
// - Utilizes the `Random` class to append random characters to the string.

// ## Big_Integer_Decimal.java
// - Provides examples of using `BigInteger` for handling large integers.
// - Demonstrates basic operations such as addition, subtraction, multiplication, division, remainder, and comparison.
// - Includes examples of using `BigDecimal` for precise decimal calculations.

// ## Factorial.java
// - Calculates the factorial of a given number using `BigInteger`.
// - Supports handling large factorials without integer overflow.

package video_52_53_54;
//video 52
import java.text.DecimalFormat;
import java.util.*;
//advantages of StringBuffer over String
// mutable, efficient, Thread safe
public class Buffer {
    public static void main(String[] args) {
        //constructor 1
        StringBuffer sb = new StringBuffer();
        sb.append("okay");

        //constructor 2
        StringBuffer sb2 = new StringBuffer("someone");

        //constructor 3
        // The initial capacity is useful when you have an estimate of the number of characters you might append to the StringBuffer.
        // Providing an initial capacity can be more efficient in terms of memory usage and performance.
        // If you know that you are going to append a large number of characters, providing an initial capacity prevents the StringBuffer
        //from having to resize its internal character array (which is an expensive operation) multiple times as you append characters.
        StringBuffer sb3 = new StringBuffer(30);

        sb.append(" ----");
        sb.insert(2, "yes");
        sb.replace(1, 4,"kayyy");
        sb.delete(2, 7);
        sb.reverse();
        String str = sb.toString();
        System.out.println(str);

        //remove whitespaces
        String sentence = "Hi h    h  i       iiii      h";
        System.out.println(sentence);
        System.out.println(sentence.replaceAll("\\s", ""));

        //split
        String arr = "Akshay Kunal Hardik Anuj";
        String[] names = arr.split(" ");
        System.out.println(Arrays.toString(names));

        //Rounding off
        DecimalFormat df = new DecimalFormat("00.0000000");
        System.out.println(df.format(7.22));
    }
}


package video_52_53_54;
import java.util.*;
//video 52
public class RandomString {
    static String generate(int size){
        StringBuffer sb = new StringBuffer(size);

        Random random = new Random();

        for(int i=0; i<size; i++){
            int randomChar = 97 + (int)(random.nextFloat() * 26);
            sb.append((char)randomChar);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        int n = 100;
        String name = RandomString.generate(n);
        System.out.println(name);
    } 
}


package video_52_53_54;
import java.math.BigDecimal;
//video 53
import java.math.BigInteger;

public class Big_Integer_Decimal {
    public static void main(String[] args) {
        BigInteger B = BigInteger.valueOf(6); // convert int/long to BI
        int c = B.intValue(); // convert BI to int
        BigInteger C = new BigInteger("2345678654325678976543256789");
        BigInteger X = new BigInteger("4536789765432");

        // constants
        BigInteger D = BigInteger.TEN;

        // operations
        BigInteger s = C.add(X);
        BigInteger m = C.multiply(X);
        BigInteger sub = C.subtract(X);
        BigInteger d = C.divide(X);
        BigInteger rem = C.remainder(X);

        if (X.compareTo(C) < 0) {
            System.out.println("Yes");
        }

        BD();
    }

    static void BD() {
        double x = 0.03;
        double y = 0.04;
        // double ans = y-x;
        // System.out.println(ans);

        BigDecimal X = new BigDecimal("0.02");
        BigDecimal Y = new BigDecimal("0.04");
        BigDecimal ans = Y.subtract(X);
        // System.out.println(ans);

        BigDecimal a = new BigDecimal("47345675.4576");
        BigDecimal b = new BigDecimal("7634565352.9865764");

        // operations
        System.out.println(b.add(a));
        System.out.println(b.subtract(a));
        System.out.println(b.multiply(a));
        System.out.println(b.pow(2));
        System.out.println(b.negate());

        // constants
        BigDecimal con = BigDecimal.ONE;

    }

}


package video_52_53_54;
import java.math.BigInteger;
public class Factorial {
    static BigInteger fact(int num){
        BigInteger ans = new BigInteger("1");
        for(int i=2; i<=num; i++){
            ans = ans.multiply(BigInteger.valueOf(i));
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Factorial.fact(99999));
    }
}

//_______________________________________________________________________________________________________________
// Standard Input (Keyboard):
// Reads characters from the keyboard until the input stream is ready.
// File Reading: Reads characters from a file named "note.txt" until the input stream is ready.
// Byte to Char Stream (User Input): Reads a line of text entered by the user from the keyboard.
// File Reading using BufferedReader: Reads lines from the file "note.txt" until the input stream is ready.
import java.io.*;
class Input {
  // Reading input from different sources
  static void input() {
    // Reading from standard input (keyboard)
    try (InputStreamReader isr = new InputStreamReader(System.in)) {
      System.out.print("Enter some letters:");
      int letters = isr.read();
      while(isr.ready()) {
        System.out.println((char) letters);
        letters = isr.read();
      }
      // isr.close();
      System.out.println();
    } catch (IOException e) {
        System.out.println(e.getMessage());      
    }

    // Reading from a file
    try (FileReader fr = new FileReader("note.txt")) {
      int letters = fr.read();
      while(fr.ready()) {
        System.out.println((char)letters);
        letters = fr.read();
      }
      // fr.close(); // Note: Using try-with-resources automatically closes FileReader
      System.out.println();
    } catch (IOException e) {
        System.out.println(e.getMessage());      
    }

    // Reading from byte to char stream and then printing
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      System.out.println("You typed: " + br.readLine());
    } catch (IOException e) {
        System.out.println(e.getMessage());      
    }
    // Reading from a file using BufferedReader
    try (BufferedReader br = new BufferedReader(new FileReader("note.txt"))) {
      while (br.ready()) {
        System.out.println(br.readLine());
      }
    } catch (IOException e) {
        System.out.println(e.getMessage());      
    }
  }
}

// Creating a New File:
// Attempts to create a new file named "new-file.txt."
// Writing to a File: Writes the text "नमस्कारः" (नमस्कारः) to the created file.
// Reading from a File using BufferedReader: Uses BufferedReader to read and print the content of "new-file.txt" line by line.
// Creating and Deleting a File: Creates a file named "random.txt" and then deletes it. If successful, prints the deleted file's name.
import java.io.*;
import java.io.IOException;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    // Creating a new file
    try {
      File fo = new File("new-file.txt");
      fo.createNewFile();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

    // Writing to a file
    try {
      FileWriter fw = new FileWriter("new-file.txt");
      fw.write("नमस्कारः");
      fw.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

    // Reading from a file using BufferedReader
    try (BufferedReader br = new BufferedReader(new FileReader("new-file.txt"))) {
      while (br.ready()) {
        System.out.println(br.readLine());
      }
    } catch (IOException e) {
        System.out.println(e.getMessage());      
    }

    // Creating and deleting a file
    try {
      File fo = new File("random.txt");
      fo.createNewFile();
      if(fo.delete()) {
        System.out.println(fo.getName());
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }   
  }
}


// Writing to Standard Output (Console):
// Attempts to write characters to the standard output (System.out). Note: The commented line os.write(😍); exceeds the valid range.
// Writing Characters using OutputStreamWriter:
// Uses OutputStreamWriter to write various characters, including a string, an ASCII value (97), a newline character, and a single character ('A') to the standard output.
// Appending to a File using FileWriter: Creates a FileWriter in append mode and writes the text "this should be appended" to the file "note.txt."
// Writing to a File using BufferedWriter: Creates a BufferedWriter to efficiently write characters to the file "note.txt" and writes the text "Akshay Jagiasi."
import java.io.*;

class Output {
    static void output() {
    // Writing to standard output (console)
    OutputStream os = System.out;
    // os.write(😍); // range is exceeded

    try (OutputStreamWriter osw = new OutputStreamWriter(System.out)) {
      osw.write("Hello World");
      osw.write(97);
      osw.write(10);
      osw.write('A');
      osw.write('\n');
      char[] arr = "hello world".toCharArray();
      osw.write(arr);
      // osw.write(😍);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    // Appending to a file using FileWriter
    try (FileWriter fw = new FileWriter("note.txt", true)) {
      fw.write("this should be appended");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    // Writing to a file using BufferedWriter
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("note.txt"))) {
      bw.write("Akshay Jagiasi");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    }
}


//_______________________________________________________________________________________________________________
https://leetcode.com/problems/binary-tree-level-order-traversal/description/

// Runtime 1ms
// Beats 89.59% of users with Java

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
  public List<List<Integer>> levelOrder(TreeNode root) {
      List<List<Integer>> result = new ArrayList<>();

      if(root == null){
          return result;
      } 
      // The 'Queue' interface is used here to represent a data structure that follows the First-In-First-Out (FIFO) principle.
      // We are creating an instance of the 'Queue' using 'LinkedList', a class that implements the 'Queue' interface.
      Queue<TreeNode> queue = new LinkedList<>();

      // Enqueue the root node to start the traversal
      queue.offer(root);

      while(!queue.isEmpty()){

          // Number of nodes at the current level
          int levelSize = queue.size();

           // List to store values of nodes at the current level
          List<Integer> currentLevel = new ArrayList<>();
          
          for(int i=0; i<levelSize; i++){
              // Dequeue the front node from the queue to process it in the current level of the binary tree.
              TreeNode currentNode = queue.poll();
              currentLevel.add(currentNode.val);
              if(currentNode.left != null){
                  queue.offer(currentNode.left);
              }
              if(currentNode.right != null){
                  queue.offer(currentNode.right);
              }
          }
          // Add the values of nodes at the current level to the result list
          result.add(currentLevel);
      }
      return result; 
  }
}



//_______________________________________________________________________________________________________________
https://leetcode.com/problems/binary-tree-right-side-view/description/

// Runtime 1ms
// Beats 71.57% of users with Java

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
  public List<Integer> rightSideView(TreeNode root) {
   List<Integer> result = new ArrayList<>();

   if (root == null) {
     return result;
   }

   Queue<TreeNode> queue = new LinkedList<>();
   queue.offer(root);

   while (!queue.isEmpty()) {
     int levelSize = queue.size();

     for (int i=0; i < levelSize; i++) {
       TreeNode currentNode = queue.poll();

       if (i == levelSize - 1) {
         result.add(currentNode.val);
       }
       
       if (currentNode.left != null) {
         queue.offer(currentNode.left);
       }
       if (currentNode.right != null) {
         queue.offer(currentNode.right);
       }
     }
   }
   return result;
 }
}


//_______________________________________________________________________________________________________________
https://leetcode.com/problems/average-of-levels-in-binary-tree/description/

// Runtime 2ms
// Beats 96.32% of users with Java

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
  public List<Double> averageOfLevels(TreeNode root) {
   List<Double> result = new ArrayList<>();

   if (root == null) {
     return result;
   }

   Queue<TreeNode> queue = new LinkedList<>();
   queue.offer(root);

   while (!queue.isEmpty()) {
     int levelSize = queue.size();
     double averageLevel = 0;
     for (int i=0; i < levelSize; i++) {
       TreeNode currentNode = queue.poll(); 
       averageLevel += currentNode.val;
       if (currentNode.left != null) {
         queue.offer(currentNode.left);
       }
       if (currentNode.right != null) {
         queue.offer(currentNode.right);
       }
     }
       averageLevel = averageLevel / levelSize;
       result.add(averageLevel);
   }
   return result;
 }
}

//_______________________________________________________________________________________________________________
https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/

// Runtime 1ms
// Beats 84.39% of users with Java

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
  List<List<Integer>> result = new ArrayList<>();

  if (root == null) {
    return result;
  }

  // Use a deque to perform level-order traversal
  Deque<TreeNode> queue = new LinkedList<>();
  queue.add(root);

  // Variable to determine whether to reverse the order for each level
  boolean reverse = false;
  
  while (!queue.isEmpty()) {
    int levelSize = queue.size();
    List<Integer> currentLevel = new ArrayList<>(levelSize);
    for (int i=0; i < levelSize; i++) {
      if (!reverse) {
        // Process nodes from the front of the deque
        TreeNode currentNode = queue.pollFirst();
        currentLevel.add(currentNode.val);
        if (currentNode.left != null) {
          queue.addLast(currentNode.left);
        }
        if (currentNode.right != null) {
          queue.addLast(currentNode.right);
        }
      }else {
        // Process nodes from the back of the deque
        TreeNode currentNode = queue.pollLast();
        currentLevel.add(currentNode.val);


        if (currentNode.right != null) {
          queue.addFirst(currentNode.right);
        }
        if (currentNode.left != null) {
          queue.addFirst(currentNode.left);
        }
      }
    }
    // Toggle the 'reverse' flag for the next level
    reverse = !reverse;
    result.add(currentLevel);
  }
  return result;
  }
}

//_______________________________________________________________________________________________________________
https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/

// Runtime 1ms
// Beats 93.27% of users with Java

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
  public List<List<Integer>> levelOrderBottom(TreeNode root) {
      List<List<Integer>> result = new ArrayList<>();

      if (root == null) {
          return result;
      }

      Queue<TreeNode> queue = new LinkedList<>();
      queue.offer(root);

      while (!queue.isEmpty()) {
          int levelSize = queue.size();
          List<Integer> currentLevel = new ArrayList<>(levelSize);
          
          for (int i = 0; i < levelSize; i++) {
              TreeNode currentNode = queue.poll();
              currentLevel.add(currentNode.val);
              
              if (currentNode.left != null) {
                  queue.offer(currentNode.left);
              }
              if (currentNode.right != null) {
                  queue.offer(currentNode.right);
              }
          }
          
          result.add(0, currentLevel); // Insert at the beginning to reverse the order
      }

      return result;
  }
}


//_______________________________________________________________________________________________________________
https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/

// Runtime 0ms
// Beats 100.00% of users with Java

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
  public Node connect(Node root) {
    if (root == null){
      return null;
    }

    Node leftMost = root;

    while (leftMost.left != null) {
      Node current = leftMost;
      while(current != null) {
        current.left.next = current.right;
        if(current.next != null) {
          current.right.next = current.next.left;
        }
        current = current.next;
      }
      leftMost = leftMost.left;
    }
    return root;
  }
}


// Problem Statement: Find Level-Order Successor in Binary Tree
// You are given the root of a binary tree and an integer key.
// Write a function findSuccessor to find the level-order successor of the node with the given key.
// The level-order successor of a node in a binary tree is the node that appears immediately after 
// the given node in a level-order traversal.

public TreeNode findSuccessor(TreeNode root, int key){
  if (root == null) {
    return null;
  }

  Queue<TreeNode> queue = new LinkedList<>();
  queue.offer(root);

  while (!queue.isEmpty()) {
    int levelSize = queue.size();
      TreeNode currentNode = queue.poll();
      if (currentNode.left != null) {
        queue.offer(currentNode.left);
      }
      if (currentNode.right != null) {
        queue.offer(currentNode.right);
      }
      if (currentNode.val = key) {
        break;
      }
  }
  return queue.peek(); 
}