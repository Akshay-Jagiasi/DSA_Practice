// Problem Statement:
// Implement a generic heap structure with Min Heap, Max Heap, Min Priority Queue, and Max Priority Queue.
// Utilize an abstract class, ensuring code reuse through protected methods. Demonstrate insertion, removal, and heap sort operations.
// MinHeap and MaxHeap inherit the structure and implement specific behaviors.
// MinPriorityQueue and MaxPriorityQueue extend the corresponding heaps for priority queue functionality.

package Heap;
import java.util.ArrayList;

// protected is used for methods and fields in the HeapStructure class to allow its subclasses, like MinHeap and MaxHeap, to access and reuse them.
// This promotes code reuse while still encapsulating internal details.

// Abstract class representing a generic heap structure
public abstract class HeapStructure<T extends Comparable<T>>{
    protected ArrayList<T> list;

    // Constructor to initialize the list
    public HeapStructure(){
        list = new ArrayList<>();
    }

    // Method to swap elements at two indices in the list
    protected void swap (int first, int second){
        T temp = list.get(first);
        list.set(first, list.get(second));
        list.set(second, temp);
    }

    // Method to calculate the index of the parent of a given index
    protected int parent(int index){
        return (index-1)/2;
    }

    // Method to calculate the index of the left child of a given index
    protected int left(int index){
        return index*2+1;
    }

    // Method to calculate the index of the right child of a given index
    protected int right(int index){
        return index*2+2;
    }

    // Method to perform heap sort and return sorted data
    public ArrayList<T> heapSort() throws Exception{
        ArrayList<T> data = new ArrayList<>();
        while(!list.isEmpty()){
            data.add(this.remove());
        }
        return data;
    }

    // Abstract method to insert an element into the heap
    public abstract void insert(T value);

    // Abstract method to perform up-heap operation
    protected abstract void upheap(int index);

    // Abstract method to remove the root element from the heap
    public abstract T remove() throws Exception;

    // Abstract method to perform down-heap operation
    protected abstract void downheap(int index);
}

// Class representing a Min Heap, extending the generic HeapStructure
class MinHeap<T extends Comparable<T>> extends HeapStructure<T>{
    
    @Override
    public void insert(T value){
        list.add(value);
        upheap(list.size()-1);
    }

    @Override
    protected void upheap(int index){
        if(index == 0){
            return;
        }
        int p = parent(index);
        if(list.get(index).compareTo(list.get(p)) < 0){
            swap(index, p);
            upheap(p);
        }
    }

    @Override
    public T remove() throws Exception{
        if(list.isEmpty()){
            throw new Exception("Removing from an empty heap");
        }
        T temp = list.get(0);

        T last = list.remove(list.size() -1);
        if(!list.isEmpty()){
            list.set(0, last);
            downheap(0);
        }

        return temp;
    }

    @Override
    protected void downheap(int index){
        int min = index;
        int left = left(index);
        int right = right(index);

        if(left < list.size() && list.get(min).compareTo(list.get(left)) > 0){
            min = left;
        }

        if(right < list.size() && list.get(min).compareTo(list.get(right)) > 0){
            min = right;
        }

        if(min != index){
            swap(min, index);
            downheap(min);
        }
    }
}

// Class representing a Max Heap, extending the generic HeapStructure
class MaxHeap<T extends Comparable<T>> extends HeapStructure<T>{
    
    @Override
    public void insert(T value){
        list.add(value);
        upheap(list.size()-1);
    }

    @Override
    protected void upheap(int index){
        if(index == 0){
            return;
        }
        int p = parent(index);
        if(list.get(index).compareTo(list.get(p)) > 0){
            swap(index, p);
            upheap(p);
        }
    }

    @Override
    public T remove() throws Exception{
        if(list.isEmpty()){
            throw new Exception("Removing from an empty heap");
        }
        T temp = list.get(0);

        T last = list.remove(list.size() -1);
        if(!list.isEmpty()){
            list.set(0, last);
            downheap(0);
        }

        return temp;
    }
    
    @Override
    protected void downheap(int index){
        int max = index;
        int left = left(index);
        int right = right(index);

        if(left < list.size() && list.get(max).compareTo(list.get(left)) < 0){
            max = left;
        }

        if(right < list.size() && list.get(max).compareTo(list.get(right)) < 0){
            max = right;
        }

        if(max != index){
            swap(max, index);
            downheap(max);
        }
    }
}

// MinPriorityQueue: Represents a Priority Queue with Min Heap behavior
class MinPriorityQueue<T extends Comparable<T>> extends MinHeap<T> {
    // Inherits heap functionality for a minimum priority queue
}

// MaxPriorityQueue: Represents a Priority Queue with Max Heap behavior
class MaxPriorityQueue<T extends Comparable<T>> extends MaxHeap<T> {
    // Inherits heap functionality for a maximum priority queue
}


package Heap;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception{


        HeapStructure<Integer> minheap = new MinHeap();
        minheap.insert(22);
        minheap.insert(222);
        minheap.insert(3422);
        minheap.insert(2);
        minheap.insert(20);
        minheap.insert(12);
        minheap.insert(2392);
        ArrayList<Integer> minList = new ArrayList<>();
        minList = minheap.heapSort();
        System.out.println(minList);


        HeapStructure<Integer> maxheap = new MaxHeap();
        maxheap.insert(22);
        maxheap.insert(222);
        maxheap.insert(3422);
        maxheap.insert(2);
        maxheap.insert(20);
        maxheap.insert(12);
        maxheap.insert(2392);
        System.out.println(maxheap.remove());
        ArrayList<Integer> maxList = new ArrayList<>();
        maxList = maxheap.heapSort();
        System.out.println(maxList);

        
        MinPriorityQueue<Integer> minpriorityqueue = new MinPriorityQueue();
        minpriorityqueue.insert(22);
        minpriorityqueue.insert(222);
        minpriorityqueue.insert(3422);
        minpriorityqueue.insert(2);
        System.out.println(minpriorityqueue.remove());
        

        MaxPriorityQueue<Integer> maxpriorityqueue = new MaxPriorityQueue();
        maxpriorityqueue.insert(22);
        maxpriorityqueue.insert(222);
        maxpriorityqueue.insert(3422);
        maxpriorityqueue.insert(2);
        System.out.println(maxpriorityqueue.remove());
    }
}


//OUTPUT:
[2, 12, 20, 22, 222, 2392, 3422]
3422
[2392, 222, 22, 20, 12, 2]
2
3422


//_______________________________________________________________________________________________________________
https://leetcode.com/problems/cousins-in-binary-tree/description/

// Runtime 0ms
// Beats 100.00% of users with Java

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
    public boolean isCousins(TreeNode root, int x, int y) {
   TreeNode xx = findNode(root, x);
   TreeNode yy = findNode(root, y);

   // Return true if both nodes are at the same level and are not siblings
   return (
     (level(root, xx, 0) == level(root, yy, 0)) && (!isSibling(root, xx, yy))
   );
}

 // Helper function to find a TreeNode with a specific value in the tree
 TreeNode findNode(TreeNode node, int x) {
   if (node == null) {
     return null;
   }
   if (node.val == x) {
     return node;
   }

   // Recursively search for the target value in the left subtree
   TreeNode n = findNode(node.left, x);
   if (n != null) {
     return n;
   }
   return findNode(node.right, x);
 }

 boolean isSibling (TreeNode node, TreeNode x, TreeNode y) {
   if (node == null) {
     return false;
   }

   //return true if x and y are siblings 
   return (
     (node.left == x && node.right == y) || (node.left == y && node.right == x)
     || isSibling(node.left, x, y) || isSibling(node.right, x, y)
   );
 }
 
 // Helper function to find the level of a specific node in the tree
 int level (TreeNode node, TreeNode x, int lev) {
   if(node == null) {
     return 0;
   }

   if(node == x) {
     return lev;
   }

   // Recursively search for the target node in the left subtree
   int l = level(node.left, x, lev+1);

   // If the target node is found in the left subtree, return its level
   if (l != 0) {
     return l;
   }

   // If the target node is not found in the left subtree, recursively search in the right subtree
   return level(node.right, x, lev+1);
 }
}


//_______________________________________________________________________________________________________________
https://leetcode.com/problems/symmetric-tree/

// Runtime 1ms
// Beats 22.51% of users with Java

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
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);

        while(!queue.isEmpty()){
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            if(left == null && right == null){
                continue;
            }
            if(left == null || right == null){
                return false;
            }

            if(left.val != right.val){
                return false;
            }

            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        }
        return true;
    }
}
