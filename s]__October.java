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
