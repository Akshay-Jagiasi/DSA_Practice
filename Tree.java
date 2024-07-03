// Problem Statement: Binary Tree Population and Display
// You are tasked with implementing a program to create and display a binary tree.
// The program should take input from the user to populate the binary tree and then display it in a tree-like structure.
import java.util.*;;
public class Binarytree {     
    private class Node{
       int value;
       Node left;
       Node right;
       
       public Node(int value){
        this.value = value;
       }
    }

    private Node root;

    //insert elements
    public void populate(Scanner scanner){
        System.out.println("Enter the root node: ");
        int value = scanner.nextInt();
        root = new Node(value);
        populate(scanner, root);
    }

    public void populate(Scanner scanner, Node node){
        System.out.println("Do you want to enter left of: "+node.value);
        boolean left = scanner.nextBoolean();
        if(left){
            System.out.println("Enter the value of the left of "+node.value);
            int value = scanner.nextInt();
            node.left = new Node(value);
            populate(scanner, node.left);
        }
        System.out.println("Do you want to enter right of "+node.value);
        boolean right = scanner.nextBoolean();
        if(right){   
            System.out.println("Enter the value of the right of "+node.value);
            int value = scanner.nextInt();
            node.right = new Node(value);
            populate(scanner, node.right);
        }
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
    
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){
        if(node == null){
            return;
        }
        System.out.print(node.value+ " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){
        if(node == null){
            return;
        }
        inOrder(node.left);
        System.out.print(node.value+ " ");
        inOrder(node.right);
    }

    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node){
        if(node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.value+ " ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Binarytree binarytree = new Binarytree();
        binarytree.populate(scanner);
        binarytree.display();
        binarytree.preOrder();
        System.out.println();
        binarytree.inOrder();
        System.out.println();
        binarytree.postOrder();
    }


}

//Output:
// Enter the root node: 
// 10
// Do you want to enter left of: 10
// true
// Enter the value of the left of 10
// 11
// Do you want to enter left of: 11
// true
// Enter the value of the left of 11
// 20
// Do you want to enter left of: 20
// true
// Enter the value of the left of 20
// 22
// Do you want to enter left of: 22
// false
// Do you want to enter right of 22
// false
// Do you want to enter right of 20
// true
// Enter the value of the right of 20
// 33
// Do you want to enter left of: 33
// false
// Do you want to enter right of 33
// false
// Do you want to enter right of 11
// true
// Enter the value of the right of 11
// 30
// Do you want to enter left of: 30
// true
// Enter the value of the left of 30
// 6
// Do you want to enter left of: 6
// false
// Do you want to enter right of 6
// false
// Do you want to enter right of 30
// true
// Enter the value of the right of 30
// 7
// Do you want to enter left of: 7
// false
// Do you want to enter right of 7
// false
// Do you want to enter right of 10
// true
// Enter the value of the right of 10
// 100
// Do you want to enter left of: 100
// true
// Enter the value of the left of 100
// 40
// Do you want to enter left of: 40
// true
// Enter the value of the left of 40
// 2
// Do you want to enter left of: 2
// false
// Do you want to enter right of 2
// false
// Do you want to enter right of 40
// true
// Enter the value of the right of 40
// 3
// Do you want to enter left of: 3
// false
// Do you want to enter right of 3
// false
// Do you want to enter right of 100
// true
// Enter the value of the right of 100
// 50
// Do you want to enter left of: 50
// true
// Enter the value of the left of 50
// 5
// Do you want to enter left of: 5
// false
// Do you want to enter right of 5
// false
// Do you want to enter right of 50
// true
// Enter the value of the right of 50
// 99
// Do you want to enter left of: 99
// false
// Do you want to enter right of 99
// false
//                         |──────>99
//                 |──────>50
//                         |──────>5
//         |──────>100
//                         |──────>3
//                 |──────>40
//                         |──────>2
// |──────>10
//                         |──────>7
//                 |──────>30
//                         |──────>6
//         |──────>11
//                         |──────>33
//                 |──────>20
//                         |──────>22
_____________________________________________________________________________________________________
// Problem Statement: Binary Search Tree Operations
// You are tasked with implementing a program that works with a Binary Search Tree (BST).
// The program should include functionality to insert elements into the BST, populate it
// with both sorted and unsorted arrays, check if it is balanced, and display the tree in a visually informative format.

import java.util.*;;
public class BinarySearchtree {    
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

    public BinarySearchtree(){
        
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
        root = insert(root, value);
    }

    public Node insert(Node node, int value){
        if(node == null){
            node = new Node(value);
            return node;
        }

        if(value < node.value){
            node.left = insert(node.left, value);
        }

        if(value > node.value){
            node.right = insert(node.right, value);
        }

        node.height = Math.max(height(node.left), height(node.right))+1;

        return node;
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
        BinarySearchtree BST = new BinarySearchtree();
        int[] nums = {1,2,3,4,5,6,7,8,9};
        BST.populateSorted(nums);
        BST.display();
        int treeHeight = BST.height(BST.root);
        System.out.println(treeHeight);
        System.out.println(BST.balanced());
    }
}

//OUTPUT
//                 |──────>9
//         |──────>8
//                 |──────>7
//                         |──────>6
// |──────>5
//                 |──────>4
//         |──────>3
//                 |──────>2
//                         |──────>1
// true



// In brackets{height}
//                 |──────>9 (0)
//         |──────>8 (2)
//                 |──────>7 (1)
//                            |──────>6 (0)
// |──────>5 (3)
//                 |──────>4 (0)
//         |──────>3 (2)
//                 |──────>2 (1)
//                         |──────>1 (0)

_____________________________________________________________________________________________________
// Problem: Automatic Tree Balancing Challenge
// Imagine you're working with a binary search tree, and the efficient retrieval of elements is a key concern.
// The challenge is that as you insert elements, the tree might become unbalanced, leading to suboptimal search times.
// Your task is to create a Java implementation of a self-balancing AVL tree.
// Design a Java AVL tree capable of automatically balancing itself upon element insertion.


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
          //        p                                      c
          //       / \                                   /   \
          //      c   T4      Right Rotate (z)          g      p
          //     / \          - - - - - - - - ->      /  \    /  \ 
          //    g   T3                               T1   T2 T3  T4
          //   / \
          // T1   T2
            return rightRotate(node);
          }
          if(height(node.left.left) - height(node.left.right) < 0) {
          // left right case
          //      p                               p                            g
          //     / \                            /   \                        /   \ 
          //    c   T4  Left Rotate (y)        g    T4  Right Rotate (z)   c      p
          //   / \      - - - - - - - - ->    /  \      - - - - - - - ->  / \    / \ 
          // T1   g                          c    T3                    T1  T2  T3  T4
          //     / \                        /  \
          //   T2   T3                    T1   T2
        
            node.left = leftRotate(node.left);
            return rightRotate(node);
          }
        }
    
        if (height(node.left) - height(node.right) < -1) {
          // right heavy
          if(height(node.right.left) - height(node.right.right) < 0) {
          // right right case
          //    p                               c
          //   / \                            /   \ 
          //  T1  c     Left Rotate(z)       p     g
          //    /  \   - - - - - - - ->    /  \   /  \ 
          //   T2   g                    T1   T2 T3  T4
          //       / \
          //      T3  T4
           
            return leftRotate(node);
          }
          if(height(node.right.left) - height(node.right.right) > 0) {
          // right left case
          //    p                            p                             g
          //   / \                          / \                          /   \ 
          // T1   c   Right Rotate (y)     T1   g      Left Rotate(z)   p     c
          //     /  \  - - - - - - - ->        /  \   - - - - - - - -> / \   / \
          //    g    T4                      T2    c                  T1 T2 T3  T4
          //   /  \                               /  \
          // T2   T3                            T3   T4
          
            node.right = rightRotate(node.right);
            return leftRotate(node);
          }
        }
     
        return node;
      }
    
      //Example Tree Before Right Rotation
      // Where:
      // p is the root node that is left-heavy.
      // c is the left child of p.
      // t is the right child of c.
      // L and R are subtrees.

      // Initial State
      //       p
      //      / \
      //     c   R
      //    / \
      //   L   t

      // Step-by-Step Transformation
      // Initialize c and t
      // Node c = p.left;
      // Node t = c.right;

      // State:
      // c = c
      // t = t

      // Perform the Rotation
      // c.right = p;
      // State:
      //       c
      //      / \
      //     L   p
      //          \
      //           R

      // p.left = t;
      // State:
      //       c
      //      / \
      //     L   p
      //        / \
      //       t   R

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
        for(int i=0; i<13 ; i++){
            tree.insert(i);
        }      
        tree.display();
        System.out.println(tree.height());
    }
}
_____________________________________________________________________________________________________
// Problem Statement:
// Implement a Segment Tree data structure to efficiently handle two operations on an array:

// Query Operation:
// Given indices qsi and qei, find the sum of elements in the range [qsi, qei] of the original array.

// Update Operation:
// Given an index index and a new value value, update the element at the specified index in the original array. Ensure the segment tree is updated accordingly.


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

// ---------------------------------------
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
