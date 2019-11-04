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

        for(int i = 0; i < rows; ++i){
            for (int j = 0; j < cols; ++j){
                arr[i][j] = ThreadLocalRandom.current().nextInt(10);
            }
        }

        for(int i = 0; i < rows; ++i) {
            Arrays.sort(arr[i], Collections.reverseOrder());
        }

        for(int i = 0; i < rows; ++i){
            for (int j = 0; j < cols; ++j){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }


    }
}