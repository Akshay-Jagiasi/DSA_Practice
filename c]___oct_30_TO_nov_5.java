//------------------------------------------------------------------------------------

//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 1
// Concatenation of Array
// Given an integer array nums of length n, you want to create an array ans of length 2n
// where ans[i] == nums[i] and ans[i + n] == nums[i] for 0 <= i < n (0-indexed).
// Specifically, ans is the concatenation of two nums arrays.
// Return the array ans.

// Example 1:
// Input: nums = [1,2,1]
// Output: [1,2,1,1,2,1]
// Explanation: The array ans is formed as follows:
// - ans = [nums[0],nums[1],nums[2],nums[0],nums[1],nums[2]]
// - ans = [1,2,1,1,2,1]

class Solution {
    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] ans = new int[2*n];
        for(int i =0; i<n;i++){
            ans[i]=nums[i];
            ans[i+n]=nums[i];
        }
        return ans;
    }
}

//------------------------------------------------------------------------------------

//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 2
// Richest Customer Wealth
// You are given an m x n integer grid accounts where accounts[i][j] is the amount of money
// the i​​​​​​​​​​​th​​​​ customer has in the j​​​​​​​​​​​th​​​​ bank. Return the wealth that the richest customer has.
// A customer's wealth is the amount of money they have in all their bank accounts.
// The richest customer is the customer that has the maximum wealth.

// Example 1:
// Input: accounts = [[1,2,3],[3,2,1]]
// Output: 6
// Explanation:
// 1st customer has wealth = 1 + 2 + 3 = 6
// 2nd customer has wealth = 3 + 2 + 1 = 6
// Both customers are considered the richest with a wealth of 6 each, so return 6.

class Solution {
   public int maximumWealth(int[][] accounts){
    int maxWealth = 0;
    for(int i=0;i<accounts.length;i++){
        int wealth = 0;
        for(int j=0 ;j<accounts[i].length;j++){
            wealth +=accounts[i][j];
        }
        maxWealth = Math.max(maxWealth, wealth);
   }
    return maxWealth;
    }
}


//------------------------------------------------------------------------------------

//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 3
// Shuffle the Array
// Given the array nums consisting of 2n elements in the form [x1,x2,...,xn,y1,y2,...,yn].
// Return the array in the form [x1,y1,x2,y2,...,xn,yn].

// Example 1:
// Input: nums = [2,5,1,3,4,7], n = 3
// Output: [2,3,5,4,1,7] 
// Explanation: Since x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 then the answer is [2,3,5,4,1,7].
class Solution {
    public int[] shuffle(int[] nums, int n) {
        int[] ans = new int[2 * n];
        for (int i = 0; i < n; i++) {
            ans[i * 2] = nums[i];
            ans[i * 2 + 1] = nums[i + n];
        }
        return ans;
    }
}


//------------------------------------------------------------------------------------

//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 4
// . Kids With the Greatest Number of Candies
// There are n kids with candies. You are given an integer array candies, where each candies[i] represents 
// the number of candies the ith kid has, and an integer extraCandies, denoting the number of extra candies that you have.
// Return a boolean array result of length n, where result[i] is true if, after giving the ith kid all the extraCandies,
// they will have the greatest number of candies among all the kids, or false otherwise.
// Note that multiple kids can have the greatest number of candies.

// Example 1:

// Input: candies = [2,3,5,1,3], extraCandies = 3
// Output: [true,true,true,false,true] 
// Explanation: If you give all extraCandies to:
// - Kid 1, they will have 2 + 3 = 5 candies, which is the greatest among the kids.
// - Kid 2, they will have 3 + 3 = 6 candies, which is the greatest among the kids.
// - Kid 3, they will have 5 + 3 = 8 candies, which is the greatest among the kids.
// - Kid 4, they will have 1 + 3 = 4 candies, which is not the greatest among the kids.
// - Kid 5, they will have 3 + 3 = 6 candies, which is the greatest among the kids.
class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int n = candies.length;
        boolean[] ans = new boolean[n];
        int maxCandies =0;
        for(int i=0;i<n;i++){
            maxCandies = Math.max(maxCandies, candies[i]);
        }
        for(int i=0;i<n;i++){
            ans[i] = (candies[i]+extraCandies)>=maxCandies;
        }

        List<Boolean> resultList = new ArrayList<>();
        for(int i = 0; i<n;i++){
            resultList.add(ans[i]);
        }

        return resultList;
    }
}


//------------------------------------------------------------------------------------

//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 5
// Number of Good Pairs
// Given an array of integers nums, return the number of good pairs.
// A pair (i, j) is called good if nums[i] == nums[j] and i < j.

// Example 1:
// Input: nums = [1,2,3,1,1,3]
// Output: 4
// Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.
class Solution {
    public int numIdenticalPairs(int[] nums) {
        int n = nums.length;
        int pairs = 0;
        for(int i =0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(nums[i]==nums[j]){
                    pairs++;
                }
            }
        }
        return pairs;
    }
}


//------------------------------------------------------------------------------------

//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 6
// How Many Numbers Are Smaller Than the Current Number
// Given the array nums, for each nums[i] find out how many numbers in the array are 
// smaller than it. That is, for each nums[i] you have to count the number of
// valid j's such that j != i and nums[j] < nums[i].
// Return the answer in an array.

// Example 1:
// Input: nums = [8,1,2,2,3]
// Output: [4,0,1,1,3]
// Explanation: 
// For nums[0]=8 there exist four smaller numbers than it (1, 2, 2 and 3). 
// For nums[1]=1 does not exist any smaller number than it.
// For nums[2]=2 there exist one smaller number than it (1). 
// For nums[3]=2 there exist one smaller number than it (1). 
// For nums[4]=3 there exist three smaller numbers than it (1, 2 and 2).
class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for(int i =0;i<n;i++){
            int count = 0;
            for(int j=0;j<n;j++){
                if(nums[i]>nums[j]){
                    count++;
                }
            }
        ans[i]=count;
        }
        return ans;
    }
}


//------------------------------------------------------------------------------------

//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 7
// Create Target Array in the Given Order
// Given two arrays of integers nums and index. Your task is to create target array under the following rules:
// Initially target array is empty.
// From left to right read nums[i] and index[i], insert at index index[i] the value nums[i] in target array.
// Repeat the previous step until there are no elements to read in nums and index.
// Return the target array.
// It is guaranteed that the insertion operations will be valid.

// Example 1:
// Input: nums = [0,1,2,3,4], index = [0,1,2,2,1]
// Output: [0,4,1,3,2]
// Explanation:
// nums       index     target
// 0            0        [0]
// 1            1        [0,1]
// 2            2        [0,1,2]
// 3            2        [0,1,3,2]
// 4            1        [0,4,1,3,2]
class Solution {
    public int[] createTargetArray(int[] nums, int[] index) {
        int n = nums.length;
        int[] target = new int[n];  
        for (int i = 0; i < n; i++) {
            int currentIndex = index[i];
            for (int j = n - 1; j > currentIndex; j--) {
                target[j] = target[j - 1];
            }
            target[currentIndex] = nums[i];
        }
        return target;
    }
}


//------------------------------------------------------------------------------------

//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 8
// Check if the Sentence Is Pangram
// A pangram is a sentence where every letter of the English alphabet appears at least once.
// Given a string sentence containing only lowercase English letters, return true if sentence is a pangram, or false otherwise.

// Example 1:
// Input: sentence = "thequickbrownfoxjumpsoverthelazydog"
// Output: true
// Explanation: sentence contains at least one of every letter of the English alphabet.
class Solution {
    public boolean checkIfPangram(String sentence) {
        boolean[] alphabet = new boolean[26];
        for (int i = 0; i < sentence.length(); i++) {
            char currentChar = sentence.charAt(i);
            if (currentChar >= 'a' && currentChar <= 'z') {
                alphabet[currentChar - 'a'] = true;
            }
        }
        for (boolean letterPresent : alphabet) {
            if (!letterPresent) {
                return false;
            }
        }
        return true;
    }
}


//------------------------------------------------------------------------------------

//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 9
// Build Array from Permutation
// Given a zero-based permutation nums (0-indexed), build an array ans of the same length
// where ans[i] = nums[nums[i]] for each 0 <= i < nums.length and return it.
// A zero-based permutation nums is an array of distinct integers from 0 to nums.length - 1 (inclusive).

// Example 1:
// Input: nums = [0,2,1,5,3,4]
// Output: [0,1,2,4,5,3]
// Explanation: The array ans is built as follows: 
// ans = [nums[nums[0]], nums[nums[1]], nums[nums[2]], nums[nums[3]], nums[nums[4]], nums[nums[5]]]
//     = [nums[0], nums[2], nums[1], nums[5], nums[3], nums[4]]
//     = [0,1,2,4,5,3]
class Solution {
    public int[] buildArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n]; // Create a new array to store results
        
        // Iterate through each index and populate ans[i] = nums[nums[i]]
        for (int i = 0; i < n; i++) {
            ans[i] = nums[nums[i]];
        }
        
        return ans; // Return the resulting array
    }
} //Time Complexity O(n) Space Complexity O(n)


class Solution {
    public int[] buildArray(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) { 
            int newValue = nums[i]; // Store the new value
            int oldValue = nums[nums[i]] % n; // Store the old value
            // Encode both values at this index
            nums[i] = newValue + (oldValue * n);
        }
        // Decode the new values and store them back
        for (int i = 0; i < n; i++) {
            nums[i] /= n;
        }
        return nums; 
    }
} //Time Complexity O(n) Space Complexity O(1)



//------------------------------------------------------------------------------------

//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 10
// Find the Highest Altitude
// There is a biker going on a road trip. The road trip consists of n + 1 points at different altitudes. The biker starts his trip on point 0 with altitude equal 0.
// You are given an integer array gain of length n where gain[i] is the net gain in altitude between points i​​​​​​ and i + 1 for all (0 <= i < n). Return the highest altitude of a point.

// Example 1:
// Input: gain = [-5,1,5,0,-7]
// Output: 1
// Explanation: The altitudes are [0,-5,-4,1,1,-6]. The highest is 1.
class Solution {
    public int largestAltitude(int[] gain) {
        int n = gain.length;
        int maxAltitude = 0;
        int currentAltitude = 0;
        for(int i=0;i<n;i++){
            currentAltitude = currentAltitude+gain[i];
            maxAltitude = Math.max(maxAltitude, currentAltitude);
        }
        return maxAltitude;
    }
}

//------------------------------------------------------------------------------------

//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 11
// Flipping an Image
// Given an n x n binary matrix image, flip the image horizontally, then invert it, and return the resulting image.
// To flip an image horizontally means that each row of the image is reversed.
// For example, flipping [1,1,0] horizontally results in [0,1,1].
// To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0.
// For example, inverting [0,1,1] results in [1,0,0].

// Example 1:
// Input: image = [[1,1,0],[1,0,1],[0,0,0]]
// Output: [[1,0,0],[0,1,0],[1,1,1]]
// Explanation: First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].
// Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]
class Solution {
    public int[][] flipAndInvertImage(int[][] image) {
        int rows = image.length;
        int cols = image[0].length;

        for (int i = 0; i < rows; i++) {
            int left = 0;
            int right = cols - 1;
            while (left < right) {
                int temp = image[i][left];
                image[i][left] = image[i][right];
                image[i][right] = temp;
                left++;
                right--;
            }
            for(int j=0;j<cols;j++){
            image[i][j] = 1- image[i][j];
            }
        }
        return image;
    }
}
