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

