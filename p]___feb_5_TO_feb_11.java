Q: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/

// O(n^2)
// Runtime 414ms
// Beats 5.59% of users with Java
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
// Runtime 4ms
// Beats 20.47% of users with Java
// class Solution {
//     public int[] twoSum(int[] numbers, int target) {
//         int n = numbers.length;
//         for(int i=0; i<n; i++){
//             int complement = target - numbers[i];
//             int index = binarySearch(numbers, complement, i+1, n-1);
//             if(index != -1){
//                 return new int[]{i+1, index+1};
//             }
//         }
//         return null;
//     }
//     int binarySearch(int[] numbers, int complement, int start, int end){
//         while(start<=end){
//             int mid = start + (end-start)/2;
//             if(numbers[mid] == complement){
//                 return mid;
//             }else if(numbers[mid] < complement){
//                 start = mid+1;
//             }else{
//                 end = mid-1;
//             }
//         }
//         return -1;
//     }
// }



// O(n)
// Runtime 6ms
// Beats 16.01% of users with Java
// class Solution {
//     public int[] twoSum(int[] numbers, int target) {
//         int n = numbers.length;
//         Map<Integer, Integer> hashmap = new HashMap<>();
//         for(int i=0; i<n; i++){
//             int complement = target - numbers[i];
//             if(hashmap.containsKey(complement)){
//                 return new int[]{hashmap.get(complement) + 1, i + 1};
//             }
//             hashmap.put(numbers[i] , i);
//         }
//         return null;
//     }
// }



// O(n)
// Runtime 1ms
// Beats 99.70% of users with Java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int[] ans = new int[2];
        int left = 0;
        int right = n-1;
        while(left<right){
            int mid = left + (right-left)/2;
            int sum = numbers[left] + numbers[right];
            if(sum == target){
                ans[0] = left+1;
                ans[1] = right+1;
                return ans;
            }else if(sum < target){
                if(numbers[right] + numbers[mid] < target){
                    left = mid+1;
                }
                else left++;
            }else{
                if(numbers[left] + numbers[mid] > target){
                    right = mid-1;
                }
                else right--;
            }
        }
        return null;
    }
}