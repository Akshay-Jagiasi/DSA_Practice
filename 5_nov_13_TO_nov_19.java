//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 1
https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/

// O(n^2)
// class Solution {
//     public int[] twoSum(int[] numbers, int target) {
//         int n = numbers.length;
//         int[] result = new int[2];
//         for(int i=1; i<=n; i++){
//             for(int j=i+1; j<=n; j++){
//                 if(numbers[i-1]+numbers[j-1] == target){
//                     result[0] = i;
//                     result[1] = j;
//                     return result;
//                 }
//             }
//         }
//         return null;
//     }
// }

// O(nlogn)
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        for(int i=0; i<n; i++){
            int complement = target - numbers[i];
            int index = binarySearch(numbers, complement, i+1, n-1);

            if(index != -1){
                return new int[]{i+1, index+1};
            }
        }
        return null;
    }

    int binarySearch(int[] numbers, int complement, int start, int end){
        while(start<=end){
            int mid = start + (end-start)/2;

            if(numbers[mid] == complement){
                return mid;
            }else if(numbers[mid] < complement){
                start = mid+1;
            }else{
                end = mid-1;
            }
        }
        return -1;
    }
}



//------------------------------------------------------------------------------------
//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 2
https://leetcode.com/problems/valid-perfect-square/description/

class Solution {
    public boolean isPerfectSquare(int num) {
        if(num < 2){
            return true; //0 and 1 are the perfect square
        }

        long left = 2;
        long right = num/2;
        while(left <= right){
            long mid = left + (right-left)/2;
            long square = mid*mid;
            if(square == num){
                return true;
            }else if(square < num){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return false;
    }
}



//------------------------------------------------------------------------------------
//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 3
https://leetcode.com/problems/search-insert-position/description/

class Solution {
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;

        while(start<=end){
            int mid = start+(end-start)/2;
            if(target == nums[mid]){
                return mid;
            }else if(target < nums[mid]){
                end = mid-1;
            }else{
                start = mid+1;
            }
        }

        return start;
    }
}



//------------------------------------------------------------------------------------
//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 4
https://leetcode.com/problems/binary-search/description/

class Solution {
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;
        while(start<=end){
            int mid = start+(end-start)/2;
            if(target<nums[mid]){
                end = mid-1;
            }else if(target>nums[mid]){
                start=mid+1;
            }else{
                return mid;
            }
        }
        return -1;
    }
}



//------------------------------------------------------------------------------------
//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 5
https://leetcode.com/problems/check-if-n-and-its-double-exist/description/

class Solution {
    public boolean checkIfExist(int[] arr) {
        int n = arr.length;
        int zeroCount = 0;
        Arrays.sort(arr);
        for(int i=0; i<n; i++){
            int target = arr[i] * 2;
            if (arr[i] == 0) {
                zeroCount++;
                if (zeroCount >= 2) {
                    return true;
                }
            }
            if(target != 0 && binarySearch(arr, 0, n-1, target)){
                return true;
            }
        }
        return false;
    }

    boolean binarySearch(int[] arr, int start, int end, int target){
        while(start<=end){
            int mid = start+(end-start)/2;
            if(target == arr[mid]){
                return true;
            }else if(target<arr[mid]){
                end = mid-1;
            }else{
                start=mid+1;
            }
        }
        return false;
    }
}



//------------------------------------------------------------------------------------
//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 6
https://leetcode.com/problems/shuffle-string/description/

class Solution {
    public String restoreString(String s, int[] indices) {
        char[] result = new char[s.length()];

        for(int i=0; i<s.length(); i++){
            result[indices[i]] = s.charAt(i);
        }
        return new String(result);
    }
}