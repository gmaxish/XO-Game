package com.MHryhorovych.XOGame;

import java.util.Random;
import java.util.Scanner;

/**
 * @autor Maksym Hryhorovych
 */

public class MainXO {

    static Scanner playersChoise = new Scanner(System.in);
    static int Size_X = choice();
    static int Size_Y = Size_X;
    static char Player = 'X';
    static char Player1 = '0';
    static char Computer = '0';
    static char Empty = '-';
    static char[][] field = new char[Size_Y][Size_X];
    static int forWin = 3;
    static Random random = new Random();

    public static void main(String[] args) {

        initField();
        fillField();
        while (true) {
            movePlayer();
            fillField();
            if (checkWinner(Player)) {
                System.out.println("Победа первого игрока");
                break;
            }
            if (isItFreeSpace()) {
                System.out.println("Ничья");
                break;
            }
//            movePlayer1();
//            System.out.println();
//            fillField();
//            if (checkWinner(Player1)) {
//                System.out.println("Победа второго игрока");
//                break;
//            }
            moveComputer();
            fillField();
            if (checkWinner(Computer)) {
                System.out.println("Победа компьютера");
                break;
            }
            if (isItFreeSpace()) {
                System.out.println("Ничья");
                break;
            }
        }
    }


    static void initField() {
        for (int i = 0; i < Size_X; i++) {
            for (int k = 0; k < Size_Y; k++) {
                field[i][k] = Empty;
            }
        }
    }

    static void fillField() {
        for (int i = 0; i < Size_X; i++) {
            for (int k = 0; k < Size_Y; k++) {
                System.out.print(field[i][k] + "\t");

            }
            System.out.println();
        }
    }

    static void Symbol(int x, int y, char Symbol) {
        field[y][x] = Symbol;
    }

    static void movePlayer() {
        int x, y;
        do {
            System.out.println("Введите координаты Игрок 1: (стоблец, строка)");
            x = playersChoise.nextInt() - 1;
            y = playersChoise.nextInt() - 1;
        } while (!isItFree(x, y));
        field[y][x] = Player;
    }

    static void movePlayer1() {
        int x, y;
        do {
            System.out.println("Введите координаты: ");
            x = playersChoise.nextInt();
            y = playersChoise.nextInt();
        } while (!isItFree(x, y));
        field[y][x] = Player1;
    }

    static void moveComputer() {
        int x, y;
            do {
                System.out.println("Введите координаты: ");
                x = random.nextInt(Size_X);
                y = random.nextInt(Size_Y);
                System.out.println("X: " + x + " Y: " + y );
            }while (!isItFree(x, y)) ;

                field[y][x] = Computer;
                System.out.println("Computer select x: " + x + " y: " + y);
            }


        static boolean isItFree ( int x, int y){
            if (x < 0 || x >= Size_X || y < 0 || y >= Size_Y) return false;
            if (field[y][x] == Empty) return true;
            if (field[y][x] == Player) return false;
            if (field[y][x] == Computer) return false;
            return false;
        }

        static boolean isItFreeSpace () {
            for (int i = 0; i < Size_Y; i++) {
                for (int k = 0; k < Size_X; k++) {
                    if (field[i][k] == Empty) return false;
                }
            }
            return true;
        }

        static int choice(){
            System.out.println("Выберите размер игрового поля: ");
            int x = playersChoise.nextInt();
            Size_X = x;
            Size_Y = x;
            return Size_X;
        }

        static boolean checkWinner (char Symbol){
        //for rows
            for (int i = 0; i < Size_Y; i++) {
                int result = 0;
                for (int k = 0; k < Size_X; k++) {
                    if (field[i][k] == Symbol) result++;
                }
                if (result == forWin) return true;
            }
        //for columns
            for (int i = 0; i < Size_Y; i++) {
                int result = 0;
                for (int k = 0; k < Size_X; k++) {
                    if (field[k][i] == Symbol) result++;
                }
                if (result == forWin) return true;
            }

            int leftDiagonal = 0;
            for (int i = 0; i < Size_Y; i++) {
                for (int k = 0; k < Size_X; k++) {
                    if (k == i && field[i][k] == Symbol) leftDiagonal++;
                }
            }
            if (leftDiagonal == forWin) return true;

            int rightDiagonal = 0;
            for (int i = 0, k = Size_Y - 1; i < Size_X && k >= 0; i++, k--) {
                if (field[i][k] == Symbol) rightDiagonal++;
            }
            if (rightDiagonal == forWin) return true;
            return false;
        }
    }

