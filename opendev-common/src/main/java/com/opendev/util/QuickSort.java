package com.opendev.util;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {

    public static void main(String[] args){
        int[] arr = {10,70,28,41,7,62,3,4,2,1,8,9,19};
        /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        int[] array  = new int[80000000];
        for (int i = 0; i < array.length; i++){
            array[i] = (int) (Math.random()*800000);
        }
        System.out.println("排序前时间为："+simpleDateFormat.format(new Date()));
        System.out.println(Arrays.toString(array));

        quickSort(array, 0, array.length-1);

        System.out.println("排序后时间为："+simpleDateFormat.format(new Date()));
        System.out.println(Arrays.toString(array));*/
        quickSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
        System.out.println(binarySearch(arr, 0, arr.length - 1, 28));
    }

    public static void quickSort(int[] array, int left, int right){
        int i, j, medium, temp;
        if (left > right){
            return;
        }

        i = left;
        j = right;
        medium = array[left];

        while ( i < j){

            while (array[j] >= medium && i < j){
                j--;
            }

            while (array[i] <= medium && i < j){
                i++;
            }

            if(i < j){
                temp = array[j];
                array[j] = array[i];
                array[i] = temp;
            }
        }
        array[left] = array[i];
        array[i] = medium;

        quickSort(array, left, j-1);
        quickSort(array, j+1, right);
    }

    public static int binarySearch(int[] arr, int left, int right, int findVal){
        int mid = (left + right) / 2;
        if (findVal > arr[mid]){
            return binarySearch(arr, mid + 1, right, findVal);
        }else if (findVal < arr[mid]){
            return binarySearch(arr, left, mid - 1, findVal);
        }else{
            return mid;
        }
    }
}
