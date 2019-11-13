package com.github.alloky.task_2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class Main {
    public static void main(String[] args) {
        System.out.println("Hello");

        int rows = 6;
        int cols = 7;
        Integer[][] arr = new Integer[rows][cols];

        fill2dRandom(arr);
        sort2dByRows(arr);
        print2dArray(rows, cols, arr);
    }

    private static void print2dArray(int rows, int cols, Integer[][] arr) {
        for(int i = 0; i < rows; ++i){
            for (int j = 0; j < cols; ++j){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void sort2dByRows(Integer[][] arr) {
        int rows = arr.length;
        for(int i = 0; i < rows; ++i) {
            Arrays.sort(arr[i], Collections.reverseOrder());
        }
    }

    private static void fill2dRandom(Integer[][] arr) {
        int rows = arr.length;
        int cols = arr[0].length;

        for(int i = 0; i < rows; ++i){
            for (int j = 0; j < cols; ++j){
                arr[i][j] = ThreadLocalRandom.current().nextInt(10);
            }
        }
    }
}