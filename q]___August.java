Q1: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/

//TLE on 203 test case 

// class Solution {
//     public int maxProfit(int[] prices) {
//         int maxProfit = 0;
//         int n = prices.length;
//         for(int i=0; i<n; i++){
//             for(int j=i+1; j<n; j++){
//                 int currentProfit = prices[j] - prices[i];
//                 if(currentProfit > maxProfit){
//                     maxProfit = currentProfit; 
//                 }
//             }
//         }
//         return maxProfit;
//     }
// }

class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;
        for(int price : prices){
            if(price < minPrice){
                minPrice = price;
            }else if(price - minPrice > maxProfit){
                maxProfit = price - minPrice;
            }
        }
        return maxProfit;
    }
}



//-------------------------------------------------------------------------------
Q2: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/

class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for(int i=1; i<prices.length; i++){
            if(prices[i-1] < prices[i]){
                maxProfit += prices[i] - prices[i-1];
            }
        }
        return maxProfit;
    }
}



//-------------------------------------------------------------------------------
Q3: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/

//Brute Force Approach TLE on test case 207 out of 214

// class Solution {
//     public int maxProfit(int[] prices) {
//         int maxProfit = 0;
//         int n = prices.length;

//         for(int i=0; i<n; i++){
//             int firstTransaction = calculate(0, i, prices);
//             int secondTransaction = calculate(i+1, n-1, prices);

//             maxProfit = Math.max(maxProfit , firstTransaction + secondTransaction);
//         }

//         return maxProfit;
//     }

//     static int calculate(int start, int end, int[] prices){
//         int maxProfit = 0;
//         int minPrice = Integer.MAX_VALUE;
//         for(int i=start; i<=end; i++){
//             minPrice = Math.min(minPrice, prices[i]);
//             maxProfit = Math.max(maxProfit, prices[i] - minPrice);
//         }

//         return maxProfit;
//     }
// }

//optimized Solution O(n)
class Solution {
    public int maxProfit(int[] prices) {
        int firstBuy = Integer.MIN_VALUE;
        int firstSell = 0;
        int secondBuy = Integer.MIN_VALUE;
        int secondSell = 0;

        for(int price : prices){
            firstBuy = Math.max(firstBuy, -price);
            firstSell = Math.max(firstSell, firstBuy + price);
            secondBuy = Math.max(secondBuy, firstSell - price);
            secondSell = Math.max(secondSell, secondBuy + price);
        }
        return secondSell;
    }
}



//-------------------------------------------------------------------------------
Q4: https://leetcode.com/problems/number-of-smooth-descent-periods-of-a-stock/

class Solution {
    public long getDescentPeriods(int[] prices) {
        long numberOfPeriods = 1;
        int n = prices.length;
        int descentLength = 1;
        for(int i=1; i<n; i++){
            if(prices[i-1] - prices[i] == 1){
                descentLength++;
            }else{
                descentLength = 1;
            }
            numberOfPeriods += descentLength;
        }
        return numberOfPeriods;
    }
}



//-------------------------------------------------------------------------------
Q5: https://leetcode.com/problems/rotate-array/description/

//TLE for the 37 test case out of 38

// class Solution {
//     public void rotate(int[] nums, int k) {
//         int n = nums.length;
//         for(int i=0; i<k; i++){

//             int lastElement = nums[n-1];

//             for(int j=n-1; j>0; j--){
//                 nums[j] = nums[j-1];
//             }

//             nums[0] = lastElement;
//         }
//     }
// }

//optimized Solution
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;

        //Normalize k to avoid unnecessary rotation
        k = k%n;
        
        reverse(0, n-1, nums);
        reverse(0, k-1, nums);
        reverse(k, n-1, nums);
    }

    static void reverse(int start, int end, int[] nums){
        while(start <= end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}



//-------------------------------------------------------------------------------
Q6: https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/description/

class Solution {
    public boolean check(int[] nums) {
        int n = nums.length;
        int count = 0;
        for(int i=0; i<n; i++){
            if(nums[(i+1) % nums.length] == nums[i]){
                continue;
            }
            if(nums[i] > nums[(i+1) % nums.length]){
                count++;
            }
            if(count > 1){
                return false;
            }
        }
        return true;
    }
}



//-------------------------------------------------------------------------------
Q7: https://leetcode.com/problems/single-number/description/

class Solution {
    public int singleNumber(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        for(int i= 0; i<n-1; i+=2){
            if(nums[i] != nums[i+1]){
                return nums[i];
            }
        } 
        return nums[nums.length-1]; 
    }
}



//-------------------------------------------------------------------------------
Q8: https://leetcode.com/problems/single-number-ii/

class Solution {
    public int singleNumber(int[] nums) {
        Arrays.sort(nums); 
        
        for (int i = 0; i < nums.length - 1; i += 3) {
            if (nums[i] != nums[i + 1]) {
                return nums[i]; 
            }
        }
        
        return nums[nums.length - 1];
    }
}



//-------------------------------------------------------------------------------
Q9: https://leetcode.com/problems/three-divisors/description/

class Solution {
    public boolean isThree(int n) {
        int count = 0;
        for(int i=1; i<=n; i++){
            if(n%i == 0){
                count++;
            }
        }
        if(count == 3){
            return true;
        }
        return false;
    }
}



//-------------------------------------------------------------------------------
Q10: https://leetcode.com/problems/majority-element/

class Solution {
    public int majorityElement(int[] nums) {
        int candidate = findCandidate(nums);

        return candidate;
    }

    static int findCandidate(int[] nums){
        int count = 0;
        int candidate = 0;

        for(int num : nums){
            if(count == 0){
                candidate = num;
            }

            if(num == candidate){
                count++;
            }else{
                count --;
            }
        }

        return candidate;
    }
}



//-------------------------------------------------------------------------------
Q11: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/

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
// class Solution {
//     public int[] twoSum(int[] numbers, int target) {
//         int n = numbers.length;
//         int[] ans = new int[2];
//         int left = 0;
//         int right = n-1;
//         while(left<right){
//             int mid = left + (right-left)/2;
//             int sum = numbers[left] + numbers[right];
//             if(sum == target){
//                 ans[0] = left+1;
//                 ans[1] = right+1;
//                 return ans;
//             }else if(sum < target){
//                 if(numbers[right] + numbers[mid] < target){
//                     left = mid+1;
//                 }
//                 else left++;
//             }else{
//                 if(numbers[left] + numbers[mid] > target){
//                     right = mid-1;
//                 }
//                 else right--;
//             }
//         }
//         return null;
//     }
// }


//Two Pointer Approach 
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while(left < right){
            int sum = numbers[left] + numbers[right];
            if(sum == target){
                return new int[] {left+1, right+1};
            }else if(sum < target){
                left++;
            }else{
                right--;
            }
        }   
        return null;     
    }
}



//-------------------------------------------------------------------------------
Q12: https://leetcode.com/problems/fruit-into-baskets/description/

//Time Complexity (n^2)   //TLE at testcase 70 out of 91
// class Solution {
//     public int totalFruit(int[] fruits) {
//         int n = fruits.length;
//         int maxFruits = 0;

        //Iterate over all possible starting points
//         for(int i=0; i<n; i++){

//             //Using Hashset here for avoiding duplicates 
//             HashSet<Integer> basket = new HashSet<>();
//             int count = 0;

//             for(int j=i; j<n; j++){
//                 basket.add(fruits[j]);

//                 //if more two types of store in the basket break 
//                 if(basket.size() > 2) break;

//                 //Increment the fruits collected in sub array 
//                 count++;

//             }

//             //Update the maximum number of fruits collected
//             maxFruits = Math.max(maxFruits, count);
//         }

//         return maxFruits;
//     }
// }


//Sliding Window Approach //Time Complexity O(n)
class Solution {
    public int totalFruit(int[] fruits) {
        HashMap <Integer, Integer> basket= new HashMap<>();
        int left = 0;
        int maxFruits = 0;

        for(int right=0; right<fruits.length; right++){
            //Add current to Basket
            int currentCount = basket.getOrDefault(fruits[right], 0);
            basket.put(fruits[right], currentCount+1);

            //If the basket contains more than two types of fruits then shrink the window
            while(basket.size() > 2){
                int fruitCount = basket.get(fruits[left]);
                if(fruitCount == 1){
                    basket.remove(fruits[left]);
                }else{
                    basket.put(fruits[left], fruitCount - 1);
                }
                //left++ shrink the window 
                left++;
            }
            maxFruits = Math.max(maxFruits, right-left+1);
        }
        return maxFruits;
    }
}



//-------------------------------------------------------------------------------
Q13: https://leetcode.com/problems/minimum-cost-to-make-array-equal/description/

// //BruteForce Approach TLE on Testcase 28 iy of 48

// class Solution {
//     public long minCost(int[] nums, int[] cost) {
//         int minValue = Integer.MAX_VALUE;
//         int maxValue = Integer.MIN_VALUE;

//         // Determine the minimum and maximum values in nums
//         for(int num : nums){
//             minValue = Math.min(minValue, num);
//             maxValue = Math.max(maxValue, num);
//         }

//         long minCost = Long.MAX_VALUE;

//         // Try every value from minValue to maxValue
//         for(int target = minValue; target <= maxValue; target++){
//             long currentCost = 0;

//             // Calculate the cost to make all elements equal to 'target'
//             for(int i=0; i<nums.length; i++){
//                 currentCost += (long) Math.abs(nums[i] - target) * cost[i];
//             }
//             minCost = Math.min(minCost, currentCost);
//         }
//         return minCost;
//     }
// }

//Binary Search Approach
class Solution {
    public long minCost(int[] nums, int[] cost) {
        long left = Long.MAX_VALUE;
        long right = Long.MIN_VALUE;

        // Determine the range for binary search (min and max values in the array)
        for(int i=0; i<nums.length; i++){
            left = Math.min(nums[i], left);
            right = Math.max(nums[i], right);
        }

        // Variable to store the index of the potential optimal value
        long ind = 0;

        // Perform binary search to find the optimal value that minimizes the cost
        while(left <= right){
            long mid = left + (right - left) / 2;

            // Compare the costs for mid and mid + 1 to determine the direction of search
            if(solve(nums, mid, cost) < solve(nums, mid+1, cost)){
                ind = mid;
                right = mid-1;
            }else{
                left = mid + 1;
            }
        }

        // Return the minimum cost calculated for the optimal value found
        return solve(nums, ind, cost);
    }

    // Function to calculate the total cost for converting all nums elements to a specific target value (mid)
    public long solve(int[] nums, long mid, int[] cost){
        long ans = 0;
        for(int i=0; i<nums.length; i++){
            ans += Math.abs(nums[i] - mid) * cost[i];
        }

        return ans;
    }
}



