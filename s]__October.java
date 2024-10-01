Q1: https://leetcode.com/problems/removing-stars-from-a-string/description/

// class Solution {
//     public String removeStars(String s) {

//         Stack<Character> stack = new Stack<>();

//         for(char c : s.toCharArray()){
//             if(c == '*'){
//                 if(!stack.isEmpty()){
//                     stack.pop();
//                 }
//             }else{
//                 stack.push(c);
//             }
//         }

//         StringBuilder result = new StringBuilder();
//         while(!stack.isEmpty()){
//             result.append(stack.pop());
//         }

//         return result.reverse().toString();
//     }
// }

// Step-by-Step Example:
// Given: s = "leet**cod*e"

// Initial Stack State: Empty

// Process each character:
// Push 'l' → Stack: ['l']
// Push 'e' → Stack: ['l', 'e']
// Push 'e' → Stack: ['l', 'e', 'e']
// Push 't' → Stack: ['l', 'e', 'e', 't']
// Encounter '*' → Pop 't' → Stack: ['l', 'e', 'e']
// Encounter '*' → Pop 'e' → Stack: ['l', 'e']
// Push 'c' → Stack: ['l', 'e', 'c']
// Push 'o' → Stack: ['l', 'e', 'c', 'o']
// Push 'd' → Stack: ['l', 'e', 'c', 'o', 'd']
// Encounter '*' → Pop 'd' → Stack: ['l', 'e', 'c', 'o']
// Push 'e' → Stack: ['l', 'e', 'c', 'o', 'e']

// Final Stack State: ['l', 'e', 'c', 'o', 'e']


class Solution {
    public String removeStars(String s) {

        StringBuilder result = new StringBuilder();

        for(char c : s.toCharArray()){
            if(c == '*'){
                result.deleteCharAt(result.length() - 1);
            }else{
                result.append(c);
            }
        }

        return result.toString();
    }
}

// Detailed Dry Run:
// Let’s dry run the optimized solution with s = "leet**cod*e":

// Initialization:
// StringBuilder result = new StringBuilder();
// Start iterating through the characters of s.

// Processing Characters:
// 'l' → Append → result = "l"
// 'e' → Append → result = "le"
// 'e' → Append → result = "lee"
// 't' → Append → result = "leet"
// '*' → Remove last character ('t') → result = "lee"
// '*' → Remove last character ('e') → result = "le"
// 'c' → Append → result = "lec"
// 'o' → Append → result = "leco"
// 'd' → Append → result = "lecod"
// '*' → Remove last character ('d') → result = "leco"
// 'e' → Append → result = "lecoe"
// Final Result: Convert result to a string → "lecoe"