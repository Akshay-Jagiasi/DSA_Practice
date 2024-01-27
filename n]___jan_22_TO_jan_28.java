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