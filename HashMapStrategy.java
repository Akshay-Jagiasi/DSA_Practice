// ---------- HashMapStrategy Interface----------
// - Defines common methods (`put`, `get`, `remove`, `size`).
// - Contains constants (`DEFAULT_CAPACITY`, `DEFAULT_LOAD_FACTOR`).

//  ----------ChainingHashMap:----------
// - Implements collision handling using chaining (linked lists).
// - Contains methods: `put`, `get`, `remove`, `size`, `rehash`.
// - Proper initialization and rehashing logic.

//  ----------OpenAddressingTechnique Abstract Class:----------
// - Defines common properties and methods for open addressing techniques.
// - Contains a `Node` class for storing key-value pairs.
// - Includes `findIndex` and `size` methods.

//  ----------LinearProbingHashMap:----------
// - Extends `OpenAddressingTechnique` and implements `HashMapStrategy`.
// - Uses linear probing for collision resolution.
// - Contains methods: `put`, `get`, `remove`, `rehash`.

//  ----------QuadraticProbingHashMap:----------
// - Extends `OpenAddressingTechnique` and implements `HashMapStrategy`.
// - Uses quadratic probing for collision resolution.
// - Contains methods: `put`, `get`, `remove`, `rehash`.

//  ----------DoubleHashingHashMap:----------
// - Extends `OpenAddressingTechnique` and implements `HashMapStrategy`.
// - Uses double hashing for collision resolution.
// - Contains methods: `put`, `get`, `remove`, `rehash`.
// - Includes a secondary hash function and a helper method to check for prime numbers.


 
//                      +----------------------+
//                      | HashMapStrategy<K, V>|
//                      +----------------------+
//                      | + DEFAULT_CAPACITY   |
//                      | + DEFAULT_LOAD_FACTOR|
//                      | + put(K, V)          |
//                      | + get(K)             |
//                      | + remove(K)          |
//                      | + size()             |
//                      +----------------------+
//                                 ^
//                                 |
//                                 |
//                                 |
//          +----------------------+------------------------+
//          |                      |                        |
//      implements                 |                    implements
//          |                      |                        |       
// +-------------------------+     |       +------------------------------------+
// | ChainingHashMap<K, V>   |     |       | OpenAddressingTechnique<K, V>      |
// +-------------------------+     |       +------------------------------------+
// | - n                     |     |       | - Node<K, V>[] table               |
// | - LinkedList<Node>[]    |     |       | - size                             |
// | + put(K, V)             |     |       | + findIndex(K)                     |
// | + get(K)                |     |       | + size()                           |
// | + remove(K)             |     |       | + put(K, V)                        |
// | + size()                |     |       | + get(K)                           |
// +-------------------------+     |       | + remove(K)                        |
//                                 |       | + rehash()                         |
//                                 |       +------------------------------------+
//                                 |                          |
//                            implements                   extends
//                                 |                          |
//                +----------------+--------------------------+--------------------------------------------+
//                |                                           |                                            |
//                |                                           |                                            |
//             +-----------------------------+  +-----------------------------+  +-----------------------------+
//             | LinearProbingHashMap<K, V>  |  | QuadraticProbingHashMap<K,V>|  | DoubleHashingHashMap<K, V>  |
//             +-----------------------------+  +-----------------------------+  +-----------------------------+
//             | + put(K, V)                 |  | + put(K, V)                 |  | + put(K, V)                 |
//             | + get(K)                    |  | + get(K)                    |  | + get(K)                    |
//             | + remove(K)                 |  | + remove(K)                 |  | + remove(K)                 |
//             | + rehash()                  |  | + rehash()                  |  | + rehash()                  |
//             +-----------------------------+  +-----------------------------+  +-----------------------------+




package HashMap;
//K => key            V => value
interface HashMapStrategy<K, V> {
    // 'public' means this field is accessible from other classes.
    // 'static' means this field belongs to the class itself rather than any instance of the class.
    // 'final' means the value of this field cannot be changed once assigned.
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
    // LinkedList<Node>[]: Type of the field, an array of LinkedList objects, each storing Node elements.
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



