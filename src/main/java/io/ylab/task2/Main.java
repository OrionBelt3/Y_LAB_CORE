package io.ylab.task2;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Sort sort = new Sort();
        Scanner console = new Scanner(System.in);
        MyRandom myRandom = new MyRandom();

        int size;

        long[] array = {5,6,3,2,5,1,4,9};
        System.out.print("IN: ");
        show(array);
        array = sort.radixSort(array, 8);
        System.out.print("OUT: ");
        show(array);


        long[] arrayForTest;
        System.out.print("Enter the size of array: ");
        try {
            size = console.nextInt();
            arrayForTest = new long[size];
        } catch (Exception e) {
            System.out.println("Size: 10");
            size = 10;
            arrayForTest = new long[10];
        }

        for (int i = 0; i < size; ++i) {
            arrayForTest[i] = myRandom.random(10L * size);
        }
        show(arrayForTest);

        if (size > 10000) {
            System.out.println("WARNING! It is recommended to use Radix Sort!\n");
        }

        System.out.print("""
                Select the desired sorting:
                1) Bubble Sort (O(n^2))
                2) Selection Sort (O(n^2))
                3) Insert Sort (O(n^2)))
                4) Radix Sort (O(n + k))
                0) Exit
                """);

        boolean flag = true;
        String answer;
        while (flag) {
            answer = console.next();
            flag = false;
            switch (answer) {
                case "1" -> {
                    sort.bubbleSort(arrayForTest);
                    show(arrayForTest);
                }
                case "2" -> {
                    sort.selectionSort(arrayForTest);
                    show(arrayForTest);
                }
                case "3" -> {
                    sort.insertSort(arrayForTest);
                    show(arrayForTest);
                }
                case "4" -> {
                    sort.radixSort(arrayForTest, size);
                    show(arrayForTest);
                }
                case "0" -> System.out.println("Goodbye!");
                default -> {
                    flag = true;
                    System.out.println("Try again!");
                }
            }
        }
    }

    public static void show(long[] array){
        for (long l : array) {
            System.out.print(l + " ");
        }
        System.out.println();
    }

}

class Sort {
    public long[] bubbleSort(long[] array) {
        boolean continueSort = true;
        while (continueSort) {
            continueSort = false;
            for (int i = 1; i < array.length; ++i) {
                if (array[i] < array[i - 1]) {
                    swap(array, i, i - 1);
                    continueSort = true;
                }
            }
        }
        return array;
    }

    public long[] selectionSort(long[] array) {
        for (int first = 0; first < array.length; ++first) {
            int nextMin = first;
            for (int i = first; i < array.length; ++i) {
                if (array[i] < array[nextMin]) {
                    nextMin = i;
                }
            }
            swap(array, first, nextMin);
        }
        return array;
    }

    public long[] insertSort(long[] array) {
        for (int left = 0; left < array.length; left++) {
            long value = array[left];
            int i = left - 1;
            for (; i >= 0; i--) {
                if (value < array[i]) {
                    array[i + 1] = array[i];
                } else {
                    break;
                }
            }
            array[i + 1] = value;
        }
        return array;
    }

    private void swap(long[] array, int first, int second) {
        long tmp = array[first];
        array[first] = array[second];
        array[second] = tmp;
    }

    public long[] radixSort(long[] array, int size) {
        long max = getMax(array, size);
        for (int exp = 1; max / exp > 0; exp *= 10)
            countSort(array, size, exp);
        return array;
    }

    private long getMax(long[] array, int size) {
        long max = array[0];
        for (int i = 0; i < size; ++i) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        return max;
    }

    private long[] countSort(long[] array, int size, int exp) {
        long[] output = new long[size];
        int[] count = new int[10];
        Arrays.fill(count, 0);

        for (int i = 0; i < size; ++i) {
            count[(int) ((array[i] / exp) % 10)]++;
        }

        for (int i = 1; i < 10; ++i) {
            count[i] +=count[i - 1];
        }

        for (int i = size - 1; i >= 0; i--) {
            output[count[(int) ((array[i] / exp) % 10)] - 1] = array[i];
            count[(int) ((array[i] / exp) % 10)]--;
        }

        System.arraycopy(output, 0, array, 0, size);
        return array;
    }

}

class MyRandom {

    private long randomNumber;

    public MyRandom() {
        this.randomNumber = System.currentTimeMillis();
    }


    private long calculationRandomNumber() {
        this.randomNumber = (1664525 * randomNumber + 1013904223) % (long) Math.pow(2, 32);
        return this.randomNumber;
    }

    public long random(long sup) {
        return this.calculationRandomNumber() % sup;
    }
}
