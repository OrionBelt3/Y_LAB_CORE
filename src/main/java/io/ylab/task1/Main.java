package io.ylab.task1;

import java.util.Scanner;

//user experience class
public class Main {
    public static void main(String[] args) {
        int sizeMatrix;
        MyMatrix myMatrix;

        System.out.print("Задайте размерность матрицы и нажмите ENTER: ");
        Scanner console = new Scanner(System.in);
        try {
            sizeMatrix = console.nextInt();
            myMatrix = new MyMatrix(sizeMatrix);
        } catch (Exception e) {
            System.out.println("Будет задана минимальная установленная размерность матрицы!");
            myMatrix = new MyMatrix();
        }

        myMatrix.show();
        System.out.println("MAX: " + myMatrix.findMax());
        System.out.println("MIN: " + myMatrix.findMin());
        System.out.println("AVG: " + myMatrix.findAvg());
        myMatrix.paint();
    }
}


//matrix class
class MyMatrix {
    final int sizeMatrix;
    final Long[][] matrix;

    public MyMatrix() {
        this.sizeMatrix = 4;
        matrix = new Long[this.sizeMatrix][this.sizeMatrix];
        create();
    }

    public MyMatrix(int sizeMatrix) {
        this.sizeMatrix = sizeMatrix;
        matrix = new Long[this.sizeMatrix][this.sizeMatrix];
        create();
    }

    private void create() {
        MyRandom myRandom = new MyRandom();
        for (int i = 0; i < this.sizeMatrix; ++i) {
            for (int j = 0; j < this.sizeMatrix; ++j) {
                this.matrix[i][j] = myRandom.random(10L * this.sizeMatrix);
            }
        }
    }

    public void show() {
        for (int i = 0; i < sizeMatrix; ++i) {
            for (int j = 0; j < sizeMatrix; ++j) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public long findMin() {
        long min = matrix[0][0];
        for (int i = 0; i < sizeMatrix; ++i) {
            for (int j = 0; j < sizeMatrix; ++j) {
                if (min >= matrix[i][j]) {
                    min = matrix[i][j];
                }
            }
        }
        return min;
    }

    public long findMax() {
        long max = matrix[0][0];
        for (int i = 0; i < sizeMatrix; ++i) {
            for (int j = 0; j < sizeMatrix; ++j) {
                if (max <= matrix[i][j]) {
                    max = matrix[i][j];
                }
            }
        }

        return max;
    }

    public long findAvg() {
        long min = findMin();
        long max = findMax();
        double half = (double) (max - min) / 2;
        long avg = matrix[0][0];
        double diff = Math.abs(half - matrix[0][0]);
        for (int i = 0; i < sizeMatrix; ++i) {
            for (int j = 0; j < sizeMatrix; ++j) {
                double currentDiff = Math.abs(half - matrix[i][j]);
                if (diff > currentDiff) {
                    diff = currentDiff;
                    avg = matrix[i][j];
                }
            }
        }
        return avg;
    }

    public void paint() {
        long min = findMin();
        long max = findMax();
        long avg = findAvg();

        for (int i = 0; i < sizeMatrix; ++i) {
            for (int j = 0; j < sizeMatrix; ++j) {
                if (matrix[i][j] == min || matrix[i][j] == max || matrix[i][j] == avg) {
                    System.out.print("{{" + matrix[i][j] + "}}" + "\t");
                } else {
                    System.out.print(matrix[i][j] + "\t\t");
                }

            }
            System.out.println();
        }
    }
}

//Linear congruent random number generator
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

