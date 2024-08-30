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

