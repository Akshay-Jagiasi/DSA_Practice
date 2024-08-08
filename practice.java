public static int findMax(int[] arr){
    int max = 0;
    for (int i=0; i<arr.length; i++){
        if(arr[i] > max){
            max = arr[i];
        }
    }
    return max;
}

public static void reverse(int[] arr){
    int i = arr[0];
    int j = arr[arr.length-1];

    while(i<j){
        int temp = arr[i];
        int temp2 = arr[j];
        arr[i] = temp2;
        arr[j] = temp;
        i++;
        j--;
    }
}

public static int sumOfMatrix(int[][] arr){
    for(int i=0; i<arr.length; i++){
        for(int j=0; j<arr[0].length; j++){
            arr[i][j] = arr[i][j] +arr[i][j];
        }
    }
}