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


//___________________________________________________________________________________________________________________________
Q14: https://leetcode.com/problems/search-a-2d-matrix/description/

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        //check if the matrix is null or empty, return false if it is 
        if(matrix == null || matrix.length == 0){
            return false; 
        }

        int start, end, mid;
        int flag;

        start = 0;
        end = matrix.length - 1;

        while(start + 1 < end){ // Continue until only two rows are left
            mid = start + (end - start) / 2;
            if(target == matrix[mid][0]){
                return true;
            }else if(target < matrix[mid][0]){ //if target element is less than the first element of the middle row 
                end = mid; //Move the end pointer to mid( target must be in the upper half)
            }else{ //If target is greater than the first element of the middle row 
                start = mid; //Move the start pointer to mid (look in the lower half)
            }
        }

        //After the loop, start and end point to adjacent rows
        //check which row to search
        if(target == matrix[start][0]){
            return true;
        }else if(target == matrix[end][0]){
            return true;
        }else if(target > matrix[start][0] && target < matrix[end][0]){ //if target is greater than the 0th element
                                                                        //of the start row and less than 0th element of the end row 
            flag = start; // target must be in the start row 
        }else{
            flag = end; //target must be in the end row 
        }

        //perform binray search in the identified row (flag)
        start = 0;
        end = matrix[flag].length - 1;

        while(start + 1 < end){ 
            mid = start + (end - start) / 2;
            if(target == matrix[flag][mid]){
                return true; //target found in the row
            }else if(target < matrix[flag][mid]){
                end = mid;
            }else{
                start = mid;
            }
        }

        //check the final two positions in the row 
        if(target == matrix[flag][start] || target == matrix[flag][end]){
            return true;
        }

        return false;
    }
}



//___________________________________________________________________________________________________________________________
Q15: https://leetcode.com/problems/sum-in-a-matrix/

class Solution {
    public int matrixSum(int[][] nums) {

        int n = nums.length;
        int m = nums[0].length;

        int score = 0; 

        //first sort each row 
        for(int[] row : nums){
            Arrays.sort(row);  // Sort row in ascending order
            reverse(row); // Reverse row to make it descending
        }

        // Then, compute the score by finding the max in each column
        for(int i = 0; i < m; i++){  // iterate over columns
            int maxInThisRound = 0;

            for(int j = 0; j < n; j++){  // iterate over rows
                maxInThisRound = Math.max(maxInThisRound, nums[j][i]); // find max in column
            }

            score += maxInThisRound;  // add max value of this column to the score
        }

        return score;
    }

    static void reverse(int[] row){
        int i = 0;
        int j = row.length-1;
        while(i < j){
            int temp = row[i];
            row[i] = row[j];
            row[j] = temp;
            i++;
            j--;
        }
    }
}



//___________________________________________________________________________________________________________________________
Q16: https://leetcode.com/problems/diagonal-traverse-ii/description/

class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        
        //Map to store elements grouped by their diagonals
        Map<Integer, List<Integer>> diagonals = new HashMap<>();

        //Track the maximum diagonal index to iterate through later
        int maxDiagonal = 0;
        int totalElements = 0;

        //Traverse the matrix to populate the diagonals map
        for(int i = 0; i < nums.size(); i++){
            for(int j = 0; j < nums.get(i).size(); j++){

                int diagonalKey = i + j;

                //If the diagonal doesn't exist in the map, create a new list for it
                diagonals.putIfAbsent(diagonalKey, new ArrayList<>());

                //Add the element to the corresponding diagonal list
                diagonals.get(diagonalKey).add(nums.get(i).get(j));

                //Update the maximum diagonal index
                maxDiagonal = Math.max(maxDiagonal, diagonalKey);
                totalElements++;
            }
        }

        //Array to store the final diagonal order traversal 
        int[] result = new int[totalElements];
        int index = 0; //Index for result array

        //Traverse diagonals from 0 to maxDiagonal
        for(int k = 0; k <= maxDiagonal; k++){
            if(diagonals.containsKey(k)){

                //retrieve the diagonal list
                List<Integer> diagonalElements = diagonals.get(k);

                //reverse the list to maintain bottom-up order and add to result array 
                for(int i = diagonalElements.size() - 1; i >= 0; i--){
                    result[index++] = diagonalElements.get(i);
                }
            }
        }

        return result;
    }
}


// Dry Run of code with Example Matrix:
// nums = [
//     [1, 2, 3],
//     [4, 5, 6],
//     [7, 8, 9]
// ]

// -- First Row (i = 0): --
// j = 0: diagonalKey = 0 + 0 = 0
// diagonals becomes {0: [1]}
// maxDiagonal becomes 0
// totalElements becomes 1

// j = 1: diagonalKey = 0 + 1 = 1
// diagonals becomes {0: [1], 1: [2]}
// maxDiagonal becomes 1
// totalElements becomes 2

// j = 2: diagonalKey = 0 + 2 = 2
// diagonals becomes {0: [1], 1: [2], 2: [3]}
// maxDiagonal becomes 2
// totalElements becomes 3

// -- Second Row (i = 1): --
// j = 0: diagonalKey = 1 + 0 = 1
// diagonals becomes {0: [1], 1: [2, 4], 2: [3]}
// totalElements becomes 4

// j = 1: diagonalKey = 1 + 1 = 2
// diagonals becomes {0: [1], 1: [2, 4], 2: [3, 5]}
// totalElements becomes 5

// j = 2: diagonalKey = 1 + 2 = 3
// diagonals becomes {0: [1], 1: [2, 4], 2: [3, 5], 3: [6]}
// maxDiagonal becomes 3
// totalElements becomes 6

// -- Third Row (i = 2): --
// j = 0: diagonalKey = 2 + 0 = 2
// diagonals becomes {0: [1], 1: [2, 4], 2: [3, 5, 7], 3: [6]}
// totalElements becomes 7

// j = 1: diagonalKey = 2 + 1 = 3
// diagonals becomes {0: [1], 1: [2, 4], 2: [3, 5, 7], 3: [6, 8]}
// totalElements becomes 8

// j = 2: diagonalKey = 2 + 2 = 4
// diagonals becomes {0: [1], 1: [2, 4], 2: [3, 5, 7], 3: [6, 8], 4: [9]}
// maxDiagonal becomes 4
// totalElements becomes 9

// -- At the end of this step: --
// diagonals looks like:
// {0: [1], 1: [2, 4], 2: [3, 5, 7], 3: [6, 8], 4: [9]}
// maxDiagonal = 4
// totalElements = 9
// -------------------

// Diagonal 0 (k = 0): [1] -> result: [1, 0, 0, 0, 0, 0, 0, 0, 0], index: 1
// Diagonal 1 (k = 1): [2, 4] -> result: [1, 4, 2, 0, 0, 0, 0, 0, 0], index: 3
// Diagonal 2 (k = 2): [3, 5, 7] -> result: [1, 4, 2, 7, 5, 3, 0, 0, 0], index: 6
// Diagonal 3 (k = 3): [6, 8] -> result: [1, 4, 2, 7, 5, 3, 8, 6, 0], index: 8
// Diagonal 4 (k = 4): [9] -> result: [1, 4, 2, 7, 5, 3, 8, 6, 9], index: 9
// -------------------

// The final result array is [1, 4, 2, 7, 5, 3, 8, 6, 9]



//___________________________________________________________________________________________________________________________
Q17: https://leetcode.com/problems/prime-in-diagonal/description/

class Solution {
    public int diagonalPrime(int[][] nums) {
        
        int n = nums.length;
        int maxPrime = 0;

        int number = 0;
        boolean check = false;

        for(int i = 0; i < n; i++){
            
            number = nums[i][i];
            if(isPrime(number)){
                maxPrime = Math.max(maxPrime, number);    
            }

            number = nums[i][n - i- 1];
            if(isPrime(number)){
                maxPrime = Math.max(maxPrime, number);
            }
        }

        return maxPrime;
    }

    static boolean isPrime(int number){
        if(number < 2){
            return false;
        }

        for(int i = 2; i <= Math.sqrt(number); i++){
            if(number % i == 0){
                return false;
            }
        }
        return true;
    }
}



//___________________________________________________________________________________________________________________________
Q18: https://leetcode.com/problems/sort-the-matrix-diagonally/description/

class Solution {
    public int[][] diagonalSort(int[][] mat) {
        
        int n = mat.length;
        int m = mat[0].length;

        //sort all diagonals STARTING from each element in the first column 
        //These diagonals start from positions (i, 0) where i ranges from 0 to n-1
        for(int i = 0; i < n; i++){
            sortDiagonal(mat, i, 0);
        }   

        //sort all diagonals STARTING from each element in the first row
        //These diagonals start from positions (0, j) where j ranges from 0 to m-1
        //Note: start from j=1 to avoid the diagonal starting at (0, 0) again
        for(int i = 1; i < m; i++){
            sortDiagonal(mat, 0, i);
        }

        return mat;
    }

    //Helper function
    private void sortDiagonal(int mat[][], int row, int col){

        int n = mat.length;
        int m = mat[0].length;

        //List to store elements of the diagonal
        List<Integer> list = new ArrayList<>();
        
        int r = row, c = col;

        //collect all elements of the current diagonal into the list 
        while(r < n && c < m){
            list.add(mat[r][c]);
            r++;
            c++;
        }

        //sort the collected diagonal elements
        Collections.sort(list);

        r = row; //reset row
        c = col; //reset col
        int index = 0;
        
        while(r < n && c < m){
            //Replace the element in the matrix with the sorted value
            mat[r][c] = list.get(index);
            r++;
            c++;
            index++;
        }
    } 
}

 // Initial matrix:
        // [
        //   [3, 3, 1, 1],
        //   [2, 2, 1, 2],
        //   [1, 1, 1, 2]
        // ]

    // Sorting diagonal starting from (0, 0):
        // Collected elements from diagonal (0,0): [3, 2, 1]
        // After sorting: [1, 2, 3]
        // Placing back sorted elements:
        // (0,0) -> 1
        // (1,1) -> 2
        // (2,2) -> 3

        // Updated Matrix:
        // [
        //   [1, 3, 1, 1],
        //   [2, 2, 1, 2],
        //   [1, 1, 3, 2]
        // ]

    // Sorting diagonal starting from (1, 0):
        // Collected elements from diagonal (1,0): [2, 1]
        // After sorting: [1, 2]
        // Placing back sorted elements:
        // (1,0) -> 1
        // (2,1) -> 2
        // Updated Matrix:
        // [
        //   [1, 3, 1, 1],
        //   [1, 2, 1, 2],
        //   [1, 2, 3, 2]
        // ]

    // Sorting diagonal starting from (2, 0):
        // Collected elements from diagonal (2,0): [1]
        // After sorting: [1] (already sorted)
        // Placing back sorted elements:
        // (2,0) remains 1
        // Updated Matrix remains unchanged:
        // [
        //   [1, 3, 1, 1],
        //   [1, 2, 1, 2],
        //   [1, 2, 3, 2]
        // ]

    // Sorting diagonal starting from (0, 1):
        // Collected elements from diagonal (0,1): [3, 1]
        // After sorting: [1, 3]
        // Placing back sorted elements:
        // (0,1) -> 1
        // (1,2) -> 3
        // Updated Matrix:
        // [
        //   [1, 1, 1, 1],
        //   [1, 2, 3, 2],
        //   [1, 2, 3, 2]
        // ]

    // Sorting diagonal starting from (0, 2):
        // Collected elements from diagonal (0,2): [1, 2]
        // After sorting: [1, 2] (already sorted)
        // Placing back sorted elements:
        // (0,2) remains 1
        // (1,3) remains 2
        // Updated Matrix remains unchanged:
        // [
        //   [1, 1, 1, 1],
        //   [1, 2, 3, 2],
        //   [1, 2, 3, 2]
        // ]

    // Sorting diagonal starting from (0, 3):
        // Collected elements from diagonal (0,3): [1]
        // After sorting: [1] (already sorted)
        // Placing back sorted elements:
        // (0,3) remains 1
        // Updated Matrix remains unchanged:
        // [
        //   [1, 1, 1, 1],
        //   [1, 2, 3, 2],
        //   [1, 2, 3, 2]
        // ]

// Final Output:
        // [
        //   [1, 1, 1, 1],
        //   [1, 2, 2, 2],
        //   [1, 2, 3, 3]
        // ]



//___________________________________________________________________________________________________________________________
Q19: https://leetcode.com/problems/maximum-area-of-longest-diagonal-rectangle/description/

class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        
        int n = dimensions.length;
        double maxSquareRoot = 0;
        int maxArea = 0;

        for(int i = 0; i < n; i++){
            int a = dimensions[i][0];
            int b = dimensions[i][1];
            double squareRoot = Math.sqrt(a * a + b * b); 

            if(squareRoot > maxSquareRoot){
                maxSquareRoot = squareRoot;
                maxArea = a * b;
            }else if(squareRoot == maxSquareRoot){
                //if the squareRoot is the same as the current maxSquareRoot, update the maxArea if needed
                maxArea = Math.max(maxArea, a * b);
            }           
        }

        return maxArea;
    }
}



//___________________________________________________________________________________________________________________________
Q20: https://leetcode.com/problems/toeplitz-matrix/description/

// Based on a previous matrix problem( https://leetcode.com/problems/sort-the-matrix-diagonally/description/ )
// this initial approach was derived by checking each diagonal independently. 

// class Solution {
//     public boolean isToeplitzMatrix(int[][] matrix) {
        
//         int n = matrix.length;
//         int m = matrix[0].length;

//         for(int i = 0; i < n; i++){
//             if( !check(matrix, i, 0)){
//                 return false;
//             }
//         }

//         for(int i = 1; i < m; i++){
//             if( !check(matrix, 0, i)){
//                 return false;
//             } 
//         }

//         return true;
//     }

//     private boolean check(int[][] matrix, int row, int col){
        
//         int n = matrix.length;
//         int m = matrix[0].length;

//         int r = row, c = col;
//         int temp = matrix[r][c];

//         boolean ans = true;

//         while(r < n && c < m){
//             if(matrix[r][c] != temp){
//                 return false;
//             }
//             r++;
//             c++;
//         }

//         return true;
//     }
// }

// However, upon further review, the solution was optimized to a more readable and efficient version 
// by directly comparing each element with its diagonal neighbor.

class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {

        int n = matrix.length;
        int m = matrix[0].length;

        for(int i = 0; i < n-1; i++){
            for(int j = 0; j < m-1; j++){
                if(matrix[i][j] != matrix[i+1][j+1]){
                    return false;
                }
            }
        }  

        return true; 
    }
}



//___________________________________________________________________________________________________________________________
Q21: https://leetcode.com/problems/modify-the-matrix/description/

//Time Complexity: O(n×m^2) in the worst case.
//For each element in the matrix, when it encounters -1,
//it starts a new loop to find the maximum value in the column below

// class Solution {
//     public int[][] modifiedMatrix(int[][] matrix) {
        
//         int n = matrix.length;
//         int m = matrix[0].length;

//         for(int i = 0; i < n; i++){
//             for(int j = 0; j < m; j++){
               
//                 if(matrix[i][j] == -1){
                    
//                     int maxElement = 0;
                    
//                     for(int k = i; k < n; k++){
//                         int currentElement = matrix[i][j];
//                         maxElement = Math.max(maxElement, matrix[k][j]);
//                     }
                    
//                     matrix[i][j] = maxElement;
//                 }
//             }
//         }

//         return matrix;
//     }
// }



// Compute maximum values for each column by traversing from the bottom to the top and store them in maxBelow
// Replace -1 values in the matrix with the corresponding precomputed maximum from maxBelow
// Time Complexity: O(n×m)

class Solution {
    public int[][] modifiedMatrix(int[][] matrix) {

        int n = matrix.length;
        int m = matrix[0].length;

        int[] maxBelow = new int[m];

        // Fill maxBelow with the maximum values of each column
        for(int j = 0; j < m; j++){
            int max = Integer.MIN_VALUE;
            for(int i = n-1; i >= 0; i--){
                max = Math.max(max, matrix[i][j]);
                maxBelow[j] = max;
            }
        }

        //Update -1 in the matrix with the maximum values from maxBelow
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == -1){
                    matrix[i][j] = maxBelow[j];
                }
            }
        }

        return matrix;
    }
}



//___________________________________________________________________________________________________________________________
Q22: https://leetcode.com/problems/check-if-matrix-is-x-matrix/description/

class Solution {
    public boolean checkXMatrix(int[][] grid) {
        
        return (diagonals(grid) && checkZeros(grid));
    }

    private static boolean diagonals(int[][] grid){
        
        int n = grid.length;

        for(int i = 0; i < n; i++){
            
            //check the primary diagonal(from top left to bottom right)
            if(grid[i][i] == 0){
                return false;
            }

            //check the secondary diagonal(from top right to bottom left)
            if(grid[i][n - i - 1] == 0){
                return false;
            }
        }
        return true;
    }

    private static boolean checkZeros(int[][] grid){

        int n = grid.length;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){

                //skip the diagonals
                if(i == j || i+j == n-1){
                    continue;
                }

                //for non diagonal elements check if they are all zeros
                if(grid[i][j] != 0){
                    return false;
                }
            }
        }
        return true;
    }
}



//___________________________________________________________________________________________________________________________
Q23: https://leetcode.com/problems/lucky-numbers-in-a-matrix/description/

// TimeComplexity: O(rows * cols * (rows + cols))
// class Solution {
//     public List<Integer> luckyNumbers(int[][] matrix) {
//         List<Integer> result = new ArrayList<>();

//         for (int i=0; i<matrix.length; i++) {
//             for (int j=0; j < matrix[i].length; j++) {
//                 int currentNumber = matrix[i][j];

                   // Check if the current number is the minimum in its row
//                 boolean isMinInRow = true;
//                 for (int k=0; k<matrix[i].length; k++) {
//                     if (matrix[i][k] < currentNumber) {
//                         isMinInRow = false;
//                         break;
//                     }
//                 }
                   // Check if the current number is the maximum in its column
//                 boolean isMaxInCol = true;
//                 for (int k=0; k<matrix.length; k++) {
//                     if (matrix[k][j] > currentNumber) {
//                         isMaxInCol = false;
//                         break;
//                     }
//                 }
                   // If the current number is both the minimum in its row and the maximum in its column, add it to the result
//                 if (isMinInRow && isMaxInCol) {
//                     result.add(currentNumber);
//                 }
//             }
//         }
//         return result;
//     }
// }

//Time Complexity: O(rows * cols)
class Solution {
    public List<Integer> luckyNumbers(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        int rows = matrix.length;
        int cols = matrix[0].length;

        //find the minimum in each row
        int[] minRow = new int[rows];
        for(int i = 0; i < rows; i++){
            int min = matrix[i][0];
            for(int j = 1; j < cols; j++){
                if(matrix[i][j] < min){
                    min = matrix[i][j];
                }
            }
            minRow[i] = min;
        }

        //find the maximum in each cpl
        int[] maxCol = new int[cols];
        for(int j = 0; j < cols; j++){
            int max = matrix[0][j];
            for(int i = 1; i < rows; i++){
                if(matrix[i][j] > max){
                    max = matrix[i][j];
                }
            }
            maxCol[j] = max;
        }

        //check for lucky number
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(matrix[i][j] == minRow[i] && matrix[i][j] == maxCol[j]){
                    result.add(matrix[i][j]);
                }
            }
        }
        return result;
    }
}

//matrix = [
//   [3, 7, 8],
//   [9, 11, 13],
//   [15, 16, 17]
// ]

// Step-by-Step Dry Run:


// Step 1: Find the Minimum in Each Row
// Initialize minRow array: minRow = [0, 0, 0]
// Calculate minimum for each row:

// For row 0 (values: [3, 7, 8]):
// min = matrix[0][0] = 3
// Compare matrix[0][1] = 7 with min = 3 → No change
// Compare matrix[0][2] = 8 with min = 3 → No change
// minRow[0] = 3

// For row 1 (values: [9, 11, 13]):
// min = matrix[1][0] = 9
// Compare matrix[1][1] = 11 with min = 9 → No change
// Compare matrix[1][2] = 13 with min = 9 → No change
// minRow[1] = 9

// For row 2 (values: [15, 16, 17]):
// min = matrix[2][0] = 15
// Compare matrix[2][1] = 16 with min = 15 → No change
// Compare matrix[2][2] = 17 with min = 15 → No change
// minRow[2] = 15

// Result after Step 1: minRow = [3, 9, 15]


// Step 2: Find the Maximum in Each Column
// Initialize maxCol array: maxCol = [0, 0, 0]
// Calculate maximum for each column:

// For column 0 (values: [3, 9, 15]):
// max = matrix[0][0] = 3
// Compare matrix[1][0] = 9 with max = 3 → max = 9
// Compare matrix[2][0] = 15 with max = 9 → max = 15
// maxCol[0] = 15

// For column 1 (values: [7, 11, 16]):
// max = matrix[0][1] = 7
// Compare matrix[1][1] = 11 with max = 7 → max = 11
// Compare matrix[2][1] = 16 with max = 11 → max = 16
// maxCol[1] = 16

// For column 2 (values: [8, 13, 17]):
// max = matrix[0][2] = 8
// Compare matrix[1][2] = 13 with max = 8 → max = 13
// Compare matrix[2][2] = 17 with max = 13 → max = 17
// maxCol[2] = 17

// Result after Step 2: maxCol = [15, 16, 17]


// Step 3: Check for Lucky Numbers
// Initialize result list: result = []
// Check each element of the matrix:

// For row 0:
// For column 0: matrix[0][0] = 3
// matrix[0][0] == minRow[0] (3 == 3) and matrix[0][0] == maxCol[0] (3 != 15) → Not a lucky number

// For column 1: matrix[0][1] = 7
// matrix[0][1] == minRow[0] (7 != 3) → Not a lucky number

// For column 2: matrix[0][2] = 8
// matrix[0][2] == minRow[0] (8 != 3) → Not a lucky number

// For row 1:
// For column 0: matrix[1][0] = 9
// matrix[1][0] == minRow[1] (9 == 9) and matrix[1][0] == maxCol[0] (9 != 15) → Not a lucky number

// For column 1: matrix[1][1] = 11
// matrix[1][1] == minRow[1] (11 != 9) → Not a lucky number

// For column 2: matrix[1][2] = 13
// matrix[1][2] == minRow[1] (13 != 9) → Not a lucky number

// For row 2:
// For column 0: matrix[2][0] = 15
// matrix[2][0] == minRow[2] (15 == 15) and matrix[2][0] == maxCol[0] (15 == 15) → Lucky number found
// Add 15 to result: result = [15]

// For column 1: matrix[2][1] = 16
// matrix[2][1] == minRow[2] (16 != 15) → Not a lucky number

// For column 2: matrix[2][2] = 17
// matrix[2][2] == minRow[2] (17 != 15) → Not a lucky number

// Result after Step 3: result = [15]



//___________________________________________________________________________________________________________________________
Q24: https://leetcode.com/problems/fibonacci-number/description/

class Solution {
    public int fib(int n) {
        if(n <= 1){
            return n;
        }

        return fib(n-1) + fib(n-2);
    }
}


//___________________________________________________________________________________________________________________________
Q25: https://leetcode.com/problems/n-th-tribonacci-number/description/

// class Solution {
//     public int tribonacci(int n) {
//        if(n == 0) return 0;
//        if(n == 1 || n == 2) return 1;
        
//        return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
//     }
// }


class Solution {
    public int tribonacci(int n) {
        if(n == 0) return 0;
        if(n == 1 || n == 2) return 1;
        
        int a=0, b=1, c=1;

        for(int i = 3; i <= n; i++){
            int next = a + b + c;
            a = b;
            b = c;
            c  = next;
        }

        return c;
    }
}


//___________________________________________________________________________________________________________________________
Q26: https://leetcode.com/problems/palindrome-number/description/

class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0) return false;

        String s = Integer.toString(x);

        return isPalindrome(s, 0, s.length()-1);
    }

    static boolean isPalindrome(String s, int left, int right){
        if(left >= right) return true;

        if(s.charAt(left) != s.charAt(right)) return false;

        return isPalindrome(s, left + 1, right - 1);
    }
}



//___________________________________________________________________________________________________________________________
Q27: https://leetcode.com/problems/valid-palindrome/description/

class Solution {
    public boolean isPalindrome(String s) {
        // Normalize the string by removing non-alphanumeric characters and converting to lowercase
        s = s.replaceAll("[^a-zA-Z0-9]","").toLowerCase();        

        return isPalindromeRecursive(s, 0, s.length() - 1);
    }

    private boolean isPalindromeRecursive(String s, int left, int right) {
        if (left >= right) return true;
        
        if (s.charAt(left) != s.charAt(right)) return false;
        
        return isPalindromeRecursive(s, left + 1, right - 1);
    }
}



//___________________________________________________________________________________________________________________________
Q28: https://leetcode.com/problems/climbing-stairs/description/

// class Solution {
//     public int climbStairs(int n) {
//         if(n == 1) return 1;
//         if(n == 0) return 1;

//         return climbStairs(n-1) + climbStairs(n-2);
//     }
// }

class Solution {
    public int climbStairs(int n) {
        int[] temp = new int[n+1];

        Arrays.fill(temp, -1);

        return climbStairsRecursive(n, temp);
    }

    static int climbStairsRecursive(int n, int[] temp){
        if(n <= 1) return 1;

        //if already computed return the store result
        if(temp[n] != -1) return temp[n];

        temp[n] = climbStairsRecursive(n-1, temp) + climbStairsRecursive(n-2, temp);

        return temp[n];
    }
}



//___________________________________________________________________________________________________________________________
Q29: https://leetcode.com/problems/subsets/description/

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generateSubsets(nums, 0, new ArrayList<>(), result);
        return result;
    }
    //'processed' represents the current subset being built
    //'index' is the current position in 'nums'
    private void generateSubsets(int[] nums, int index, List<Integer> processed, List<List<Integer>> result) {
        
        // Base case: if we have processed all elements
        if (index == nums.length) {
            result.add(new ArrayList<>(processed));  // Add the current subset to the result
            return;
        }

        //Decision 1: Include the current element in the subset
        processed.add(nums[index]); //Include nums[index] in the processed subset
        generateSubsets(nums, index+1, processed, result); //Recur for next element
        processed.remove(processed.size() - 1); //Backtrack: Remove the last element

        //Decision 2: Exclude the current element from the subset
        generateSubsets(nums, index+1, processed, result); //Recur for next element without including nums[index]
    }
}

 // Dry run explanation for nums = [1, 2, 3]:
        // 1. Initial Call: generateSubsets([1, 2, 3], 0, [], result)
        // 2. Current index = 0, processed = []
        //    - Decision 1: Include nums[0] (1)
        //      processed = [1]
        //      Recur with index = 1
        // 3. Current index = 1, processed = [1]
        //    - Decision 1: Include nums[1] (2)
        //      processed = [1, 2]
        //      Recur with index = 2
        // 4. Current index = 2, processed = [1, 2]
        //    - Decision 1: Include nums[2] (3)
        //      processed = [1, 2, 3]
        //      Recur with index = 3
        // 5. Current index = 3, processed = [1, 2, 3]
        //    - Base case reached, add [1, 2, 3] to result
        //    - Backtrack: processed = [1, 2]
        //    - Decision 2: Exclude nums[2] (3)
        //      processed = [1, 2]
        //      Recur with index = 3
        // 6. Current index = 3, processed = [1, 2]
        //    - Base case reached, add [1, 2] to result
        //    - Backtrack: processed = [1]
        //    - Decision 2: Exclude nums[1] (2)
        //      processed = [1]
        //      Recur with index = 2
        // 7. Current index = 2, processed = [1]
        //    - Decision 1: Include nums[2] (3)
        //      processed = [1, 3]
        //      Recur with index = 3
        // 8. Current index = 3, processed = [1, 3]
        //    - Base case reached, add [1, 3] to result
        //    - Backtrack: processed = [1]
        //    - Decision 2: Exclude nums[2] (3)
        //      processed = [1]
        //      Recur with index = 3
        // 9. Current index = 3, processed = [1]
        //    - Base case reached, add [1] to result
        //    - Backtrack: processed = []
        //    - Decision 2: Exclude nums[0] (1)
        //      processed = []
        //      Recur with index = 1
        // 10. Current index = 1, processed = []
        //    - Decision 1: Include nums[1] (2)
        //      processed = [2]
        //      Recur with index = 2
        // 11. Current index = 2, processed = [2]
        //    - Decision 1: Include nums[2] (3)
        //      processed = [2, 3]
        //      Recur with index = 3
        // 12. Current index = 3, processed = [2, 3]
        //    - Base case reached, add [2, 3] to result
        //    - Backtrack: processed = [2]
        //    - Decision 2: Exclude nums[2] (3)
        //      processed = [2]
        //      Recur with index = 3
        // 13. Current index = 3, processed = [2]
        //    - Base case reached, add [2] to result
        //    - Backtrack: processed = []
        //    - Decision 2: Exclude nums[1] (2)
        //      processed = []
        //      Recur with index = 2
        // 14. Current index = 2, processed = []
        //    - Decision 1: Include nums[2] (3)
        //      processed = [3]
        //      Recur with index = 3
        // 15. Current index = 3, processed = [3]
        //    - Base case reached, add [3] to result
        //    - Backtrack: processed = []
        //    - Decision 2: Exclude nums[2] (3)
        //      processed = []
        //      Recur with index = 3
        // 16. Current index = 3, processed = []
        //    - Base case reached, add [] to result
        //    - Backtrack complete

        // Final result: [[1, 2, 3], [1, 2], [1, 3], [1], [2, 3], [2], [3], []]                                                                  



//___________________________________________________________________________________________________________________________
Q30: https://leetcode.com/problems/subsets-ii/description/

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
         
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        generateSubsets(nums, 0, new ArrayList<>(), result);
        return result;
    }

    //'processed' represents the current subset being built
    //'index' is the current position in 'nums'
    private void generateSubsets(int[] nums, int index, List<Integer> processed, List<List<Integer>> result) {
        
        // Base case: if we have processed all elements
        if (index == nums.length) {
            result.add(new ArrayList<>(processed));  // Add the current subset to the result
            return;
        }

        //Decision 1: Include the current element in the subset
        processed.add(nums[index]); //Include nums[index] in the processed subset
        generateSubsets(nums, index+1, processed, result); //Recur for next element
        processed.remove(processed.size() - 1); //Backtrack: Remove the last element

        //Decision 2: Exclude the current element from the subset
        while(index + 1 < nums.length && nums[index] == nums[index+1]){
            index++;
        }
        generateSubsets(nums, index+1, processed, result); //Recur for next element without including nums[index]
    }
}

//___________________________________________________________________________________________________________________________
Q31: https://leetcode.com/problems/merge-sorted-array/description/

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        
        int p1 = m-1;
        int p2 = n-1;
        int merged = m+n-1;

        //merge the arrays from the end to the beginning
        while(p1 >= 0 && p2 >= 0){
            if(nums1[p1] > nums2[p2]){
                nums1[merged] = nums1[p1];
                p1--;
            }else{
                nums1[merged] = nums2[p2];
                p2--;
            }
            merged--;
        }

        //if there are still elements in nums2, copy them to nums1
        while(p2 >= 0){
            nums1[merged] = nums2[p2];
            p2--;
            merged--;
        }

        //No need to handle the remaining elements of nums1 if p1 >= 0;
        //as they are already in place
    }
}



//___________________________________________________________________________________________________________________________
Q32: https://leetcode.com/problems/special-positions-in-a-binary-matrix/description/

class Solution {
    public int numSpecial(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        int[] rowSum = new int[n];
        int[] colSum = new int[m];

        int count = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(mat[i][j] == 1){
                    rowSum[i]++;
                    colSum[j]++;
                }
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(mat[i][j] == 1 && rowSum[i] == 1 && colSum[j] == 1){
                    count++;
                }
            }
        }

        return count;
    }
}



//___________________________________________________________________________________________________________________________
Q33: https://leetcode.com/contest/biweekly-contest-139/problems/find-indices-of-stable-mountains/

class Solution {
    public List<Integer> stableMountains(int[] height, int threshold) {
        
        List<Integer> result = new ArrayList<>();
        for(int i = 1; i< height.length; i++){
            if(height[i-1] > threshold){
                result.add(i);
            }
        }
        
        return result;
    }
}



//___________________________________________________________________________________________________________________________
Q34: https://leetcode.com/problems/remove-linked-list-elements/description/

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
    public ListNode removeElements(ListNode head, int val) {
        
        //handle the case where head itself is going to be removed
        while(head != null && head.val == val){
            head = head.next;
        }

        ListNode current = head;
        ListNode prev = null;

        while(current != null){
            if(current.val == val){
                // Remove the current node by linking the previous node to the next node
                if(prev != null){
                    prev.next = current.next;
                }
            }else{
                prev = current;
            }
            current = current.next;
        }
        return head;
    }
}



//___________________________________________________________________________________________________________________________
Q35: https://leetcode.com/problems/implement-stack-using-queues/description/

class MyStack {

    private Queue<Integer> queue;

    public MyStack() {
        queue = new LinkedList<>();
    }
    
    public void push(int x) {
        //add the elemnt to the queue
        queue.offer(x);

        int size = queue.size();
        for(int i = 1; i < size; i++){
            queue.offer(queue.poll());
        }
    }
    
    public int pop() {
        return queue.poll();
    }
    
    public int top() {
        return queue.peek();
    }
    
    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */



//___________________________________________________________________________________________________________________________
Q36: https://leetcode.com/problems/final-array-state-after-k-multiplication-operations-i/description/

class Solution {
    public int[] getFinalState(int[] nums, int k, int multiplier) {

        for(int count = 1; count <= k; count++){
            int minElement = Integer.MAX_VALUE;
            int minElementIndex = -1;  
            
            for(int i = 0; i < nums.length; i++){
           
                if(nums[i] < minElement){
                    minElement = nums[i];
                    minElementIndex = i;
            
                }
            }
            
            nums[minElementIndex] *= multiplier;
        }
       
        return nums;
    }
}



//___________________________________________________________________________________________________________________________
Q37: https://leetcode.com/problems/design-linked-list/description/

class MyLinkedList {


    class Node{
        private int val;
        private Node next, prev;

        Node(int val){
            this.val = val;
        }
    }
    
    
    private Node head, tail;
    private int size;

    
    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    
    
    public int get(int index) {

        if(index < 0 || index >= size){
            return -1;
        }

        Node current = head;
        
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        
        return current.val;
    }
    

    public void addAtHead(int val) {
        
        Node newNode = new Node(val);
        
        if(size == 0){
            head = newNode;
            tail = newNode; 
        }else{
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        
        size++;
    }
    

    public void addAtTail(int val) {
        
        Node newNode = new Node(val);
        
        if(size == 0){
            head = newNode;
            tail = newNode; 
        }else{
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        
        size++;
    }
    
    public void addAtIndex(int index, int val) {
        
        if(index < 0 || index > size){
            return;
        }

        if(index == 0){
            addAtHead(val);
            return;
        }

        if(index == size){
            addAtTail(val);
            return;
        }

        Node newNode = new Node(val);
        Node current = head;

        for(int i = 0; i < index; i++){
            current = current.next;
        }

        newNode.next = current;
        newNode.prev = current.prev;
        current.prev.next = newNode;
        current.prev = newNode;
        size++;
    }
    
    public void deleteAtIndex(int index) {

        Node current = head;

        if(index < 0 || index >= size){
            return;
        }

        if(index == 0){
            head = head.next;
            if(head != null){
                head.prev = null;
            }
        }else if(index == size-1){
            tail = tail.prev;
            if(tail != null){
                tail.next = null;
            }
        }else{
            Node currnt = head;
            for(int i = 0; i < index; i++){
                current = current.next;
            }
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        size--;
        if(size == 0){
            head = null;
            tail = null;
        }
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */