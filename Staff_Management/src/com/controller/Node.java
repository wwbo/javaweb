package com.controller;

public class Node {
    //冒泡排序
    public void mpSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++)
            for (int j = 0; j < arr.length - i - 1; j++){
                if (arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
    }

    //直接插入排序
    public void zjcrSort(int[] arr){
        for (int i = 1; i < arr.length; i++){
            int temp = arr[i];
            int j;
            for (j = i-1; j >=0; j--){
                if (arr[j] > temp){
                    arr[j+1] = arr[j];
                }else {
                    break;
                }
            }
            arr[j+1] = temp;
        }
    }

    //快速排序
    public int getMiddle(int[] arr, int low, int high){
        int temp = arr[low];
        while(low < high){
            if (low < high && arr[high] > temp){
                high--;
            }
            arr[low] = arr[high];
            if (low < high && arr[low] < temp){
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = temp;
        return low;
    }

    public void quickSort(int[] arr, int low, int high){
        if (low < high){
            int middle = getMiddle(arr, low,high);
            quickSort(arr, 0, middle-1);
            quickSort(arr, middle+1, high);
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 0, 5, 7, 9, 6, 4, 8, 3};
        new Node().quickSort(arr, 0, arr.length-1);
        for (int a : arr) {
            System.out.print(a + " ");
        }
    }
}
