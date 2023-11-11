//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 1
//Matrix Diagonal Sum
// Given a square matrix mat, return the sum of the matrix diagonals.
// Only include the sum of all the elements on the primary diagonal and all the elements on the secondary diagonal that are not part of the primary diagonal.

// Example 1:
// Input: mat = [[1,2,3],
//               [4,5,6],
//               [7,8,9]]
// Output: 25
// Explanation: Diagonals sum: 1 + 5 + 9 + 3 + 7 = 25
// Notice that element mat[1][1] = 5 is counted only once.

// Example 2:
// Input: mat = [[1,1,1,1],
//               [1,1,1,1],
//               [1,1,1,1],
//               [1,1,1,1]]
// Output: 8

class Solution {
    public int diagonalSum(int[][] mat) {
        int n = mat.length;
        int sum = 0;
        for(int i=0; i<n; i++){
            sum += mat[i][i];

            if(i != n-1-i){
                sum += mat[i][n-1-i];
            }
        }
        return sum;
    }
}

//------------------------------------------------------------------------------------

//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 2
// Transpose Matrix
// Given a 2D integer array matrix, return the transpose of matrix.
// The transpose of a matrix is the matrix flipped over its main diagonal, switching the matrix's row and column indices.

// Example 1:
// Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
// Output: [[1,4,7],[2,5,8],[3,6,9]]

// Example 2:
// Input: matrix = [[1,2,3],[4,5,6]

class Solution {
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] transposeMatrix = new int[n][m];
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<n; j++){
                transposeMatrix[j][i] = matrix[i][j];
            }
        }
        return transposeMatrix;
    }
}



//------------------------------------------------------------------------------------

//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 3
// Maximum Population Year
// You are given a 2D integer array logs where each logs[i] = [birthi, deathi] indicates the birth and death years of the ith person.
// The population of some year x is the number of people alive during that year. The ith person is counted in year x's population if x is in the inclusive range [birthi, deathi - 1]. Note that the person is not counted in the year that they die.
// Return the earliest year with the maximum population.

// Example 1:
// Input: logs = [[1993,1999],[2000,2010]]
// Output: 1993
// Explanation: The maximum population is 1, and 1993 is the earliest year with this population.

// Example 2:
// Input: logs = [[1950,1961],[1960,1971],[1970,1981]]
// Output: 1960
// Explanation: 
// The maximum population is 2, and it had happened in years 1960 and 1970.
// The earlier year between them is 1960.

class Solution {
    public int maximumPopulation(int[][] logs) {
        int[] population = new int[2051];

        //Increment population for birth and decrement for death
        for(int[] log : logs){
            population[log[0]]++;
            population[log[1]]--;
        }

        int maxPopulation = 0;
        int maxYear = 0;
        int currentPopulation = 0;

        for(int year=1950; year<=2050; year++){
            currentPopulation += population[year];

            if(currentPopulation > maxPopulation){
                maxPopulation = currentPopulation;
                maxYear = year;
            }
        }
        return maxYear;
    }
}


//------------------------------------------------------------------------------------

//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 4
// Find N Unique Integers Sum up to Zero
// Given an integer n, return any array containing n unique integers such that they add up to 0.

// Example 1:
// Input: n = 5
// Output: [-7,-1,1,3,4]
// Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].

// Example 2:
// Input: n = 3
// Output: [-1,0,1]

// Example 3:
// Input: n = 1
// Output: [0]

class Solution {
    public int[] sumZero(int n) {
        int[] result = new int[n];

        //if n is odd then add 0 in the middle
        if(n%2 != 0){
            result[n/2] = 0;
        }

        for(int i=0; i<n/2;i++){
            result[i] = i+1;
            result[n-1-i] = -result[i];
        }
        return result;
    }
}


//------------------------------------------------------------------------------------

//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 5
// Plus One
// You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.
// Increment the large integer by one and return the resulting array of digits

// Example 1:
// Input: digits = [1,2,3]
// Output: [1,2,4]
// Explanation: The array represents the integer 123.
// Incrementing by one gives 123 + 1 = 124.
// Thus, the result should be [1,2,4].

// Example 2:
// Input: digits = [4,3,2,1]
// Output: [4,3,2,2]
// Explanation: The array represents the integer 4321.
// Incrementing by one gives 4321 + 1 = 4322.
// Thus, the result should be [4,3,2,2].

// Example 3:
// Input: digits = [9]
// Output: [1,0]
// Explanation: The array represents the integer 9.
// Incrementing by one gives 9 + 1 = 10.
// Thus, the result should be [1,0]. 

class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for(int i = n-1; i>=0; i--){
            if(digits[i]<9){
                digits[i]++;
                return digits;
            }else{
                digits[i] = 0;
            }
        }

        int[] result = new int[n+1];
        result[0] = 1;
        return result;
    }
}


//------------------------------------------------------------------------------------

//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 6
// Maximum Subarray
// Given an integer array nums, find the subarray
// with the largest sum, and return its sum.

// Example 1:
// Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
// Output: 6
// Explanation: The subarray [4,-1,2,1] has the largest sum 6.

// Example 2:
// Input: nums = [1]
// Output: 1
// Explanation: The subarray [1] has the largest sum 1.

// Example 3:
// Input: nums = [5,4,-1,7,8]
// Output: 23
// Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.


// O(n^3) solution time limit exceeded
class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int n = nums.length;

        for (int start = 0; start<n; start++){
            for(int end = start; end<n; end++){
                int currentSum = 0;
                for(int i=start; i<=end; i++){
                    currentSum += nums[i];
                }
                maxSum = Math.max(maxSum, currentSum);
            }
        }
        return maxSum;
    }
}


// O(n^2) solution time limit exceeded
class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int n = nums.length;

        for (int start = 0; start<n; start++){
        int currentSum = 0;
            for(int end = start; end<n; end++){
                currentSum += nums[end];
                maxSum = Math.max(maxSum, currentSum);
            }
        }
        return maxSum;
    }
}


//O(n)
class Solution {
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int max = nums[0];
        int n = nums.length;
        for(int i=0; i<n; i++){
            sum += nums[i];
            if(sum>max){
                max = sum;
            }
            if(sum<0){
                sum = 0;
            }
        }
        return max;
    }
}

//------------------------------------------------------------------------------------

//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 7
// Reshape the Matrix
// In MATLAB, there is a handy function called reshape which can reshape an m x n matrix into a new one with a different size r x c keeping its original data.
// You are given an m x n matrix mat and two integers r and c representing the number of rows and the number of columns of the wanted reshaped matrix.
// The reshaped matrix should be filled with all the elements of the original matrix in the same row-traversing order as they were.
// If the reshape operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.

// Example 1:
// Input: mat = [[1,2],[3,4]], r = 1, c = 4
// Output: [[1,2,3,4]]

// Example 2:
// Input: mat = [[1,2],[3,4]], r = 2, c = 4
// Output: [[1,2],[3,4]]

class Solution {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length;
        int n = mat[0].length;

        // Check if reshape operation is possible
        if (m * n != r * c) {
            return mat; // Return the original matrix
        }

        int[][] reshapedMat = new int[r][c];
        int rowIndex = 0, colIndex = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                reshapedMat[rowIndex][colIndex] = mat[i][j];
                colIndex++;
                // Move to the next row when reaching the column limit
                if (colIndex == c) {
                    colIndex = 0;
                    rowIndex++;
                }
            }
        }

        return reshapedMat;
    }
}

//------------------------------------------------------------------------------------

//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 8
// Determine Whether Matrix Can Be Obtained By Rotation
// Given two n x n binary matrices mat and target, return true if it is possible to make mat equal to target by rotating mat in 90-degree increments, or false otherwise.

// Example 1:
// Input: mat = [[0,1],[1,0]], target = [[1,0],[0,1]]
// Output: true
// Explanation: We can rotate mat 90 degrees clockwise to make mat equal target.

// Example 2:
// Input: mat = [[0,1],[1,1]], target = [[1,0],[0,1]]
// Output: false
// Explanation: It is impossible to make mat equal to target by rotating mat.

// Example 3:
// Input: mat = [[0,0,0],[0,1,0],[1,1,1]], target = [[1,1,1],[0,1,0],[0,0,0]]
// Output: true
// Explanation: We can rotate mat 90 degrees clockwise two times to make mat equal target.


class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;

        for(int rotation=0; rotation<4; rotation++){
            if(areMatrixEqual(mat, target)){
                return true;
            }
            rotate90Clockwise(mat);
        }
        return false;
    }

    boolean areMatrixEqual(int[][] mat, int[][]target){
        int n = mat.length;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(mat[i][j] != target[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    void rotate90Clockwise(int[][] mat){
        int n = mat.length;

        //Transpose the matrix
        for(int i=0; i<n; i++){
            for(int j=i; j<n; j++){
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n/2; j++){
                int temp = mat[i][j];
                mat[i][j] = mat[i][n-1-j];
                mat[i][n-1-j] = temp;
            }
        }
    }
}



//------------------------------------------------------------------------------------

//𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧 9
// Count Items Matching a Rule
// You are given an array items, where each items[i] = [typei, colori, namei] describes the type, color, and name of the ith item. You are also given a rule represented by two strings, ruleKey and ruleValue.
// The ith item is said to match the rule if one of the following is true:
// ruleKey == "type" and ruleValue == typei.
// ruleKey == "color" and ruleValue == colori.
// ruleKey == "name" and ruleValue == namei.
// Return the number of items that match the given rule.

// Example 1:
// Input: items = [["phone","blue","pixel"],["computer","silver","lenovo"],["phone","gold","iphone"]], ruleKey = "color", ruleValue = "silver"
// Output: 1
// Explanation: There is only one item matching the given rule, which is ["computer","silver","lenovo"].

// Example 2:
// Input: items = [["phone","blue","pixel"],["computer","silver","phone"],["phone","gold","iphone"]], ruleKey = "type", ruleValue = "phone"
// Output: 2
// Explanation: There are only two items matching the given rule, which are ["phone","blue","pixel"] and ["phone","gold","iphone"]. Note that the item ["computer","silver","phone"] does not match.
 
class Solution {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int count = 0;

        for (List<String> item : items) {
            // Determine which rule to check based on ruleKey
            if ("type".equals(ruleKey) && item.get(0).equals(ruleValue)) {
                count++;
            } else if ("color".equals(ruleKey) && item.get(1).equals(ruleValue)) {
                count++;
            } else if ("name".equals(ruleKey) && item.get(2).equals(ruleValue)) {
                count++;
            }
        }

        return count;
    }
}
