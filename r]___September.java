Q1: https://leetcode.com/problems/find-the-duplicate-number/description/

// //HashMap Approach
// class Solution {
//     public int findDuplicate(int[] nums) {
//         HashMap<Integer, Integer> hashmap = new HashMap<>();
//         for(int i=0; i<nums.length; i++){
//             int num = nums[i];

//             if(hashmap.containsKey(num)){
//                 return num;
//             }

//             hashmap.put(num, i);
//         }
//         return -1;
//     }
// }



//You must solve the problem without modifying the array nums and uses only constant extra space.
//Now this solution
//The Hare and Tortoise algorithm
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];

        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while(slow != fast);

        slow = nums[0];
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}



//___________________________________________________________________________________________________________________________
Q2: https://leetcode.com/problems/convert-1d-array-into-2d-array/description/

class Solution {
    public int[][] construct2DArray(int[] original, int m, int n) {
        int ans[][] = new int[m][n];
        if(original.length != m*n){
            return new int[0][0];
        }
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                ans[i][j] = original[n * i + j];
                
            }
        }

        return ans;
    }
}



//___________________________________________________________________________________________________________________________
Q3: https://leetcode.com/problems/search-in-rotated-sorted-array/description/

class Solution {
    public int search(int[] nums, int target){

        //find pivot index where the rotation occurs
        int pivot = findPivot(nums);  

        //if no pivot then normal binary search
        if(pivot == -1){
            return binarySearch(nums, target, 0, nums.length-1);
        } 

        if(nums[pivot] == target){
            return pivot;
        }

        //Decide which side of pivot to do binary search on
        if(target >= nums[0]){
            // target is in the left part of the array
            return binarySearch(nums, target, 0, pivot-1);
        }else{
            // target is in the righht part of the array
            return binarySearch(nums, target, pivot+1, nums.length-1);
        }
    }

    static int findPivot(int nums[]){
        int start = 0;
        int end = nums.length-1;

        while(start <= end){
            int mid = start + (end -start) / 2;

            //check if mid is pivot
            if(mid < end && nums[mid] > nums[mid+1]){
                return mid;
            }

            //check if mid-1 is pivot
            if(mid > start && nums[mid] < nums[mid-1]){
                return mid-1;
            }

            //Decide which side to search for pivot
            if(nums[start] <= nums[mid]){
                //left side is sorted pivot must be in the right side 
                start = mid+1;
            }else{
                //right side is sorted pivot must be in the left side
                end = mid-1;
            }
        }

        //no pivot found
        return -1;
    }

    static int binarySearch(int[] nums, int target, int start, int end){

        while(start <= end){

            int mid = start + (end-start) / 2;
            
            if(nums[mid] < target){
                start = mid+1;
            }else if(nums[mid] > target){
                end = mid-1;
            }else{
                return mid;
            }
        }
        return -1;
    }
}



//___________________________________________________________________________________________________________________________
Q4: https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/

class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int left = getMax(weights);
        int right = getSum(weights);
        
        // While (left <= right) is used in standard binary searches for finding exact values.
        // The loop ensures all positions are checked and executes one last time if left == right.
        // While (left < right) is used to narrow down to the smallest/largest valid option.
        // The loop ends when left and right converge to a single point, representing the answer.
        // Extra iteration with (left <= right) can lead to unnecessary checks and off-by-one errors.
        // It can also risk infinite loops if left and right don’t move as expected
        // TLE occured thats why explained above 
        while(left < right){

            int mid = left + (right-left) / 2;
            
            //check if it is possible to check all packages within 'days' using 'mid' capacity
            if(canShipInDays(weights, days, mid)){
                //if possible, try a samller capacity
                right = mid;
            }else{
                //if not possible, try a larger capacity
                left = mid + 1;
            }
        }

        return left;
    }

    //Helper Function
    static int getMax(int[] weights){
        int maxWeight = 0;
        for(int weight : weights){
            maxWeight = Math.max(weight, maxWeight);
        }
        return maxWeight;
    }

    //Helper Function
    static int getSum(int[] weights){
        int sum = 0;
        for(int weight : weights){
            sum += weight;
        }
        return sum;
    }

    static boolean canShipInDays(int[] weights, int days, int capacity){
        int currentWeight = 0;
        int daysNeeded = 1;
        
        for(int weight : weights){
            if(currentWeight + weight > capacity){
                daysNeeded++;
                currentWeight = 0;
            }
            currentWeight += weight;
        }

        return daysNeeded <= days;
    }
}



//___________________________________________________________________________________________________________________________
Q5: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] ans = {-1, -1};
        int start = search(nums, target, true);
        int end = search(nums, target, false);

        ans[0] = start;
        ans[1] = end;

        return ans;
    }

    static int search(int[] nums, int target, boolean firstIndex){
        int start = 0;
        int end = nums.length-1;
        int ans = -1;

        while(start <= end){
            int mid = start + (end - start) / 2;

            if(target < nums[mid]){
                end = mid - 1;
            }else if(target > nums[mid]){
                start = mid + 1;
            }else{
                ans = mid;
                if(firstIndex){
                    end = mid - 1;
                }else{
                    start = mid + 1;
                }
            }
        }
        return ans;
    }            
}



//___________________________________________________________________________________________________________________________
Q6: https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/description/

//Brute force approach Time Limit Exceeded 83 / 93 testcases passed
// class Solution {
//     public int minDays(int[] bloomDay, int m, int k) {
//         int n = bloomDay.length;

//         if(n < m * k){
//             return -1;
//         }

//         int minDay = Integer.MAX_VALUE;
//         int maxDay = Integer.MIN_VALUE;

//         for(int day : bloomDay){
//             minDay = Math.min(minDay, day);
//             maxDay = Math.max(maxDay, day);
//         }

//         for(int day = minDay; day <= maxDay; day++){
//             if(canMakeBouquets(bloomDay, day, m, k)){
//                 return day;
//             }
//         }
//         return -1;
//     }

//     static boolean canMakeBouquets(int[] bloomDay, int day, int m, int k){
//         int bouquets = 0;
//         int flowers = 0;

//         for(int bloom : bloomDay){
//             if(bloom <= day){
//                 flowers++;
//                 if(flowers == k){
//                     bouquets++;
//                     flowers = 0;
//                 }
//             }else{
//                 flowers = 0;
//             }
//         }
//         return bouquets >= m;
//     }
// }

//Binary Approach
class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;

        // If total flowers are less than required for m bouquets, return -1
        if(n < m * k){
            return -1;
        }

        int minDay = Integer.MAX_VALUE;
        int maxDay = Integer.MIN_VALUE;

        for(int day : bloomDay){
            minDay = Math.min(minDay, day);
            maxDay = Math.max(maxDay, day);
        }

        while(minDay < maxDay){
            int mid = minDay + (maxDay - minDay) / 2;

            if(canMakeBouquets(bloomDay, mid, m, k)){
                maxDay = mid;
            }else{
                minDay = mid+1;
            }
        }
        // Final check: Validate if the computed minDay is actually feasible
        // After binary search, minDay is the smallest day that could potentially work.
        // We need to confirm if minDay allows making the required number of bouquets.
        // If canMakeBouquets returns true for minDay, it means minDay is valid.
        // Otherwise, if minDay doesn't allow making the bouquets, return -1.
        return canMakeBouquets(bloomDay, minDay, m, k) ? minDay : -1;
    }

    static boolean canMakeBouquets(int[] bloomDay, int day, int m, int k){
        int bouquets = 0;
        int flowers = 0;

        // Traverse through bloomDay array to count valid bouquets
        for(int bloom : bloomDay){
            if(bloom <= day){
                flowers++; // Flower can be used (has bloomed by 'day')
                // If we have enough flowers to make one bouquet
                if(flowers == k){
                    bouquets++;  // Make one bouquet
                    flowers = 0; // Reset flower count for next bouquet
                }
            }else{
                // Flower hasn't bloomed by 'day', reset count
                flowers = 0;
            }
        }
        return bouquets >= m;
    }
}



//___________________________________________________________________________________________________________________________
Q7: https://leetcode.com/problems/median-of-two-sorted-arrays/

// Runtime 1ms Beats 100.00%
// TimeComplexity O(M+N)
// class Solution {
//     public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
//         int[] finalArray = new int[nums1.length + nums2.length];
        
//         int i = 0, j = 0, finalIndex = 0;
        
           // Merge nums1 and nums2 into finalArray
//         while(i<nums1.length &&  j<nums2.length){
//             if(nums1[i] < nums2[j]){
//                 finalArray[finalIndex++] = nums1[i];
//                 i++;
//             }else{
//                 finalArray[finalIndex++] = nums2[j];
//                 j++;
//             }
//         }

           // If there are remaining elements in nums1
//         while(i<nums1.length){
//             finalArray[finalIndex++] = nums1[i];
//             i++;
//         }

           // If there are remaining elements in nums2
//         while(j<nums2.length){
//             finalArray[finalIndex++] = nums2[j];
//             j++;
//         }

//         int n = finalArray.length;
//         int mid = n/2;

//         if(n % 2 == 0){
//             return (finalArray[mid-1] +finalArray[mid]) / 2.0;
//         }else{
//             return finalArray[mid];
//         }
//     }
// }

// Runtime 1ms Beats 100.00%
//The overall run time complexity should be O(log (m+n)).
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        //Ensure nums1 is the smaller array to minimize the binary search space 
        if(nums1.length > nums2.length){
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        int low = 0;
        int high = m; //end of the binary search range(size of nums1)

        //perform binary search on the nums1
        while(low <= high){
            int px = low + (high-low) / 2; //partition index for nums1
            int py = (m + n + 1) / 2 - px; //partition index for nums2

            //get values around the partition for nums1
            int x1 = Integer.MIN_VALUE; //Default for left partition when px is 0
            int x3 = Integer.MAX_VALUE; //Default for right partition when px is at the end of nums1

            if(px > 0){
                x1 = nums1[px - 1]; //largest value on the left side of nums1 partition
            }
            if(px < m){
                x3 = nums1[px]; //smallest value on the right side of nums1 partition
            }

            //Get values around the partition for nums2
            int x2 = Integer.MIN_VALUE; //default for left partition when py is 0
            int x4 = Integer.MAX_VALUE; //default for right partition when py is at the end of nums2
            if(py > 0){
                x2 = nums2[py - 1]; //Largest value on the left side of nums2 partition 
            }
            if(py < n){
                x4 = nums2[py]; //smallest value on the right side of nums2 partition
            }

            if(x1 <= x4 && x2 <= x3){

                //valid partition found
                if((m + n) % 2 == 0){
                    //If total length is even median is the avrage of max(left partition) and min(right partition)
                    double leftMax = Math.max(x1, x2);
                    double rightMin = Math.min(x3, x4);
                    return (leftMax + rightMin) / 2.0;
                }
                //if the total length is odd, median is the maximum value on the left side of the partition
                return Math.max(x1, x2);

            }else if(x1 > x4){
                //if the left value of nums1 is greater than the right value of the nums2, move the partition left 
                high = px - 1; //Decrease the partition index of nums1

            }else{
                //if the left of the nums2 is greater than the right value of nums1, move the partition right
                low = px + 1; //Increase the partition index of nums1
            }
        }
        return -1;
    }
}

//Visualization of the code do it on paper for understanding more clearly

// nums1 = [1, 3, 8]
// nums2 = [7, 9, 10, 11]


// Example 1: Initial Partitioning
// Partition Indices:
// Px = 1 (for nums1)
// Py = (3 + 4 + 1) / 2 - Px = 4 - 1 = 3 (for nums2)

// Partitions:
// nums1 becomes:
// Left Partition: [1]
// Right Partition: [3, 8]

// nums2 becomes:
// Left Partition: [7, 9, 10]
// Right Partition: [11]

// Values around the Partition:
// x1 = nums1[Px - 1] = nums1[0] = 1 (Largest value on the left side of nums1 partition)
// x3 = nums1[Px] = nums1[1] = 3 (Smallest value on the right side of nums1 partition)
// x2 = nums2[Py - 1] = nums2[2] = 10 (Largest value on the left side of nums2 partition)
// x4 = nums2[Py] = nums2[3] = 11 (Smallest value on the right side of nums2 partition)

// Check Validity:
// x1 <= x4 → 1 <= 11 (True)
// x2 <= x3 → 10 <= 3 (False)
// Since x2 > x3, the partitions are not correct, so adjust the search bounds. We need to move to the right in nums1.


// Example 2: Adjusted Partitioning

// Partition Indices:
// Px = 2 (for nums1)
// Py = (3 + 4 + 1) / 2 - Px = 4 - 2 = 2 (for nums2)

// Partitions:
// nums1 becomes:
// Left Partition: [1, 3]
// Right Partition: [8]

// nums2 becomes:
// Left Partition: [7, 9]
// Right Partition: [10, 11]

// Values around the Partition:
// x1 = nums1[Px - 1] = nums1[1] = 3 (Largest value on the left side of nums1 partition)
// x3 = nums1[Px] = nums1[2] = 8 (Smallest value on the right side of nums1 partition)
// x2 = nums2[Py - 1] = nums2[1] = 9 (Largest value on the left side of nums2 partition)
// x4 = nums2[Py] = nums2[2] = 10 (Smallest value on the right side of nums2 partition)

// Check Validity:
// x1 <= x4 → 3 <= 10 (True)
// x2 <= x3 → 9 <= 8 (False)
// Since x2 > x3, the partitions are not correct, so adjust the search bounds. We need to move to the right in nums1.


// Example 3: Final Partitioning
// Partition Indices:
// Px = 3 (for nums1)
// Py = (3 + 4 + 1) / 2 - Px = 4 - 3 = 1 (for nums2)

// Partitions:
// nums1 becomes:
// Left Partition: [1, 3, 8]
// Right Partition: []

// nums2 becomes:
// Left Partition: [7]
// Right Partition: [9, 10, 11]

// Values around the Partition:
// x1 = nums1[Px - 1] = nums1[2] = 8 (Largest value on the left side of nums1 partition)
// x3 = Integer.MAX_VALUE (No right partition in nums1)
// x2 = nums2[Py - 1] = nums2[0] = 7 (Largest value on the left side of nums2 partition)
// x4 = nums2[Py] = nums2[1] = 9 (Smallest value on the right side of nums2 partition)

// Check Validity:
// x1 <= x4 → 8 <= 9 (True)
// x2 <= x3 → 7 <= 8 (True)
// Since both conditions are satisfied, we have found the correct partitions.

// Calculate Median:
// Combined length of arrays: 7 (odd)
// Median is max(x1, x2) = max(8, 7) = 8

// Summary
// Initial Partition:
// Left Partitions: [1] and [7, 9, 10]
// Right Partitions: [3, 8] and [11]
// Adjust Bounds: Move right in nums1.

// Adjusted Partition:
// Left Partitions: [1, 3] and [7, 9]
// Right Partitions: [8] and [10, 11]

// Adjust Bounds: Move right in nums1.
// Final Partition:
// Left Partitions: [1, 3, 8] and [7]
// Right Partitions: [] and [9, 10, 11]
// Median: 8



//___________________________________________________________________________________________________________________________
// Q8: Problem Matrix Multiplication
// Description:
// Given two matrices, A of size m x n and B of size n x p, return the product matrix C of size m x p that results from multiplying A by B.
// Matrix multiplication is only possible when the number of columns in the first matrix (A) is equal to the number of rows in the second matrix (B).
// Therefore, the solution must first check if the matrices are compatible for multiplication. If they are not compatible, return an empty matrix or indicate an error.

class solution{
    public int[][] multiplyMatrix(int[][] A, int[][] B){
        //validate if the multiplication is possible
        int p = A.length;
        int q = B.length;
        int m = A[0].length;
        int n = B[0].length;

        int[][] finalMatrix = new int[p][n];

        if(m != q){
            return finalMatrix;
        }

        for(int i=0; i<p; i++){
            for(int j=0; j<n; j++){
                for(int k=0; k<m; k++){
                    finalMatrix[i][j] = finalMatrix[i][j] + A[i][k] * B[k][j];
                }
            }
        }

        return finalMatrix;
    }
}

// Input:
// Matrix A:
// 1  2  3
// 4  5  6

// Matrix B:
// 7   8
// 9  10
// 11 12

// Expected Output:
// Matrix C (Result of A x B):
// 58  64
// 139 154

// Explanation:
// Step-by-step multiplication process:

// 1. Multiply the first row of A with the first column of B:
//    (1*7) + (2*9) + (3*11) = 7 + 18 + 33 = 58

// 2. Multiply the first row of A with the second column of B:
//    (1*8) + (2*10) + (3*12) = 8 + 20 + 36 = 64

// 3. Multiply the second row of A with the first column of B:
//    (4*7) + (5*9) + (6*11) = 28 + 45 + 66 = 139

// 4. Multiply the second row of A with the second column of B:
//    (4*8) + (5*10) + (6*12) = 32 + 50 + 72 = 154

// Output Matrix:
// 58  64
// 139 154



//___________________________________________________________________________________________________________________________
Q9: https://leetcode.com/problems/diagonal-traverse/

class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        List<Integer> result = new ArrayList<>();

        for(int d = 0; d < m+n-1; d++){
            if(d % 2 == 0){
                int row = Math.min(d, m-1);  // Calculate starting row for this diagonal
                int col = d - row; // Calculate starting column for this diagonal

                // Even diagonal: go from bottom-left to top-right
                while(row >= 0 && col < n){
                    result.add(mat[row][col]);
                    row--; // Move up
                    col++; // Move right
                }
            }else{ // If the diagonal is odd
                int col = Math.min(d, n-1);  // Calculate starting column for this diagonal
                int row = d - col; // Calculate starting row for this diagonal

                // Odd diagonal: go from top-right to bottom-left
                while(row < m && col >= 0){
                    result.add(mat[row][col]);
                    row++; // Move down
                    col--; // Move left
                }
            }
        } 

        // Convert the result list to an array
        int[] output = new int[result.size()];
        for(int i=0; i<result.size(); i++){
            output[i] = result.get(i);
        }

        return output;
    }
}



//___________________________________________________________________________________________________________________________
Q10: https://leetcode.com/problems/spiral-matrix/description/

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> answer = new ArrayList<>();

        if(matrix == null || matrix.length == 0){
            return answer;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int topBoundary = 0;
        int rightBoundary = n-1;
        int bottomBoundary = m-1;
        int leftBoundary = 0;

        while(topBoundary <= bottomBoundary && leftBoundary <= rightBoundary){
            

            //Traverse from left boundary to right along the current top boundary 
            for(int i = leftBoundary; i <= rightBoundary; i++){
                answer.add(matrix[topBoundary][i]);
            }
            topBoundary++; //Move the top boundary down 


            //Traverse from top boundary to bottom along the current right boundary
            for(int i = topBoundary; i <= bottomBoundary; i++){
                answer.add(matrix[i][rightBoundary]);
            }
            rightBoundary--; //Move the right boundary left;


            // Check if there are rows remaining to be processed
            if(topBoundary <= bottomBoundary){
                // Traverse from the right boundary to the left along the current bottom row
                for(int i = rightBoundary; i >= leftBoundary; i--){
                    answer.add(matrix[bottomBoundary][i]);
                }
                bottomBoundary--; //Move the bottom boundary up
            }


            // Check if there are columns remaining to be processed
            if(leftBoundary <= rightBoundary){
                // Traverse from the bottom boundary to the top along the current left column
                for(int i = bottomBoundary; i >= topBoundary; i--){
                    answer.add(matrix[i][leftBoundary]);
                }
                leftBoundary++; //Move the left boundary right
            } 


        }
        return answer;
    }
}



//___________________________________________________________________________________________________________________________
Q11: https://leetcode.com/problems/spiral-matrix-ii/description/

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
    

        int topBoundary = 0;
        int rightBoundary = n-1;
        int bottomBoundary = n-1;
        int leftBoundary = 0;
        int nums = 1;

        while(topBoundary <= bottomBoundary && leftBoundary <= rightBoundary){
            

            //Traverse from left boundary to right along the current top boundary 
            for(int i = leftBoundary; i <= rightBoundary; i++){
                matrix[topBoundary][i] = nums++;
            }
            topBoundary++; //Move the top boundary down 


            //Traverse from top boundary to bottom along the current right boundary
            for(int i = topBoundary; i <= bottomBoundary; i++){
                matrix[i][rightBoundary] = nums++;
            }
            rightBoundary--; //Move the right boundary left;


            // Check if there are rows remaining to be processed
            if(topBoundary <= bottomBoundary){
                // Traverse from the right boundary to the left along the current bottom row
                for(int i = rightBoundary; i >= leftBoundary; i--){
                    matrix[bottomBoundary][i] = nums++;
                }
                bottomBoundary--; //Move the bottom boundary up
            }


            // Check if there are columns remaining to be processed
            if(leftBoundary <= rightBoundary){
                // Traverse from the bottom boundary to the top along the current left column
                for(int i = bottomBoundary; i >= topBoundary; i--){
                    matrix[i][leftBoundary] = nums++;
                }
                leftBoundary++; //Move the left boundary right
            } 


        }
        return matrix;
    }
}



//___________________________________________________________________________________________________________________________
Q12: https://leetcode.com/problems/spiral-matrix-iii/description/

class Solution {
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        
        //Result array to store the coordinates of cells visited in spiral order
        //size is rows*cols because we need to visit every cell once
        int[][] result = new int[rows * cols][2];

        //Directions array to define the movement in spiral order
        //{0, 1} -> Move East (Right)
        //{1, 0} -> Move South (Down)
        //{0, -1} -> Move West (Left)
        //{-1, 0} -> Move North (Up)
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        //Variable to control the number of steps to move in each direction
        //Start with 1 step and it will increment after every two turns 
        int steps = 1;

        //Index to keep track of the next position in the result array to fill
        int index = 0;

        //Variable to track which direction to move next; start with 0, which means "East"
        int directionIndex = 0;

        //Set the starting point the result array as the initial position(rStart, cStart)
        result[index++] = new int[]{rStart, cStart};

        //continue filling the result array until we have visited all cells
        while(index < rows * cols){

            //Each pair of direction (East + South and West + North) requires us to change the direction
            //Hence, we use a loop that iterated twice to handle two direction changes
            for(int d = 0; d < 2; d++){

                //Get the current direction's movements (dx, dy)
                int dx = directions[directionIndex][0];
                int dy = directions[directionIndex][1];

                //Move 'steps' times in the current direction
                for(int step = 0; step < steps; step++){

                    //Update the current position by adding the direction
                    rStart = rStart + dx;
                    cStart = cStart + dy;

                    //check if the new position is within the grid's boundaries 
                    if(rStart >= 0 && rStart < rows && cStart >= 0 && cStart < cols){
                        //If within boundaries add the position to the result array
                        result[index++] = new int[] {rStart, cStart};
                    }
                }

                //Update the direction to the next one in clockwise order (East -> South -> West -> North)
                // '% 4' ensures the directionIndex wraps around to 0 after reaching 3
                directionIndex = (directionIndex + 1) % 4; 
            }

            //After each full set of two moves 
            steps++;
        }

        return result;
    }
}


// Dry Run of Spiral Matrix III for a 4x4 grid starting at (1, 1)

// Initial Setup:
// Grid:
// [ (0, 0), (0, 1), (0, 2), (0, 3) ]
// [ (1, 0), (1, 1), (1, 2), (1, 3) ]
// [ (2, 0), (2, 1), (2, 2), (2, 3) ]
// [ (3, 0), (3, 1), (3, 2), (3, 3) ]
// Start at (1, 1)
// Result: [[1, 1], ...]  // Starting point added

// Iteration 1: Moving 1 Step East and 1 Step South
// steps = 1
// Move East (right) 1 step:
// (rStart, cStart) = (1, 1) + (0, 1) = (1, 2)
// Result: [[1, 1], [1, 2], ...]
// Move South (down) 1 step:
// (rStart, cStart) = (1, 2) + (1, 0) = (2, 2)
// Result: [[1, 1], [1, 2], [2, 2], ...]
// Increase steps to 2

// Iteration 2: Moving 2 Steps West and 2 Steps North
// steps = 2
// Move West (left) 2 steps:
// (rStart, cStart) = (2, 2) + (0, -1) = (2, 1)
// (rStart, cStart) = (2, 1) + (0, -1) = (2, 0)
// Result: [[1, 1], [1, 2], [2, 2], [2, 1], [2, 0], ...]
// Move North (up) 2 steps:
// (rStart, cStart) = (2, 0) + (-1, 0) = (1, 0)
// (rStart, cStart) = (1, 0) + (-1, 0) = (0, 0)
// Result: [[1, 1], [1, 2], [2, 2], [2, 1], [2, 0], [1, 0], [0, 0], ...]
// Increase steps to 3

// Iteration 3: Moving 3 Steps East and 3 Steps South
// steps = 3
// Move East (right) 3 steps:
// (rStart, cStart) = (0, 0) + (0, 1) = (0, 1)
// (rStart, cStart) = (0, 1) + (0, 1) = (0, 2)
// (rStart, cStart) = (0, 2) + (0, 1) = (0, 3)
// Result: [[1, 1], [1, 2], [2, 2], [2, 1], [2, 0], [1, 0], [0, 0], [0, 1], [0, 2], [0, 3], ...]
// Move South (down) 3 steps:
// (rStart, cStart) = (0, 3) + (1, 0) = (1, 3)
// (rStart, cStart) = (1, 3) + (1, 0) = (2, 3)
// (rStart, cStart) = (2, 3) + (1, 0) = (3, 3)
// Result: [[1, 1], [1, 2], [2, 2], [2, 1], [2, 0], [1, 0], [0, 0], [0, 1], [0, 2], [0, 3], [1, 3], [2, 3], [3, 3], ...]
// Increase steps to 4

// Iteration 4: Moving 4 Steps West and 4 Steps North
// steps = 4
// Move West (left) 4 steps:
// (rStart, cStart) = (3, 3) + (0, -1) = (3, 2)
// (rStart, cStart) = (3, 2) + (0, -1) = (3, 1)
// (rStart, cStart) = (3, 1) + (0, -1) = (3, 0)
// (rStart, cStart) = (3, 0) + (0, -1) = (3, -1) (Out of bounds, do not add)
// Result: [[1, 1], [1, 2], [2, 2], [2, 1], [2, 0], [1, 0], [0, 0], [0, 1], [0, 2], [0, 3], [1, 3], [2, 3], [3, 3], [3, 2], [3, 1], [3, 0]]
// At this point, all cells in the 4 x 4 grid have been visited in spiral order.



//___________________________________________________________________________________________________________________________
Q13: https://leetcode.com/problems/spiral-matrix-iv/description/

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
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        
        //Initialize the matrix with -1 values
        // This is to fill the remaining spaces when the linked list is exhausted
        int[][] matrix = new int[m][n];
        for(int[] row : matrix){
            Arrays.fill(row, -1); //Fill each row with -1
        } 

        //Define the initial boundaries for the spiral traversal
        int top = 0; //Top Boundary, start at the first row
        int bottom = m-1; //Bottom Boundary, start at the last row
        int left = 0; //Left Boundary, start at the first column
        int right = n-1; //Right Boundary, start as the last column

        //start with the head of the linked list
        ListNode current = head;

        //Continue filling the matrix until the linked list is exhausted or the matrix is filled
        while(current != null){
            
            //Traverse from left to right on the current top row 
            for(int i = left; i <= right && current != null; i++){
                matrix[top][i] = current.val;
                current = current.next;
            }
            top++; //After filling the top row, move the top boundary down

            //Traverse from top to bottom on the current right column
            for(int i = top; i <= bottom && current != null; i++){
                matrix[i][right] = current.val;
                current = current.next;
            }
            right--; //After filling right column, move the right boundary left

            //Traverse from right to left on the current bottom row
            for(int i = right; i >= left && current != null; i--){
                matrix[bottom][i] = current.val;
                current = current.next;
            }
            bottom--; //After filling the bottom row, move the bottom boundary up

            //Traverse from bottom to top on the current left column
            for(int i = bottom; i >= top && current != null; i--){
                matrix[i][left] = current.val;
                current = current.next;
            }
            left++; //After filling the left col, move the left boundary right
        }

        return matrix;
    }
}