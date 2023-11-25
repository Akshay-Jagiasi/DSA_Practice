//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 https://leetcode.com/problems/4sum/

//TIME COMPLEXITY O(n^4) bruteforce
// class Solution {
//     public List<List<Integer>> fourSum(int[] nums, int target) {
//         int n = nums.length;
//         Arrays.sort(nums);
//         List<List<Integer>> result = new ArrayList<>();
//         for(int i=0; i<n-3; i++){
//             if (i > 0 && nums[i] == nums[i - 1]) {
//                 continue;//skip duplicates
//             }
//             for(int j=i+1; j<n-2; j++){
//                 if (j > i + 1 && nums[j] == nums[j - 1]) {
//                     continue;//skip duplicates
//                 }
//                 for(int k=j+1; k<n-1; k++){
//                     if (k > j + 1 && nums[k] == nums[k - 1]) {
//                         continue;//skip duplicates
//                     }  
//                     for(int l=k+1; l<n; l++){
//                         if (l > k + 1 && nums[l] == nums[l - 1]) {
//                            continue;//skip duplicates
//                         } 
//                         long ans = (long)nums[i]+nums[j]+nums[k]+nums[l];
//                         if(ans == target){
//                             result.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
//                         }
//                     }
//                 }
//             }
//         }
//         return result;
//     }
// }

//TIME COMPLEXITY O(n^3)
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int i=0; i<n-3; i++){
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;//skip duplicates
            }
            for(int j=i+1; j<n-2; j++){
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;//skip duplicates
                }
                int left = j+1;
                int right = n-1;
                while(left < right){
                    long ans = (long)nums[i]+nums[j]+nums[left]+nums[right];
                    if(ans == target){
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;//skip duplicates
                        }
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;//skip duplicates
                        }
                    }else if(ans<target){
                       left++;
                    }else{
                        right--;
                    }
                }
            }
        }
        return result;
    }
}