Q1: https://leetcode.com/problems/invert-binary-tree/description/

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

Runtime 0ms
Beats 100.00% of users with Java

class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;

        return root;
    }
}



//__________________________________________________________________________________________________________________________________________
Q2: https://leetcode.com/problems/maximum-depth-of-binary-tree/description/

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

 Runtime 0ms
 Beats 100.00% of users with Java

class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        int depth = Math.max(left, right) + 1;

        return depth;
    }
}



//__________________________________________________________________________________________________________________________________________
Q3: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/

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

 Runtime 0ms
Beats 100.00% of users with Java

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBSTHelper(int[] nums, int left, int right){
        //base case
        if(left > right){
            return null;
        }

        int mid = left + (right - left)/2;

        //create a new TreeNode with the middle element
        TreeNode root = new TreeNode(nums[mid]);

        //recursively build the left and right subtree
        root.left = sortedArrayToBSTHelper(nums, left, mid - 1);
        root.right = sortedArrayToBSTHelper(nums, mid + 1, right);

        return root;
    }
}



//__________________________________________________________________________________________________________________________________________
Q4: https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/

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

 Runtime 0ms
Beats 100.00% of users with Java

class Solution {
    public void flatten(TreeNode root) {
        TreeNode current = root;
        while(current != null){
            if(current.left != null){
                TreeNode temp = current.left;
                while(temp.right != null){
                    temp = temp.right;
                }

                temp.right = current.right;
                current.right = current.left;
                current.left = null;
            }
            current = current.right;
        }
    }
}



//__________________________________________________________________________________________________________________________________________
Q5: https://leetcode.com/problems/validate-binary-search-tree/description/

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

 Runtime 0ms
Beats 100.00% of users with Java

//for future if you are not understanding the code draw the tree and debug it step by step
class Solution {
    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    public boolean helper(TreeNode node, Integer low, Integer high) {
    if (node == null) {
      return true;
    }

    if (low != null && node.val <= low) {
      return false;
    }

    if(high != null && node.val >= high) {
      return false;
    }

    boolean leftTree = helper(node.left, low, node.val);
    boolean rightTree = helper(node.right, node.val, high);

    return leftTree && rightTree;
    }
}



//__________________________________________________________________________________________________________________________________________
Q6: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 Runtime 6ms
Beats 98.27% of users with Java

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return null;
        }   

        if(root == p || root == q){
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left != null && right != null){
            return root;
        }

        return left == null ? right : left;
    }
}



//__________________________________________________________________________________________________________________________________________
Q7: https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/

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

 Runtime 0ms
Beats 100.00% of users with Java

class Solution {
    int count = 0;
    public int kthSmallest(TreeNode root, int k) {
        return helper(root, k).val;
    }

    public TreeNode helper(TreeNode root, int k){
        if(root == null){
            return null;
        }

        TreeNode left = helper(root.left, k);

        if(left != null){
            return left;
        }

        count++;

        if(count == k){
            return root;
        }

        return helper(root.right, k);
    }
}



//__________________________________________________________________________________________________________________________________________
Q8: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/

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

 Runtime 7ms
Beats 15.28% of users with Java
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0){
            return null;
        }

        int r = preorder[0];
        int index = 0;

        for(int i=0; i<inorder.length; i++){
            if(inorder[i] == r){
                index = i;
            }
        }

        TreeNode node = new TreeNode(r);

        node.left =  buildTree(Arrays.copyOfRange(preorder, 1, index+1),Arrays.copyOfRange(inorder, 0, index)); 
        node.right =  buildTree(Arrays.copyOfRange(preorder, index+1, inorder.length),Arrays.copyOfRange(inorder, index+1, inorder.length)); 

        return node;
    }
}


//optimized using HashMap
Runtime 1ms
Beats 95.55% of users with Java

class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, inorder, 0, 0, inorder.length - 1, indexMap);
    }
    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, int preStart, int inStart, int inEnd, Map<Integer, Integer> indexMap) {
        if (preStart >= preorder.length || inStart > inEnd) {
            return null;
        }
        int rootValue = preorder[preStart];
        TreeNode root = new TreeNode(rootValue);
        int rootIndexInorder = indexMap.get(rootValue);
        root.left = buildTreeHelper(preorder, inorder, preStart + 1, inStart, rootIndexInorder - 1, indexMap);
        root.right = buildTreeHelper(preorder, inorder, preStart + rootIndexInorder - inStart + 1, rootIndexInorder + 1, inEnd, indexMap);
        return root;
    }
}



//__________________________________________________________________________________________________________________________________________
Q9: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 Runtime 11ms
 Beats 78.41% of users with Java 

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("null,");
        } else {
            sb.append(node.val).append(",");
            serializeHelper(node.left, sb);
            serializeHelper(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
         List<String> nodeList = new LinkedList<>(Arrays.asList(data.split(",")));
        Collections.reverse(nodeList);
        return deserializeHelper(nodeList);
    }

    private TreeNode deserializeHelper(List<String> nodeList) {
        String val = nodeList.remove(nodeList.size() - 1);

        if (val.equals("null")) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(val));

        node.left = deserializeHelper(nodeList);
        node.right = deserializeHelper(nodeList);

        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));



//__________________________________________________________________________________________________________________________________________
Q10: https://leetcode.com/problems/path-sum/description/

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

 Runtime 0ms
 Beats 100.00% of users with Java 

class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null){
            return false;
        }

        if(root.val == targetSum && root.left == null && root.right == null){
            return true;
        }

        return hasPathSum(root.left, targetSum-root.val) || hasPathSum(root.right, targetSum-root.val); 
    }
}



//__________________________________________________________________________________________________________________________________________
Q11: https://leetcode.com/problems/sum-root-to-leaf-numbers/description/

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

 Runtime 0ms
 Beats 100.00% of users with Java

class Solution {
    public int sumNumbers(TreeNode root) {
       return helper(root, 0); 
    }

    public int helper(TreeNode node, int sum){
        if(node == null){
            return 0;
        }

        sum = sum * 10 + node.val;

        if(node.left == null && node.right == null){
            return sum;
        }

        return helper(node.left, sum) + helper(node.right, sum);
    }
}



//__________________________________________________________________________________________________________________________________________
Q12: https://leetcode.com/problems/binary-tree-maximum-path-sum/description/

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

 Runtime 0ms
 Beats 100.00% of users with Java

//In future draw the tree and debug the code for understanding the working
class Solution {
    int ans = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        helper(root);
        return ans;
    }

    int helper(TreeNode node){
        if(node == null){
            return 0;
        }

        int left = helper(node.left);
        int right = helper(node.right);

        //By using Math.max(0, left) and Math.max(0, right), the algorithm ensures that if the contribution from a branch is negative, it is replaced with 0.
        //This effectively prevents negative contributions from being added to the path sum calculation.
        left = Math.max(0, left);
        right = Math.max(0, right);

        int pathSum = left + right + node.val;
        ans = Math.max(ans, pathSum);

        return Math.max(left, right) + node.val;
    }
}

//__________________________________________________________________________________________________________________________________________
Q13:// Problem Statement: Path Existence in Binary Tree
// You are given a binary tree where each node has an integer value.
// Write a function boolean findPath(TreeNode root, int[] arr) to determine
// whether a given array arr forms a valid path in the binary tree.
// A valid path in the binary tree is a sequence of nodes from the root to a leaf node,
// where the values in the sequence match the values in the array arr from the root to the leaf.

//Example:
// Tree:
//        3
//       / \
//      5   9
//         / \
//       10   12
//      /     /
//    16      8

// Array: [3, 9, 12, 8]
// Output: true

//Array: [3, 9, 12, 16]
//Output: false

boolean findPath(Node node, int[] arr){
    if(node == null){
        return arr.length == 0;
    }
    return helper(node, arr, 0);
}

boolean helper(Node node, int[] arr, int index){
    if(node == null){
        return false;
    }

    if(index >= arr.length || node.val != arr[index]){
        return false;
    }

    if(node.left == null && node.right == null && index == arr.length-1){
        return true;
    }

    return helper(node.left, arr, index+1) || helper(node.right, arr, index+1);
}



//__________________________________________________________________________________________________________________________________________
Q14://Problem Statement: Find Paths start from root and end at leaf node with Given Sum 
// Given a binary tree where each node has an integer value,
// write a function findPaths to find and return a list of all paths in the tree that sum to a given target value.
// However, in this problem, paths should specifically start from the root and end at a leaf node.
// Example:
// Tree:
//           1
//          / \
//         3   2
//            / \
//           1   3
//          /   /
//        11   2
//              \
//               2
//              /
//            16
// targetSum = 4
//OUTPUT: [[1,3]]

List<List<Integer>> findPaths(Node node, int sum) {
    // Initialize a list to store all found paths
    List<List<Integer>> paths = new ArrayList<>();
    // Initialize a list to represent the current path
    List<Integer> path = new ArrayList<>();
    helper(node, sum, path, paths);
    return paths;
}

void helper(Node node, int sum, List<Integer> path, List<List<Integer>> paths) {
    if (node == null) {
        return;
    }

    // Add the current node's value to the current path
    path.add(node.val);

    // Check if the current node is a leaf and the path sum equals the target sum
    if (node.val == sum && node.left == null && node.right == null) {
        // Add a copy of the current path to the list of paths
        paths.add(new ArrayList<>(path));
    } else {
        // Recursively explore the left subtree with the updated path
        helper(node.left, sum - node.val, new ArrayList<>(path), paths);
        // Recursively explore the right subtree with the updated path
        helper(node.right, sum - node.val, new ArrayList<>(path), paths);
    }

    // Backtrack: remove the last element from the current path
    path.remove(path.size() - 1);
}



//__________________________________________________________________________________________________________________________________________
Q15:// Problem Statement: Path Sum Count | all possible paths count
// Given a binary tree where each node has an integer value, write a function countPaths
// to find and return the number of paths in the tree that sum to a given target value.

// Example:
// Tree:
//           1
//          / \
//         3   2
//            / \
//           1   3
//          /   /
//        11   2
//              \
//               2
//              /
//            16
// targetSum = 4
//OUTPUT: 3 explanation:<1,3>, <1,2,1>, <2,2> 3 paths are there whose sum is equals to targetSum

int countPaths(Node node, int sum) {
    // Initialize an empty list to store the current path
    List<Integer> path = new ArrayList<>();
    return helper(node, sum, path);
}

int helper(Node node, int sum, List<Integer> path) {
    if (node == null) {
        return 0;
    }

    path.add(node.val);
    int count = 0;
    int runningSum = 0;
    // Create a list iterator starting from the end of the path
    ListIterator<Integer> itr = path.listIterator(path.size());

    // Iterate through the path from the end to the beginning
    while (itr.hasPrevious()) {
        // Update the running sum
        runningSum += itr.previous();
        // If the running sum equals the target sum, increment the count
        if (runningSum == sum) {
            count++;
        }
    }

    // Recursively explore left and right subtrees and add their counts to the overall count
    count += helper(node.left, sum, path) + helper(node.right, sum, path);

    // Backtrack by removing the last element from the path
    path.remove(path.size() - 1);

    // Return the total count of valid paths
    return count;
}



//__________________________________________________________________________________________________________________________________________
Q16:// Problem Statement: Find Paths start at any node and end at any node 
// Given a binary tree where each node has an integer value,
// write a function countPaths to find and return a list of all paths in the tree that sum to a given target value.
// Important Notes:
// Paths can start at any node and end at any node.
// The paths should be returned as a list of lists, where each inner list represents a path from any node to any node whose values sum to the target value.
// Example:
// Tree:
//           1
//          / \
//         3   2
//            / \
//           1   3
//          /   /
//        11   2
//              \
//               2
//              /
//            16
// targetSum = 4
//OUTPUT: [[1,3],[1,2,1],[2,2]]
List<List<Integer>> allPaths(Node node, int sum) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    helper(node, sum, path, result);
    return result;
}

void helper(Node node, int sum, List<Integer> path, List<List<Integer>> result) {
    if (node == null) {
        return;
    }

    path.add(node.val);
    int runningSum = 0;
    ListIterator<Integer> itr = path.listIterator(path.size());

    while (itr.hasPrevious()) {
        runningSum += itr.previous();
        if (runningSum == sum) {
            // Found a path, add a copy of the current path to the result
            result.add(new ArrayList<>(path));
        }
    }

    helper(node.left, sum, new ArrayList<>(path), result);
    helper(node.right, sum, new ArrayList<>(path), result);

    path.remove(path.size() - 1);
} 



//__________________________________________________________________________________________________________________________________________
Q17:// Problem Statement: Traversals using a Stack
// Given a binary tree, implement three different depth-first traversal algorithms using a stack: In-Order, Pre-Order, and Post-Order.

// In-order traversal using a stack
void inOrderDFSStack(Node node) {
    if (node == null) {
        return;
    }
    Stack<Node> stack = new Stack<>();
    Node current = node;

    while (current != null || !stack.isEmpty()) {
        while (current != null) {
            stack.push(current);
            current = current.left;
        }
        current = stack.pop();
        System.out.print(current.val + " ");
        current = current.right;
    }
}

// Pre-order traversal using a stack
void preOrderDFSStack(Node node) {
    if (node == null) {
        return;
    }
    Stack<Node> stack = new Stack<>();
    stack.push(node);

    while (!stack.isEmpty()) {
        Node removed = stack.pop();
        System.out.print(removed.val + " ");
        if (removed.right != null) {
            stack.push(removed.right);
        }
        if (removed.left != null) {
            stack.push(removed.left);
        }
    }
}

// Post-order traversal using a stack
void postOrderDFSStack(Node node) {
    if (node == null) {
        return;
    }
    Stack<Node> stack1 = new Stack<>();
    Stack<Node> stack2 = new Stack<>();

    stack1.push(node);

    while (!stack1.isEmpty()) {
        Node removed = stack1.pop();
        stack2.push(removed);

        if (removed.left != null) {
            stack1.push(removed.left);
        }
        if (removed.right != null) {
            stack1.push(removed.right);
        }
    }

    while (!stack2.isEmpty()) {
        System.out.print(stack2.pop().val + " ");
    }
}



//__________________________________________________________________________________________________________________________________________
Q18: https://leetcode.com/problems/reverse-linked-list/description/

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

 Runtime 0ms
 Beats 100.00% of users with Java

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



//__________________________________________________________________________________________________________________________________________
Q19: https://leetcode.com/problems/reverse-linked-list-ii/description/

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

 Runtime 0ms
 Beats 100.00% of users with Java

 //for future draw the linkedlist and debug the code for the understanding
 class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }

        // skip the first left-1 nodes
        ListNode current = head;
        ListNode prev = null;
        for (int i = 0; current != null && i < left - 1; i++) {
            prev = current;
            current = current.next;
        }

        ListNode last = prev;
        ListNode newEnd = current;

        // reverse between left and right
        ListNode next = current.next;
        for (int i = 0; current != null && i < right - left + 1; i++) {
            current.next = prev;
            prev = current;
            current = next;
            if (next != null) {
                next = next.next;
            }
        }

        if (last != null) {
            last.next = prev;
        } else {
            head = prev;
        }

        newEnd.next = current;
        return head;
    }
}



//__________________________________________________________________________________________________________________________________________
Q20: https://leetcode.com/problems/palindrome-linked-list/description/

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

Runtime 6ms
Beats 53.63% of users with Java

class Solution {
    public boolean isPalindrome(ListNode head) {
      ListNode mid = middleNode(head);
      ListNode headSecond = reverseList(mid);
      ListNode rereverseHead = headSecond;

      // compare both the halves
      while (head != null && headSecond != null) {
          if (head.val != headSecond.val) {
              break;
          }
          head = head.next;
          headSecond = headSecond.next;
      }
      reverseList(rereverseHead);

      return head == null || headSecond == null;
  }

  public ListNode middleNode(ListNode head) {
      ListNode s = head;
      ListNode f = head;

      while (f != null && f.next != null) {
          s = s.next;
          f = f.next.next;
      }
      return s;
  }

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