//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧
// Search in Rotated Sorted Array
// There is an integer array nums sorted in ascending order (with distinct values).
// Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
// Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
// You must write an algorithm with O(log n) runtime complexity.

// Example 1:
// Input: nums = [4,5,6,7,0,1,2], target = 0
// Output: 4

// Example 2:
// Input: nums = [1], target = 0
// Output: -1

class Solution {
    public int search(int[] nums, int target){
        int pivot = findPivot(nums);
        if(pivot == -1){
            return binarySearch1(nums, target, 0, nums.length-1);
        }
        if(nums[pivot]==target){
            return pivot;
        }
        if(target >= nums[0]){
            return binarySearch1(nums, target, 0, pivot-1);
        }
        return binarySearch1(nums, target, pivot+1, nums.length-1);
    }

     int binarySearch1(int[] nums,int target, int start, int end){

        while(start<=end){
            int mid = start+(end-start)/2;
            if(nums[mid]>target){
                end = mid - 1;
            }else if(nums[mid]<target){
                start = mid + 1;
            }else{
                return mid;
            }
        }
        return -1;
    }

     int findPivot(int[] nums) {
    int start = 0;
    int end = nums.length - 1;
    
    while (start <= end) {
        int mid = start + (end - start) / 2;
        
        if (mid < end && nums[mid] > nums[mid + 1]) {
            return mid;
        }
        
        if (mid > start && nums[mid] < nums[mid - 1]) {
            return mid - 1;
        }
        
        if (nums[start] < nums[mid]) {
            start = mid + 1;
        } else {
            end = mid - 1;
        }
    }
    
    return -1;
}

}

//------------------------------------------------------------------------------------

//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧
// Split Array Largest Sum
// Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.
// Return the minimized largest sum of the split.
// A subarray is a contiguous part of the array.

// Example 1:
// Input: nums = [7,2,5,10,8], k = 2
// Output: 18
// Explanation: There are four ways to split nums into two subarrays.
// The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.

class Solution {
    public int splitArray(int[] nums, int m) {
        int start = 0;
        int end = 0;

        for (int i = 0; i < nums.length; i++) {
            start = Math.max(start, nums[i]);
            end += nums[i];
        }

        // binary search
        while (start < end) {
            int mid = start + (end - start) / 2;

            int sum = 0;
            int pieces = 1;
            for(int num : nums) {
                if (sum + num > mid) {
                    sum = num;
                    pieces++;
                } else {
                    sum += num;
                }
            }

            if (pieces > m) {
                start = mid + 1;
            } else {
                end = mid;
            }

        }
        return end; 
    }
}


//------------------------------------------------------------------------------------

//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 Search a 2D Matrix 
// Integers in each row are sorted in ascending from left to right.
// Integers in each column are sorted in ascending from top to bottom.
// Example
// Input: matrix = [[1  ,4,  7,  11, 15],
                //  [2,  5,  8,  12, 19],
                //  [3,  6,  9,  16, 22],
                //  [10, 13, 14, 17, 24],
                //  [18, 21, 23, 26, 30]], target = 5
// Output: true

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
         int r = 0;
        // int c = matrix.length-1; //This is only applicable for the n*n, 2*2,3*3,4*4 matrix 
        int c = matrix[0].length-1; //This will work for n*m, 2*3,3*4,4*2
        while(r<matrix.length && c>=0){
            if(matrix[r][c] == target){
                return true;
            }else if(matrix[r][c] < target){
                r++;
            }else{
                c--;
            }
        }
        return false;
    }
}


//------------------------------------------------------------------------------------

//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 Search a 2D Matrix 
// 74. Search a 2D Matrix
// You are given an m x n integer matrix matrix with the following two properties:
// Each row is sorted in non-decreasing order.
// The first integer of each row is greater than the last integer of the previous row.

public class binarySearch2D {  
     public static void main(String[] args) {
      
    int[][] arr = {
        {11, 22, 33},
        {44, 55, 67},
        {77, 88, 99}
    };
    System.out.println(Arrays.toString(search2(arr, 11)));
    }

    static int[] binarySearch(int[][] matrix, int target, int row, int cStart, int cEnd){
        while(cStart<=cEnd){
            int mid = cStart+(cEnd-cStart)/2;
            if(matrix[row][mid]==target){
                return new int[]{row,mid};
            }else if(matrix[row][mid]<target){
                cStart = mid+1;
            }else{
                cEnd = mid-1;
            }
        }
        return new int[]{-1,-1};
    }

    static int[] search2(int[][] matrix, int target){
        int rows = matrix.length;
        int cols = matrix[0].length;

        if(cols == 0){
            return new int[]{-1,-1};
        }
        if(rows == 1){
            return binarySearch(matrix, target, 0, 0, cols-1);
        }

        int rStart = 0;
        int rEnd = rows-1;
        int cMid = cols/2;

        // run the loop till 2 rows are remaining
        while(rStart< (rEnd-1)){
            int mid = rStart +(rEnd-rStart)/2;
            if(matrix[mid][cMid] == target){
                return new int[]{mid,cMid};
            }else if (matrix[mid][cMid] < target){
                rStart = mid;
            }else{
                rEnd = mid;
            }
        }

        // now we have two rows
        // check whether the target is in the col of 2 rows
        if(matrix[rStart][cMid]==target){
            return new int[]{rStart,cMid};
        }
        if(matrix[rStart+1][cMid]==target){
            return new int[]{rStart+1,cMid};
        }

        // search in 1st half
        if(target <= matrix[rStart][cMid-1]){
            return binarySearch(matrix, target, rStart, 0, cMid-1);
        }
        // search in 2nd half
         if(target >= matrix[rStart][cMid+1] && target <= matrix[rStart][cols - 1]){
            return binarySearch(matrix, target, rStart, cMid+1, cols-1);
        }
        // search in 3rd half
         if(target <= matrix[rStart+1][cMid-1]){
            return binarySearch(matrix, target, rStart+1, 0, cMid-1);
        }
        // search in 4th half
        else{
            return binarySearch(matrix, target, rStart+1, cMid+1, cols-1);
        }
    }
}