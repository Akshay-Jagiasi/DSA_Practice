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
