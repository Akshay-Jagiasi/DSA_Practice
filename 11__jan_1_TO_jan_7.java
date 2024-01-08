// Problem Statement: Binary Tree Population and Display
// You are tasked with implementing a program to create and display a binary tree.
// The program should take input from the user to populate the binary tree and then display it in a tree-like structure.
package tree;
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
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Binarytree binarytree = new Binarytree();
        binarytree.populate(scanner);
        binarytree.display();
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



//_________________________________________________________________________________________________________________________________
// Problem Statement: Binary Search Tree Operations
// You are tasked with implementing a program that works with a Binary Search Tree (BST).
// The program should include functionality to insert elements into the BST, populate it
// with both sorted and unsorted arrays, check if it is balanced, and display the tree in a visually informative format.

package tree;
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
