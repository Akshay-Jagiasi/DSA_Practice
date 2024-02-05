Q: https://leetcode.com/problems/largest-rectangle-in-histogram/description/

//Time Complexity  O(n^2)
//Time Limit Exceeded at 91 testcase
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;

        // Iterate through each bar in the histogram
        for(int i=0; i<n; i++){
            int minHeight = heights[i];

            // Extend to the left
            for(int j=i; j>=0; j--){
                // Update the minHeight with the minimum height encountered
                minHeight = Math.min(minHeight, heights[j]);

                // Calculate the width and area of the rectangle
                int width = i - j + 1;
                int area = minHeight * width;

                // Update the maxArea if the current rectangle has a larger area
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }
}

Runtime 121ms
Beats 16.96% of users with Java

//Time Complexity O(n)
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;

        int[] left = new int[n];
        int[] right = new int[n];

        Stack<Integer> stack = new Stack<>();

        // Calculate the indices of the smaller elements to the left of each bar        
        for(int i=0; i<n; i++){
            if(stack.isEmpty()){
                left[i] = 0;// No smaller element to the left
            }else{
                while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                    stack.pop(); // Pop indices of elements greater than or equal to the current bar
                }
                left[i] = stack.isEmpty() ? 0 : stack.peek() + 1;
            }
            stack.push(i); // Push the current bar's index
        }

        stack.clear();// Clear the stack for re-use

        // Calculate the indices of the smaller elements to the right of each bar
        for(int i=n-1; i>=0; i--){
            if(stack.isEmpty()){
                right[i] = n-1;// No smaller element to the right
            }else{
                while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                    stack.pop(); // Pop indices of elements greater than or equal to the current bar
                }
                right[i] = stack.isEmpty() ? n-1 : stack.peek() - 1;
            }
            stack.push(i); // Push the current bar's index
        }

        // Calculate the maximum area by iterating through each bar
        int maxArea = 0;
        for(int i=0; i<n; i++){
            int area = heights[i] * (right[i] - left[i] + 1); // Calculate the area for each bar
            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }
}

//____________________________________________________________________________________________________________________
Q: https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/description/

//Time Complexity O(n^2)
// class Solution {
//     public int[] smallerNumbersThanCurrent(int[] nums) {
//         int n = nums.length;
//         int[] ans = new int[n];
//         for(int i =0;i<n;i++){
//             int count = 0;
//             for(int j=0;j<n;j++){
//                 if(nums[i]>nums[j]){
//                     count++;
//                 }
//             }
//         ans[i]=count;
//         }
//         return ans;
//     }
// }


Runtime 5ms
Beats 85.76% of users with Java

//Time Complexity O(nlogn)
class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
       int n = nums.length;
       int[] ans = new int[n];
       int[] sortedArray = Arrays.copyOf(nums, n);
       Arrays.sort(sortedArray);
       Map<Integer, Integer> hashMap = new HashMap<>();

       for(int i=0; i<n; i++){
           hashMap.putIfAbsent(sortedArray[i], i);
       }

       for(int i=0; i<n; i++){
           ans[i] = hashMap.get(nums[i]);
       }

       return ans;
    }
}

