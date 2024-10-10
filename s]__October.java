Q1: https://leetcode.com/problems/removing-stars-from-a-string/description/

// class Solution {
//     public String removeStars(String s) {

//         Stack<Character> stack = new Stack<>();

//         for(char c : s.toCharArray()){
//             if(c == '*'){
//                 if(!stack.isEmpty()){
//                     stack.pop();
//                 }
//             }else{
//                 stack.push(c);
//             }
//         }

//         StringBuilder result = new StringBuilder();
//         while(!stack.isEmpty()){
//             result.append(stack.pop());
//         }

//         return result.reverse().toString();
//     }
// }

// Step-by-Step Example:
// Given: s = "leet**cod*e"

// Initial Stack State: Empty

// Process each character:
// Push 'l' → Stack: ['l']
// Push 'e' → Stack: ['l', 'e']
// Push 'e' → Stack: ['l', 'e', 'e']
// Push 't' → Stack: ['l', 'e', 'e', 't']
// Encounter '*' → Pop 't' → Stack: ['l', 'e', 'e']
// Encounter '*' → Pop 'e' → Stack: ['l', 'e']
// Push 'c' → Stack: ['l', 'e', 'c']
// Push 'o' → Stack: ['l', 'e', 'c', 'o']
// Push 'd' → Stack: ['l', 'e', 'c', 'o', 'd']
// Encounter '*' → Pop 'd' → Stack: ['l', 'e', 'c', 'o']
// Push 'e' → Stack: ['l', 'e', 'c', 'o', 'e']

// Final Stack State: ['l', 'e', 'c', 'o', 'e']


class Solution {
    public String removeStars(String s) {

        StringBuilder result = new StringBuilder();

        for(char c : s.toCharArray()){
            if(c == '*'){
                result.deleteCharAt(result.length() - 1);
            }else{
                result.append(c);
            }
        }

        return result.toString();
    }
}

// Detailed Dry Run:
// Let’s dry run the optimized solution with s = "leet**cod*e":

// Initialization:
// StringBuilder result = new StringBuilder();
// Start iterating through the characters of s.

// Processing Characters:
// 'l' → Append → result = "l"
// 'e' → Append → result = "le"
// 'e' → Append → result = "lee"
// 't' → Append → result = "leet"
// '*' → Remove last character ('t') → result = "lee"
// '*' → Remove last character ('e') → result = "le"
// 'c' → Append → result = "lec"
// 'o' → Append → result = "leco"
// 'd' → Append → result = "lecod"
// '*' → Remove last character ('d') → result = "leco"
// 'e' → Append → result = "lecoe"
// Final Result: Convert result to a string → "lecoe"



//___________________________________________________________________________________________________________________________
Q2: https://leetcode.com/problems/remove-nodes-from-linked-list/

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

// Approach:
// Reverse the linked list: This step makes it easier to traverse the list and remove nodes that are smaller than the largest
// node we've encountered so far.
// Remove nodes: As we traverse the reversed list, we will keep track of the maximum value encountered.
// Any node with a value smaller than this maximum will be removed.
// Reverse the list back: After removing the necessary nodes, we reverse the list again to restore the original order.
class Solution {
    public ListNode removeNodes(ListNode head) {
        ListNode current = head;
        ListNode prev = null;
        
        head = reverse(head);

        ListNode dummy = new ListNode(0); // Create a dummy node to help handle edge cases (e.g., empty lists)
        ListNode newHead = dummy; // `newHead` is the current end of the new list that we are building
        int max = Integer.MIN_VALUE;

        //Traverse through the reversed list
        while(head != null){
            //if the current node value is greater than or equal to max we keep it 
            if(head.val >= max){
                max = head.val; // Update `max` to the value of the current node, as it's the largest seen so far
                newHead.next = head; // Add the current node to the new list
                newHead = newHead.next; // Move `newHead` forward to point to the newly added node
            }
            head = head.next; // Move to the next node in the list
        }

        // Important: Terminate the last node
        newHead.next = null;  // Ensure that the new list ends with a `null` (so there's no leftover link from the original list)

        // Reverse the list again to restore the original order
        return reverse(dummy.next); // Reverse the modified list (starting from `dummy.next`) to restore the correct order
    } 

    private ListNode reverse(ListNode head){
        ListNode prev = null;
        ListNode current = head;

        while(current != null){
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }
}



//Input Linked List:
//5 -> 2 -> 13 -> 3 -> 8 -> null

//Step 1: Reverse the Linked List
//Initially, we reverse the list using the reverse() function.

// Initial State:
// head: 5 -> 2 -> 13 -> 3 -> 8 -> null
// prev: null
// current: 5

// Iteration 1:
// Reverse 5: 5 -> null
// prev: 5 -> null
// current: 2 -> 13 -> 3 -> 8 -> null

// Iteration 2:
// Reverse 2: 2 -> 5 -> null
// prev: 2 -> 5 -> null
// current: 13 -> 3 -> 8 -> null

// Iteration 3:
// Reverse 13: 13 -> 2 -> 5 -> null
// prev: 13 -> 2 -> 5 -> null
// current: 3 -> 8 -> null

// Iteration 4:
// Reverse 3: 3 -> 13 -> 2 -> 5 -> null
// prev: 3 -> 13 -> 2 -> 5 -> null
// current: 8 -> null

// Iteration 5:
// Reverse 8: 8 -> 3 -> 13 -> 2 -> 5 -> null
// prev: 8 -> 3 -> 13 -> 2 -> 5 -> null
// current: null (end of the list)

//State after the first reversal:
// Reversed list: 
// 8 -> 3 -> 13 -> 2 -> 5 -> null


//Step 2: Traverse the Reversed List and Remove Nodes
//Now, we traverse the reversed list and build a new list by removing nodes with values smaller than the largest value encountered so far.

// Initial State:
// head: 8 -> 3 -> 13 -> 2 -> 5 -> null
// max = Integer.MIN_VALUE (initial maximum value is very low)
// dummy = 0 -> null (dummy node helps with edge cases)
// newHead = dummy

// Iteration 1:
// head.val (8) >= max (-∞) -> true, add 8 to new list
// new list: 0 -> 8 -> null
// Update max = 8
// newHead: 8
// Move head to the next node: 3 -> 13 -> 2 -> 5 -> null

// Iteration 2:
// head.val (3) >= max (8) -> false, skip node 3
// new list: 0 -> 8 -> null
// max = 8 (unchanged)
// Move head to the next node: 13 -> 2 -> 5 -> null

// Iteration 3:
// head.val (13) >= max (8) -> true, add 13 to new list
// new list: 0 -> 8 -> 13 -> null
// Update max = 13
// newHead: 13
// Move head to the next node: 2 -> 5 -> null

// Iteration 4:
// head.val (2) >= max (13) -> false, skip node 2
// new list: 0 -> 8 -> 13 -> null
// max = 13 (unchanged)
// Move head to the next node: 5 -> null

// Iteration 5:
// head.val (5) >= max (13) -> false, skip node 5
// new list: 0 -> 8 -> 13 -> null
// max = 13 (unchanged)
// Move head to the next node: null (end of the list)

//State after traversal:
// Filtered list (in reversed order):
// dummy: 0 -> 8 -> 13 -> null


//Step 3: Reverse the Filtered List Again
//We reverse the filtered list back to its original order using the reverse() function.
// Initial State:
// head (dummy.next): 8 -> 13 -> null
// prev: null
// current: 8

// Iteration 1:
// Reverse 8: 8 -> null
// prev: 8 -> null
// current: 13 -> null

// Iteration 2:
// Reverse 13: 13 -> 8 -> null
// prev: 13 -> 8 -> null
// current: null (end of the list)

//State after the final reversal:
// Final output list:
// 13 -> 8 -> null

//Full Code Flow Visualized
//Initial List:
// 5 -> 2 -> 13 -> 3 -> 8 -> null

// After First Reversal:
// 8 -> 3 -> 13 -> 2 -> 5 -> null

// After Traversal and Removal:
// Nodes 3, 2, and 5 are removed since they are smaller than the largest node seen so far.
// 8 -> 13 -> null

// After Second Reversal (Final Output):
// 13 -> 8 -> null


//___________________________________________________________________________________________________________________________
Q3: https://leetcode.com/problems/container-with-most-water/description/

class Solution {
    public int maxArea(int[] height) {

        int n = height.length;
        int left = 0;
        int right = n-1;
        int maxArea = 0;

        while(left < right){
            // Calculate the area between the lines at 'left' and 'right'
            int currentArea = Math.min(height[left], height[right]) * (right - left);
           
            maxArea = Math.max(maxArea, currentArea);
            
            if(height[left] < height[right]){
                left++;
            }else{
                right--;
            }
        }
        return maxArea;
    }
}



//___________________________________________________________________________________________________________________________
Q4: https://leetcode.com/problems/max-number-of-k-sum-pairs/description/

class Solution {
    public int maxOperations(int[] nums, int k) {

        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        int pairs = 0;

        while(left < right){
            int currentSum = nums[left] + nums[right];
            if(currentSum == k){
                pairs++;
                left++;
                right--;
            }else if(currentSum < k){
                left++;
            }else{
               right--;
            }
        }

        return pairs;
    }
}



//___________________________________________________________________________________________________________________________
Q5: https://leetcode.com/problems/string-compression/description/

class Solution {
    public int compress(char[] chars) {
        int index = 0; //where compressed version will be written 
        int i = 0; //Pointer to traverse the array 

        while(i < chars.length){
            char currentChar = chars[i];
            int count = 0;

            // Count how many times the current character repeats
            while(i < chars.length && chars[i] == currentChar){
                i++;
                count++;
            } 

            // Write the character itself to the compressed array
            chars[index++] = currentChar;

            // If the character occurs more than once, write the count
            if(count > 1){
                // Convert the count to characters and append them
                for(char c : String.valueOf(count).toCharArray()){
                    chars[index++] = c;
                }
            }
        }
        return index; // This is the new length of the compressed array
    }
}



//___________________________________________________________________________________________________________________________
Q6: https://leetcode.com/problems/increasing-triplet-subsequence/description/

// Iteration: It then iterates through each number in the input array nums.
// Update first: If the current number is less than first, it updates first to this current number.
// Update second: If the current number is greater than first but less than second,
// it updates second to this current number.
// Found Triplet: If the current number is greater than both first and second,
// it returns true, indicating that an increasing triplet has been found. If the loop ends without finding a triplet, it returns false.

class Solution {
    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for(int num : nums){

            if(num <= first){
                first = num;
            }else if(num <= second){
                second = num;
            }else{
                return true;
            }
        }

        return false;
    }
}

/*
Iteration    num          Action                                             Updated Values
1            5            num < first is true, update first to 5          first = 5, second = Integer.MAX_VALUE
2            1            num < first is true, update first to 1          first = 1, second = Integer.MAX_VALUE
3            5            num < second is true, update second to 5        first = 1, second = 5
4            5            Neither condition is true, continue             first = 1, second = 5
5            2            num < second is true, update second to 2        first = 1, second = 2
6            5            num > second, return true (found triplet)       Returns true
*/



//___________________________________________________________________________________________________________________________
Q7: https://leetcode.com/problems/asteroid-collision/description/

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        // Stack to keep track of surviving asteroids
        Stack<Integer> stack = new Stack<>();

        // Loop through each asteroid in the input array
        for(int asteroid : asteroids){

            // Boolean flag to track if the current asteroid collided
            boolean collided = false;

            // Check for potential collisions:
            // - There is a potential for collision if the stack is not empty
            // - The current asteroid is moving left (i.e., asteroid < 0)
            // - The top of the stack asteroid is moving right (i.e., stack.peek() > 0)
            while(!stack.isEmpty() && asteroid < 0 && stack.peek() > 0){

                // Peek at the top asteroid from the stack (the right-moving asteroid)
                int top = stack.peek();

                // Case 1: The absolute size of the current left-moving asteroid is greater than the size of the right-moving asteroid at the top of the stack
                if(Math.abs(asteroid) > top){
                    // Current left-moving asteroid destroys the right-moving one (pop it from the stack)  
                    stack.pop();
                    // Note: We do not set `collided = true` because the current asteroid is still active and might continue to destroy more right-moving asteroids
                
                // Case 2: Both asteroids have the same size
                }else if(Math.abs(asteroid) == top){
                    // Both asteroids destroy each other
                    stack.pop(); // Remove the top asteroid
                    collided = true; // Set flag to true, as the current asteroid also gets destroyed
                    break; // Exit the loop since the current asteroid no longer exists
                
                // Case 3: The top asteroid is larger than the current one
                }else{
                    // The right-moving asteroid survives, and the current asteroid is destroyed
                    collided = true;  // Mark the current asteroid as destroyed
                    break; // Exit the loop since no further checks are necessary
                }
            }
            if(!collided){ // If no collision happened (either the stack is empty or all right-moving asteroids were destroyed), we push the current asteroid onto the stack
                stack.push(asteroid); // Add the surviving asteroid (either right-moving or left-moving) to the stack
            }
        }

        int[] result = new int[stack.size()];
        // Pop elements from the stack (LIFO) and place them in the result array in reverse order (from last added to first added)
        for(int i = result.length-1; i >= 0; i--){
            result[i] = stack.pop();
        }
        
        return result;
    }
}



//___________________________________________________________________________________________________________________________
Q8: https://leetcode.com/problems/min-stack/description/

/*
     * Summary of how minStack works:
     *
     * 1. The main stack holds all elements.
     * 2. The minStack only holds the current minimums. It helps retrieve the minimum value in O(1) time.
     * 3. Whenever a value is pushed onto the main stack, it is compared with the current minimum (top of minStack):
     *    - If the value is less than or equal to the current minimum, it's pushed to the minStack as well.
     *    - If the value is greater than the current minimum, it doesn't affect the minStack.
     * 4. When popping an element, if the element being popped is equal to the top of the minStack (i.e., the current minimum),
     *    we also pop from the minStack to update the minimum correctly.
     *
     * Why compare mainStack.top() with minStack.top()?
     * - We compare the top of the main stack with the top of the minStack during a pop operation to check if we're
     *   removing the current minimum from the main stack. If so, we also remove it from minStack to keep both stacks in sync.
     * - The minStack only contains elements that are the current minimum at some point in time.
     *
     * Edge case: If we have multiple values of the same minimum (e.g., multiple -2's), both will be pushed to minStack.
     * When popping, they will be removed one by one, keeping the minimum accurate.
*/

class MinStack {
   
    // Main stack to hold the values
    private Stack<Integer> stack;
    // Auxiliary stack to hold the minimum values
    private Stack<Integer> minStack;


    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int val) {
        stack.push(val);
        if(minStack.isEmpty() || val <= minStack.peek()){
            minStack.push(val);
        }
    }
    
    public void pop() {
        int topValue = stack.pop();
        if(topValue == minStack.peek()){
            minStack.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */



//___________________________________________________________________________________________________________________________
Q9: https://leetcode.com/problems/design-a-stack-with-increment-operation/description/

class CustomStack {

    private int[] stack; // Main Stack
    private int[] increment; //Array to store increments
    private int size; //current size of the stack
    private int maxSize; //maximum size of the stack

    public CustomStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
        this.increment = new int[maxSize];
        this.size = 0;
    }
    
    public void push(int x) {
        if(size < maxSize){
            stack[size] = x;
            size++;
        }
    }
    
    public int pop() {
        if(size == 0){
            return -1;
        }
        size--; // Decrease the size as popping

        // Apply the increment at this level
        int result = stack[size] + increment[size];

        // If there's an increment, propagate it to the previous element
        if(size > 0){
            increment[size - 1] = increment[size - 1] + increment[size];
        }


        // Reset the increment for the current position since it's applied
        increment[size] = 0;

        return result;
    }
    
    public void increment(int k, int val) {
        int index = Math.min(k, size) - 1; // Increment up to min(k, size) elements
        if(index >= 0){
            increment[index] += val; // Add the increment to the k-th element (index k-1)
        }
    }
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */



//___________________________________________________________________________________________________________________________
Q10: https://leetcode.com/problems/add-two-numbers/description/

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
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0); // Dummy node to make handling the list easier
        ListNode current = dummyHead; // Pointer to construct the result list
        int carry = 0; // Carry for digits exceeding 9

        while(l1 != null || l2 != null || carry != 0){ // Loop until both lists are done and no carry remains
            int sum = carry; // Start with the carry from the previous step

            // If l1 has a node, add its value to sum
            if(l1 != null){
                sum = sum + l1.val;
                l1 = l1.next; // Move to the next node in l1
            }
            
            // If l2 has a node, add its value to sum
            if(l2 != null){
                sum = sum + l2.val;
                l2 = l2.next; // Move to the next node in l2
            }

            carry = sum / 10; // Calculate the carry (sum >= 10 means carry is 1)
            int newVal = sum % 10; // The current digit for the result (0-9)

            current.next = new ListNode(newVal); // Create a new node with the current digit
            current = current.next; // Move to the next node in the result list
        }

        return dummyHead.next; // Return the result list starting from the node after dummy
    }
}


//___________________________________________________________________________________________________________________________
Q11: https://leetcode.com/problems/balanced-binary-tree/description/

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
    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private int checkHeight(TreeNode node){
        if(node == null){
            return 0; // Base case: height of an empty subtree is 0
        }

        // Recursively get the height of the left subtree
        int leftHeight = checkHeight(node.left);
        if(leftHeight == -1) return -1;
    
        // Recursively get the height of the right subtree
        int rightHeight = checkHeight(node.right);
        if(rightHeight == -1) return -1;

        // If the current node is unbalanced, return -1
        if(Math.abs(leftHeight - rightHeight) > 1){
            return -1;
        }

        // Otherwise, return the height of the current node
        return 1 + Math.max(leftHeight, rightHeight);
    }    
}



//___________________________________________________________________________________________________________________________
Q12: https://leetcode.com/problems/reverse-integer/description/

// Extracting Digits: The number x is processed digit by digit using x % 10 to extract the last digit and x = x / 10 to remove it from x.
// Overflow Check: Before multiplying result by 10, the code checks whether doing so will cause the value to exceed INT_MAX. If it does, return 0.
// Underflow Check: Similarly, it checks if multiplying result by 10 will cause it to drop below INT_MIN. If it does, return 0.
// Reversing: For each extracted digit, the code appends the digit to the reversed result by multiplying the current result by 10 and adding the digit.

/*
Explanation of overflow and underflow checks:

Overflow Check:
1. If result > INT_MAX / 10 (214748364), multiplying by 10 will overflow.
2. If result == INT_MAX / 10 and the next digit > 7, adding it will overflow.
   - Example: result = 214748364, next digit = 8 -> result * 10 + 8 = 2147483648 (overflow).

Underflow Check:
1. If result < INT_MIN / 10 (-214748364), multiplying by 10 will underflow.
2. If result == INT_MIN / 10 and the next digit < -8, adding it will underflow.
   - Example: result = -214748364, next digit = -9 -> result * 10 + (-9) = -2147483649 (underflow).
*/


class Solution {
    public int reverse(int x) {
        int INT_MIN = Integer.MIN_VALUE;
        int INT_MAX = Integer.MAX_VALUE;

        int result = 0;

        while(x != 0){
            int digit = x % 10; // extract the last digit
            x = x / 10; // Remove the last digit

            // Check for overflow before multiplying result by 10
            if(result > INT_MAX / 10 || (result == INT_MAX / 10 && digit > 7)){
                return 0;  // overflow case
            }

            if(result < INT_MIN / 10 || (result == INT_MIN / 10 && digit < -8)){
                return 0;  // underflow case
            }

            result = result * 10 + digit;
        }

        return result;
    }
}



//___________________________________________________________________________________________________________________________
Q13: https://leetcode.com/problems/monotonic-array/description/

class Solution {
    public boolean isMonotonic(int[] nums) {
        boolean isIncreasing = true;  // Flag to check for monotonic increasing
        boolean isDecreasing = true;  // Flag to check for monotonic decreasing
        
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                isIncreasing = false;  // If current element is greater than next, it's not increasing
            }
            if (nums[i] < nums[i + 1]) {
                isDecreasing = false;  // If current element is less than next, it's not decreasing
            }
        }
        
        return isIncreasing || isDecreasing;
    }
}



//___________________________________________________________________________________________________________________________
Q14: https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/description/

class Solution {
    public int maxVowels(String s, int k) {
        int maxVowels =  0;
        int currentVowelCount = 0;

        for(int i = 0; i < s.length(); i++){

            // If the current character is a vowel, increment the current vowel count
            if(isVowel(s.charAt(i))){
                currentVowelCount++;
            }

            // Once we have processed 'k' characters, start sliding the window
            // If the character that is leaving the window (i - k) is a vowel, 
            // decrement the current vowel count
            if(i >= k && isVowel(s.charAt(i-k))){
                currentVowelCount--;
            }

            maxVowels = Math.max(maxVowels, currentVowelCount);
        }

        return maxVowels;
    }

    private boolean isVowel(char c){
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}