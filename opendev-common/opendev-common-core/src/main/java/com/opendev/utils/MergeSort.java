package com.opendev.utils;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args){

        int[] array = {7,9,5,2,8,4,3,1,0,6};
        int[] temp = new int[array.length];
        System.out.println("归并前："+ Arrays.toString(array));
        mergeSort(array, 0, array.length - 1, temp);
        System.out.println("归并后："+ Arrays.toString(array));
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        if (left < right){
            int mid = (left + right) / 2;
            mergeSort(arr, left , mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }

    }

    public static void merge(int[] arr, int left, int mid, int right, int[] temp){
        int i = left;
        int j = mid + 1;
        int t = 0;

        while (i <= mid && j <= right){
            if (arr[i] <= arr[j]){
                temp[t] = arr[i];
                i += 1;
                t += 1;
            }else{
                temp[t] = arr[j];
                j += 1;
                t += 1;
            }
        }

        while (i <= mid){
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }

        while (j <= right){
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        t = 0;
        int tempLeft = left;
        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }
}
