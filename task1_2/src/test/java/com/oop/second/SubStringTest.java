package com.oop.second;

import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

class SubStringTest {

    SubString test = new SubString();
    Files creation = new Files();

    @Test
    void emptyTest() {
        String s = "first_test.txt";
        creation.createFile(s);
        int[] arr = {};
        int[] a = {};
        String sub = "3";

        test.find(s, a, sub);
        assertArrayEquals(arr, a);
    }

    @Test
    void littleTest() {
        String s = "second_test.txt";
        creation.createFile(s);
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("second_test.txt");
            myWriter.write("abcde");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] arr = {};
        int[] a = {};
        String sub = "3";

        test.find(s, a, sub);
        assertArrayEquals(arr, a);
    }

    @Test
    void littleEasyTest() {
        String s = "third_test.txt";
        creation.createFile(s);
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("third_test.txt");
            myWriter.write("abcde");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] arr = new int[1000];
        arr[0] = 0;
        int[] a = new int[1000];
        String sub = "a";

        test.find(s, a, sub);
        assertArrayEquals(arr, a);
    }

    @Test
    void littleDoubleTest() {
        String s = "forth_test.txt";
        creation.createFile(s);
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("forth_test.txt");
            myWriter.write("abcdea");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] arr = new int[1000];
        arr[0] = 0;
        arr[1] = 5;
        int[] a = new int[1000];
        String sub = "a";

        test.find(s, a, sub);
        assertArrayEquals(arr, a);
    }

    @Test
    void littleNormalTest() {
        String s = "fifth_test.txt";
        creation.createFile(s);
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("fifth_test.txt");
            myWriter.write("abcdeabc");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] arr = new int[1000];
        arr[0] = 0;
        arr[1] = 5;
        int[] a = new int[1000];
        String sub = "abc";

        test.find(s, a, sub);
        assertArrayEquals(arr, a);
    }

    @Test
    void littleCrossTest() {
        String s = "sixth_test.txt";
        creation.createFile(s);
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("sixth_test.txt");
            myWriter.write("ababaabc");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] arr = new int[1000];
        arr[0] = 0;
        arr[1] = 2;
        int[] a = new int[1000];
        String sub = "aba";

        test.find(s, a, sub);
        assertArrayEquals(arr, a);
    }

    @Test
    void crossTest() {
        String s = "seventh_test.txt";
        creation.createFile(s);
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("seventh_test.txt");
            myWriter.write("nabcabcabchcbajdklfababcabc");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] arr = new int[1000];
        arr[0] = 1;
        arr[1] = 4;
        arr[2] = 21;
        int[] a = new int[1000];
        String sub = "abcab";

        test.find(s, a, sub);
        assertArrayEquals(arr, a);
    }

    @Test
    void bigTest() {
        String s = "eighth_test.txt";
        creation.createFile(s);
        FileWriter myWriter = null;
        int[] arr = new int[1000];
        try {
            myWriter = new FileWriter("eighth_test.txt");
            for (int i = 0; i < 300; i++){
                myWriter.write("nabcabcabc");
                arr[i] = i * 10;
            }
            for (int i = 0; i < 160000; i++) {
                myWriter.write("mabcabcabchcbajdklfababcabc jdklfababcabc mabaabcabchcbajdklfababcabc jdklf");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] a = new int[1000];
        String sub = "nabca";

        test.find(s, a, sub);
        assertArrayEquals(arr, a);
    }
}