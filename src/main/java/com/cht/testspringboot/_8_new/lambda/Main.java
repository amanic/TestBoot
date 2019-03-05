package com.cht.testspringboot._8_new.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * 基本语法:
 * (parameters) -> expression
 * 或
 * (parameters) ->{ statements; }
 *
 * @auther chen.haitao
 * @date 2019-03-05
 */
public class Main {

    public static void main(String[] args) {

/**
 // 1. 不需要参数,返回值为 5
 () -> 5

 // 2. 接收一个参数(数字类型),返回其2倍的值
 x -> 2 * x

 // 3. 接受2个参数(数字),并返回他们的差值
 (x, y) -> x – y

 // 4. 接收2个int型整数,返回他们的和
 (int x, int y) -> x + y

 // 5. 接受一个 string 对象,并在控制台打印,不返回任何值(看起来像是返回void)
 (String s) -> System.out.print(s)
 */

        String[] atp = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        List<String> players = Arrays.asList(atp);

// 以前的循环方式
        for (String player : players) {
            System.out.print(player + "; ");
        }

// 使用 lambda 表达式以及函数操作(functional operation)
        players.forEach(player -> {
            System.out.print(player + "; ");
        });

// 在 Java 8 中使用双冒号操作符(double colon operator)
        players.forEach(System.out::print);


        // 1.1使用匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world !");
            }
        }).start();


        // 1.2使用 lambda expression
        new Thread(() -> System.out.println("Hello world !")).start();

        // 2.1不使用匿名内部类
        Runnable race1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world !");
            }
        };

        // 2.2使用 lambda expression
        Runnable race2 = () -> System.out.println("Hello world !");


        String[] people = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka", "David Ferrer",
                "Roger Federer", "Andy Murray",
                "Tomas Berdych", "Juan Martin Del Potro",
                "Richard Gasquet", "John Isner"};

        Integer[] animal = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};

// 1.1 使用匿名内部类根据 name 排序 players
//        Arrays.sort(people, new Comparator<String>() {
//            @Override
//            public int compare(String s1, String s2) {
//                return (s1.compareTo(s2));
//            }
//        });

        Arrays.asList(people).forEach((peo) -> System.out.print(peo+"-"));
        System.out.println();

        Arrays.sort(people, (s1, s2) -> s1.compareTo(s2));

        List<String> peopleList = Arrays.asList(people);

        peopleList.forEach((peo) -> System.out.print(peo+"-"));


    }
}
