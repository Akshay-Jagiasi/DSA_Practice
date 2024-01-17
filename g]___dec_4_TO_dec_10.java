//Recursion Practice
//Basic recursion practice question ans 
    static void recursive(int n){
        if(n==0){
            return;
        }
        System.out.println(n);
        recursive(n-1);
    }

    static long factorial(int n){
        if(n<=1){
            return 1;
        }
        return n * factorial(n-1);
    }

    static int sumOfDigits(int n){
         if(n==0){
            return 0;
        }
        return (n%10) + sumOfDigits(n/10);
    }

    static int countZero(int n){
        return helper(n,0);
    }
    
    private static int helper(int n, int count){
        if(n==0){
            return count;
        }
        int rem = n%10;
        if(rem == 0){
            return helper(n/10, count+1);
        }
        return helper(n/10, count);
    }

    static boolean find(int[] arr,int target, int index){
        if(index == arr.length){
            return false;
        }
        return arr[index] == target || find(arr,target, index+1);
    }


//_______________________________________________________________________________________________________________
//mergeSort
import java.util.Arrays;

public class mergeSort {
    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(sort(arr)));
    }

    static int[] sort(int[] arr) {
        //Base condition
        if (arr.length == 1) {
            return arr;
        }

        //Divide array into 2 halves for each iteration 
        int mid = arr.length / 2;

        //copy that two halves in each of the array and then the recursive call go on till the basecondition
        int[] left = sort(Arrays.copyOfRange(arr, 0, mid));
        int[] right = sort(Arrays.copyOfRange(arr, mid, arr.length));

        //then at each iteration two halves get compared in this functiona and get sorted 
        return merge(left, right);
    }

    private static int[] merge(int[] first, int[] second) {
        int[] mix = new int[first.length + second.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < first.length && j < second.length) {
            if (first[i] < second[j]) {
                mix[k] = first[i];
                i++;
            } else {
                mix[k] = second[j];
                j++;
            }
            k++;
        }

        // it may be possible that one of the arrays is not complete
        // copy the remaining elements
        while (i < first.length) {
            mix[k] = first[i];
            i++;
            k++;
        }

        while (j < second.length) {
            mix[k] = second[j];
            j++;
            k++;
        }

        return mix;
    }
}



//_______________________________________________________________________________________________________________
//Inplace MergeSort
import java.util.*;

public class mergeSortInPlace {
    public static void main(String[] args) {
        int[] arr = {5,4,3,2,1};
        sort(arr, 0, arr.length);
        System.out.println(Arrays.toString(arr));
    }
    static void sort(int[] arr, int s, int e) {
        if (e - s == 1) {
            return;
        }

        int mid = (s + e) / 2;

        sort(arr, s, mid);
        sort(arr, mid, e);

        mergeInPlace(arr, s, mid, e);
    }

    private static void mergeInPlace(int[] arr, int s, int m, int e) {
        int[] mix = new int[e - s];

        int i = s;
        int j = m;
        int k = 0;

        while (i < m && j < e) {
            if (arr[i] < arr[j]) {
                mix[k] = arr[i];
                i++;
            } else {
                mix[k] = arr[j];
                j++;
            }
            k++;
        }

        // it may be possible that one of the arrays is not complete
        // copy the remaining elements
        while (i < m) {
            mix[k] = arr[i];
            i++;
            k++;
        }

        while (j < e) {
            mix[k] = arr[j];
            j++;
            k++;
        }

        for (int l = 0; l < mix.length; l++) {
            arr[s+l] = mix[l];
        }
    }
}




//_______________________________________________________________________________________________________________
//QuickSort
import java.util.*;

public class quicksort {
    public static void main(String[] args) {
        int[] arr = {5,4,3,2,1};
        sort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    static void sort(int[] nums, int low, int high){
        if(low >= high){
            return;
        }
        int start = low;
        int end = high;
        int mid = start+(end-start)/2;
        int pivot = nums[mid];

        while(start <= end){
            while(nums[start]<pivot){
                start++;
            }
            while(nums[end]>pivot){
                end--;
            }
            if(start <= end){
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }

        //now my pivot is at correct index, sort two halves now
        sort(nums, low, end);
        sort(nums, start, high);
    }
}



//_______________________________________________________________________________________________________________
//recursion string questions practice
public class recursion_2 {
    public static void main(String[] args) {
        String ans = "baccad";
        System.out.println(ans);
        skip("", ans);
        System.out.println(ans);
    }

    static void skip(String p, String up){
        if(up.isEmpty()){
            System.out.println(p);
            return;
        }
        char ch = up.charAt(0);

        if(ch == 'a'){
            skip(p, up.substring(1));
        }else{
            skip(p+ch, up.substring(1));
        }
    }

    static String skip2(String up){
        if(up.isEmpty()){
            return "";
        }
        char ch = up.charAt(0);

        if(ch == 'a'){
            return skip2(up.substring(1));
        }else{
            return ch + skip2(up.substring(1));
        }
        
    }
}




//_______________________________________________________________________________________________________________
//Recursion subset & subseq practice
import java.util.ArrayList;

public class recursion_sub_set_seq {
 public static void main(String[] args) {
    subseq("","abc");
 }  
 static void subseq(String p, String up){
    if(up.isEmpty()){
        System.out.println(p);
        return;
    }
    char ch = up.charAt(0);
    subseq(p, up.substring(1));
    subseq(p+ch, up.substring(1));
 } 
 static ArrayList<String> subseqRet(String p, String up) {
        if (up.isEmpty()) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        char ch = up.charAt(0);
        ArrayList<String> left = subseqRet(p + ch, up.substring(1));
        ArrayList<String> right = subseqRet(p, up.substring(1));

        left.addAll(right);
        return left;
    }
}

