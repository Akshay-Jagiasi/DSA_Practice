//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧
// Find Numbers with Even Number of Digits
// Given an array nums of integers, return how many of them contain an even number of digits.

// Example 1:
// Input: nums = [555,901,482,1771]
// Output: 1 
// Explanation: 
// Only 1771 contains an even number of digits.

class Solution {
    public int findNumbers(int[] nums) {
        int count = 0;
        for(int num : nums){
            if(even(num)){
                count ++;
            }
        } 
        return count;
    }

    static boolean even(int num){
        int numOfDigits = digits2(num);
        if(numOfDigits % 2 == 0){
            return true;
        }
        return false;
    }

    // static int  digits(int num){
    //     int count = 0;
    //     while(num>0){
    //         count ++;
    //         num=num/10;
    //     }
    //     return count;
    // }

    static int digits2(int num){
        if(num<0){
            num = num*-1;
        }
        return (int)(Math.log10(num))+1;
    }
}


//------------------------------------------------------------------------------------

//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧
// Find Smallest Letter Greater Than Target
// You are given an array of characters letters that is sorted in non-decreasing order, 
// and a character target. There are at least two different characters in letters.
// Return the smallest character in letters that is lexicographically greater than target.
// If such a character does not exist, return the first character in letters.

// Example 1:
// Input: letters = ["c","f","j"], target = "a"
// Output: "c"
// Explanation: The smallest character that is lexicographically greater than 'a' in letters is 'c'.

class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int start = 0;
        int end = letters.length -1;

        while(start<=end){
            int mid = start+(end-start)/2;
            if(letters[mid]>target){
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return letters[start%letters.length];
    }
}


//------------------------------------------------------------------------------------

//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧
// Find First and Last Position of Element in Sorted Array
// Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of
// a given target value.

// If target is not found in the array, return [-1, -1].
// You must write an algorithm with O(log n) runtime complexity.

// Example 1:
// Input: nums = [5,7,7,8,8,10], target = 8
// Output: [3,4]

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] ans = {-1,-1};
        int start = search(nums,target,true);
        int end = search(nums,target,false);

        ans[0]=start;
        ans[1]=end;

        return ans;
    }

    int search(int[] nums, int target, boolean findStartIndex){
        int ans = -1;
        int start = 0;
        int end = nums.length - 1;
        while(start<=end){
            int mid = start+(end-start)/2;

            if(target<nums[mid]){
                end=mid-1;
            }else if (target>nums[mid]){
                start = mid + 1;
            }else{
                ans=mid;
                if(findStartIndex){
                    end=mid-1;
                }else{
                    start=mid+1;
                }
            }
        }
        return ans;
    }
}


//------------------------------------------------------------------------------------

//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧
// Find Peak Element
// A peak element is an element that is strictly greater than its neighbors.
// Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
// You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.
// You must write an algorithm that runs in O(log n) time.

// Example 1:
// Input: nums = [1,2,3,1]
// Output: 2
// Explanation: 3 is a peak element and your function should return the index number 2.

class Solution {
    public int findPeakElement(int[] nums) {
        int start = 0;
        int end = nums.length-1;
        while(start<end){
            int mid = start+(end-start)/2;
            if (nums[mid] > nums[mid+1]){
                //you are in the descending part of the arrya this may be ans, but look at left this is why end != mid-1
                end = mid;
            }else{
                //you are in asc part of the arrya 
                start = mid+1;
            }

        }
        //in the end start == end and pointing the largest number because of the above checks
        return start;//or end as both are equal
    }
}

//------------------------------------------------------------------------------------

//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧
// Find in Mountain Array
// You may recall that an array arr is a mountain array if and only if:
// arr.length >= 3
// There exists some i with 0 < i < arr.length - 1 such that:
// arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
// arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
// Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target. 
// If such an index does not exist, return -1.
// You cannot access the mountain array directly. You may only access the array using a MountainArray interface:
// MountainArray.get(k) returns the element of the array at index k (0-indexed).
// MountainArray.length() returns the length of the array.
// Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer.
//1 Also, any solutions that attempt to circumvent the judge will result in disqualification.

// Example 1:
// Input: array = [1,2,3,4,5,3,1], target = 3
// Output: 2
// Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.

// Example 2:
// Input: array = [0,1,2,4,2,1], target = 3
// Output: -1
// Explanation: 3 does not exist in the array, so we return -1.

class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int peak = peakIndexInMountainArray(mountainArr);
        int firstTry = binarySearch(mountainArr, target, 0, peak, true);
        if (firstTry != -1) {
            return firstTry;
        }
        return binarySearch(mountainArr, target, peak + 1, mountainArr.length() - 1, false);
    }

    int peakIndexInMountainArray(MountainArray arr) {
        int start = 0;
        int end = arr.length() - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr.get(mid) > arr.get(mid + 1)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    int binarySearch(MountainArray arr, int target, int start, int end, boolean isAscending) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int midElement = arr.get(mid);
            if (midElement == target) {
                return mid;
            }
            if (isAscending) {
                if (midElement < target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else {
                if (midElement < target) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return -1;
    }
}

//------------------------------------------------------------------------------------
//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧: Binary search in infinite array means you cannot use the .length for finding the end of the nums array
    static int binarySearchInfinitenumsay(int[] nums, int target){
        int start = 0;
        int end = 1;

        while(target > nums[end]){
            int newStart = end +1;
            end = end + (end-start+1)*2;
            start = newStart;
        }
        return binarySearch1(nums, target, start, end);
    }

    static int binarySearch1(int[] nums,int target, int start, int end){

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