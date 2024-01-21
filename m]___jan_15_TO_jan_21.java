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


//_______________________________________________________________________________________________________________
https://leetcode.com/problems/diameter-of-binary-tree/

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
    int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return diameter-1;
    }

    public int height(TreeNode node){
        if(node == null){
            return 0;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        int dia = leftHeight + rightHeight + 1;
        diameter = Math.max(diameter, dia);

        return Math.max(leftHeight, rightHeight) + 1;
    }
}


//_______________________________________________________________________________________________________________
// Problem Statement: Custom HashMap Implementations

// Objective:
// Implement different collision resolution strategies in custom HashMaps using Java.

// HashMapStrategy is an interface representing a generic hash map strategy.
// It defines the common methods required for hash map implementations.
// All hash map strategies should implement these methods for consistency.
package HashMap;

interface HashMapStrategy<K, V> {
    public static final int DEFAULT_CAPACITY = 4;
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;
    void put(K key, V value);
    V get(K key);
    V remove(K key);
    int size();
}



// ChainingHashMap is an implementation of a hash map using the chaining method.
// It uses linked lists to handle collisions by storing multiple key-value pairs
// in the same bucket.

// When to use:
// - Use ChainingHashMap when you expect a high number of collisions in your hash map.
// - Well-suited for scenarios with frequent insertions, deletions, and updates.
// - Provides good performance in situations where the load factor may vary.
// - Dynamic resizing helps maintain a balance between space and time complexity.
package HashMap;
import java.util.LinkedList;

public class ChainingHashMap <K, V> implements HashMapStrategy<K, V>{
    private int n;
    private LinkedList<Node>[] buckets;

    private class Node{
        K key;
        V value;

        Node(K key, V value){
            this.key = key;
            this.value = value;
        }
    }

    private void initBuckets(int N){
        buckets = new LinkedList[N];
        for(int i=0; i<buckets.length; i++){ 
            buckets[i] = new LinkedList<>();
        }
    }

    // Constructs a ChainingHashMap with the default capacity
    public ChainingHashMap(){
        initBuckets(DEFAULT_CAPACITY);
    }

    // Rehashes the hash map when the load factor exceeds the threshold
    private void rehash(){
        LinkedList<Node>[] oldBuckets = buckets;
        initBuckets(oldBuckets.length * 2);
        n = 0;
        // Reinsert nodes into the new array of buckets
        for(var bucket : oldBuckets){
            for(var node : bucket){
                put(node.key, node.value);
            }
        }
    }

    public int size(){
         return n;
    }

    private int HashFunc(K key){
        int hc = key.hashCode();
        return (Math.abs(hc)) % buckets.length;
    }

    //traveses the the linkedlist at the current index
    private int SearchInBucket(LinkedList<Node> currBucket, K key){
        for(int i=0; i<currBucket.size(); i++){
            if(currBucket.get(i).key.equals(key)){
                return i;
            }
        }
        return -1;
    }

    // Inserts or updates a key-value pair in the hash map
    public void put(K key, V value){
        int bi = HashFunc(key); //bi = bucketindex
        LinkedList<Node> currBucket = buckets[bi];
        int ei = SearchInBucket(currBucket, key); //ei entry index;
        if(ei == -1){ //key doesnt exist we have to insert a new node
            Node node = new Node(key, value);
            currBucket.add(node);
            n++;
        }else{ //key exist we have to update 
            Node currNode = currBucket.get(ei);
            currNode.value = value;
        }

        if(n >= buckets.length * DEFAULT_LOAD_FACTOR){
            rehash();
        }
    }

    public V get(K key){
        int bi = HashFunc(key);
        LinkedList<Node> currBucket = buckets[bi];
        int ei = SearchInBucket(currBucket, key);
        if(ei != -1){
            Node currNode = currBucket.get(ei);
            return currNode.value;
        }
        return null;
    }

    public V remove(K key){
        int bi = HashFunc(key);
        LinkedList<Node> currBucket = buckets[bi];
        int ei = SearchInBucket(currBucket, key);
        if(ei != -1){
            Node currNode = currBucket.get(ei);
            V val = currNode.value;
            currBucket.remove(ei);
            n--;
            return val;
        }
        return null;
    }

}


// OpenAddressingTechnique is an abstract class serving as a base for open addressing techniques such as linear probing, quadratic probing, and double hashing.
// It provides common functionality for handling collisions and resizing.
package HashMap;

public abstract class OpenAddressingTechnique <K, V>{

    protected Node<K, V>[] table;
    protected int size;

    protected static class Node<K, V> {
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Find the index based on key hash
    protected int findIndex(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % table.length;
    }

    public int size() {
        return size;
    }
}



// LinearProbingHashMap is an implementation of a hash map using linear probing for collision resolution.
// It searches for the next available slot linearly in case of collisions.
// 
// When to use:
// - Use LinearProbingHashMap when you expect a moderate number of collisions in your hash map.
// - Well-suited for scenarios with a relatively low load factor and a low likelihood of extensive clustering.
// - Provides good performance in scenarios with a stable load factor and minimal resizing requirements.
// - Efficient for scenarios where key access patterns exhibit locality.
package HashMap;
import java.util.Arrays;

public class LinearProbingHashMap<K, V> extends OpenAddressingTechnique<K, V> implements HashMapStrategy<K, V> {

    public LinearProbingHashMap() {
        table = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    // Put method to insert or update key-value pair in the hash map
    public void put(K key, V value) {
        int index = findIndex(key);

        // If the slot is empty, insert a new node
        if (table[index] == null) {
            table[index] = new Node<>(key, value);
            size++;
        } else {
            // Linear probing to find the next available slot
            while (table[index] != null) {
                if (table[index].key.equals(key)) {
                    // Update existing key
                    table[index].value = value;
                    return;
                }
                index = (index + 1) % table.length;
            }
            
            // Found an empty slot, insert a new node
            table[index] = new Node<>(key, value);
            size++;
        }

        if (size >= table.length * DEFAULT_LOAD_FACTOR) {
            rehash();
        }
    }

    // Get method to retrieve the value associated with the key
    public V get(K key) {
        int index = findIndex(key);
        while (table[index] != null) {
            if (table[index].key.equals(key)) {
                return table[index].value;
            }
            index = (index + 1) % table.length;
        }
        return null; // Key not found
    }

    // Remove method to delete the key-value pair based on the key
    public V remove(K key) {
        int index = findIndex(key);
        while (table[index] != null) {
            if (table[index].key.equals(key)) {
                V removedValue = table[index].value;
                table[index] = null; // Remove the node
                size--;
                return removedValue;
            }
            index = (index + 1) % table.length;
        }
        return null; // Key not found
    }

    private void rehash() {
        // Double the size of the array and rehash
        Node<K, V>[] oldTable = table;
        table = new Node[table.length * 2];
        size = 0;

        // Reinsert nodes into the new array
        for (Node<K, V> Node : oldTable) {
            if (Node != null) {
                put(Node.key, Node.value);
            }
        }
    }
}



//   QuadraticProbingHashMap is an implementation of a hash map using quadratic probing for collision resolution.
//   It searches for the next available slot using a quadratic function in case of collisions.
//  
//  When to use:
//  - Use QuadraticProbingHashMap when you expect a moderate number of collisions in your hash map.
//  - Well-suited for scenarios with a relatively low load factor and a low likelihood of extensive clustering.
//  - Provides better dispersion compared to linear probing, resulting in reduced clustering.
//  - Efficient for scenarios where key access patterns exhibit moderate locality.


package HashMap;

import java.util.Arrays;

public class QuadraticProbingHashMap<K, V> extends OpenAddressingTechnique<K, V> implements HashMapStrategy<K, V> {

    public QuadraticProbingHashMap() {
        table = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    // Put method to insert or update key-value pair in the hash map
    public void put(K key, V value) {
        int index = findIndex(key);

        // If the slot is empty, insert a new node
        if (table[index] == null) {
            table[index] = new Node<>(key, value);
            size++;
        } else {
            int i = 1;
            // Quadratic probing to find the next available slot
            while (table[index] != null) {
                if (table[index].key.equals(key)) {
                    // Update existing key
                    table[index].value = value;
                    return;
                }
                index = (index + i * i) % table.length;
                i++;
            }
            
            // Found an empty slot, insert a new node
            table[index] = new Node<>(key, value);
            size++;
        }

        if (size >= table.length * DEFAULT_LOAD_FACTOR) {
            rehash();
        }
    }

    // Get method to retrieve the value associated with the key
    public V get(K key) {
        int index = findIndex(key);
        int i = 1;
        while (table[index] != null) {
            if (table[index].key.equals(key)) {
                return table[index].value;
            }
            index = (index + i * i) % table.length;
            i++;
        }
        return null; // Key not found
    }

    // Remove method to delete the key-value pair based on the key
    public V remove(K key) {
        int index = findIndex(key);
        int i = 1;
        while (table[index] != null) {
            if (table[index].key.equals(key)) {
                V removedValue = table[index].value;
                table[index] = null; // Remove the node
                size--;
                return removedValue;
            }
            index = (index + i * i) % table.length;
            i++;
        }
        return null; // Key not found
    }

    private void rehash() {
        // Double the size of the array and rehash
        Node<K, V>[] oldTable = table;
        table = new Node[table.length * 2];
        size = 0;

        // Reinsert nodes into the new array
        for (Node<K, V> Node : oldTable) {
            if (Node != null) {
                put(Node.key, Node.value);
            }
        }
    }
}



//  DoubleHashingHashMap is an implementation of a hash map using double hashing for collision resolution.
//  It uses two hash functions to find the next available slot in case of collisions, providing better distribution.
//  
//  When to use:
//  - Use DoubleHashingHashMap when dealing with scenarios where linear or quadratic probing may lead to clustering.
//  - Well-suited for hash maps with a moderate to high load factor or scenarios prone to clustering.
//  - Provides improved dispersion compared to linear and quadratic probing, reducing clustering effects.
//  - Efficient for scenarios where the key access patterns may lead to a higher likelihood of collisions.

package HashMap;

public class DoubleHashingHashMap<K, V> extends OpenAddressingTechnique<K, V> implements HashMapStrategy<K, V> {

    public DoubleHashingHashMap() {
        table = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    // Put method to insert or update key-value pair in the hash map
    public void put(K key, V value) {
        int index = findIndex(key);

        // If the slot is empty, insert a new node
        if (table[index] == null) {
            table[index] = new Node<>(key, value);
            size++;
        } else {
            // Double hashing to find the next available slot
            int hash2 = doubleHash(key);
            int i = 1;

            while (table[index] != null) {
                index = (index + i * hash2) % table.length;
                i++;
            }

            // Found an empty slot, insert a new node
            table[index] = new Node<>(key, value);
            size++;
        }

        if (size >= table.length * DEFAULT_LOAD_FACTOR) {
            rehash();
        }
    }

    // Get method to retrieve the value associated with the key
    public V get(K key) {
        int index = findIndex(key);
        int hash2 = doubleHash(key);
        int i = 1;

        while (table[index] != null) {
            if (table[index].key.equals(key)) {
                return table[index].value;
            }
            index = (index + i * hash2) % table.length;
            i++;
        }

        return null; // Key not found
    }

    // Remove method to delete the key-value pair based on the key
    public V remove(K key) {
        int index = findIndex(key);
        int hash2 = doubleHash(key);
        int i = 1;

        while (table[index] != null) {
            if (table[index].key.equals(key)) {
                V removedValue = table[index].value;
                table[index] = null; // Remove the node
                size--;
                return removedValue;
            }
            index = (index + i * hash2) % table.length;
            i++;
        }

        return null; // Key not found
    }

    private void rehash() {
        // Double the size of the array and rehash
        Node<K, V>[] oldTable = table;
        table = new Node[table.length * 2];
        size = 0;

        // Reinsert nodes into the new array
        for (Node<K, V> Node : oldTable) {
            if (Node != null) {
                put(Node.key, Node.value);
            }
        }
    }

    // Secondary hash function for double hashing
    private int doubleHash(K key) {
        int hashCode = key.hashCode();
        int tableSize = table.length;

        // Iterate from tableSize to 1 or 2 to find the nearest prime
        for (int i = tableSize; i >= 2; i--) {
            if (isPrime(i)) {
                return i - (hashCode % i);
            }
        }

        // Default to 1 if no prime is found
        return 1;
    }

    // Helper method to check if a number is prime
    private boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}



package HashMap;
public class Main {
    public static void main(String[] args) {
        // HashMapStrategy<String, Integer> hashmap = new ChainingHashMap<>();
        // hashmap.put("akshay", 100);
        // hashmap.put("kunal", 89);
        // hashmap.put("anuj", 88);
        // hashmap.put("nitin", 93);
        // hashmap.put("hardik", 98);
        // System.out.println(hashmap.size()); //5
        // System.out.println(hashmap.get("akshay")); //100
        // hashmap.put("akshay", 110);
        // System.out.println(hashmap.get("akshay")); //110
        // System.out.println(hashmap.remove("anuj")); //88
        // System.out.println(hashmap.size()); //4

        // HashMapStrategy<String, Integer> linearProbingHashMap = new LinearProbingHashMap<>();
        // linearProbingHashMap.put("akshay", 100);
        // linearProbingHashMap.put("kunal", 89);
        // linearProbingHashMap.put("anuj", 88);
        // linearProbingHashMap.put("nitin", 93);
        // linearProbingHashMap.put("hardik", 98);
        // System.out.println(linearProbingHashMap.size()); //5
        // System.out.println(linearProbingHashMap.get("akshay")); //100
        // linearProbingHashMap.put("akshay", 110);
        // System.out.println(linearProbingHashMap.get("akshay")); //110
        // System.out.println(linearProbingHashMap.remove("anuj")); //88
        // System.out.println(linearProbingHashMap.size()); //4

        // HashMapStrategy<String, Integer> quadraticProbingHashMap = new QuadraticProbingHashMap();
        // quadraticProbingHashMap.put("akshay", 100);
        // quadraticProbingHashMap.put("kunal", 89);
        // quadraticProbingHashMap.put("anuj", 88);
        // quadraticProbingHashMap.put("nitin", 93);
        // quadraticProbingHashMap.put("hardik", 98);
        // System.out.println(quadraticProbingHashMap.size()); //5
        // System.out.println(quadraticProbingHashMap.get("akshay")); //100
        // quadraticProbingHashMap.put("akshay", 110);
        // System.out.println(quadraticProbingHashMap.get("akshay")); //110
        // System.out.println(quadraticProbingHashMap.remove("anuj")); //88
        // System.out.println(quadraticProbingHashMap.size()); //4

        HashMapStrategy<String, Integer> doubleHashingHashMap = new DoubleHashingHashMap();
        doubleHashingHashMap.put("akshay", 100);
        doubleHashingHashMap.put("kunal", 89);
        doubleHashingHashMap.put("anuj", 88);
        doubleHashingHashMap.put("nitin", 93);
        doubleHashingHashMap.put("hardik", 98);
        System.out.println(doubleHashingHashMap.size()); //5
        System.out.println(doubleHashingHashMap.get("akshay")); //100
        doubleHashingHashMap.put("akshay", 110);
        System.out.println(doubleHashingHashMap.get("akshay")); //110
        System.out.println(doubleHashingHashMap.remove("anuj")); //88
        System.out.println(doubleHashingHashMap.size()); //4
    }
}


//_______________________________________________________________________________________________________________
https://leetcode.com/problems/two-sum/description/

// Runtime 2ms
// Beats 90.49% of users with Java

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();

        for (int i=0; i<nums.length; i++) {
            int complement = target-nums[i];

            // Check if the complement is already in the HashMap
            if (numMap.containsKey(complement)) {
                // Found a pair, return their indices
                return new int[] { numMap.get(complement), i };
            }
            // Add the current number and its index to the HashMap
            numMap.put(nums[i], i);
        }
        // No valid pair found
        return new int[] {};
    }
}
