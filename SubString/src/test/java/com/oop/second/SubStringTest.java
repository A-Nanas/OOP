package com.oop.second;

import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

class SubStringTest {

    @Test
    void emptyTest() {
        String s = "first_test.txt";
        /*FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("filename.txt");
            myWriter.write("");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        SubString.createFile(s);
        int[] arr = {};
        int[] a = {};
        String sub = "3";

        SubString.find(s, a, sub);
        assertArrayEquals(arr, a);
    }

    @Test
    void littleTest() {
        String s = "second_test.txt";
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("second_test.txt");
            myWriter.write("abcde");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SubString.createFile(s);
        int[] arr = {};
        int[] a = {};
        String sub = "3";

        SubString.find(s, a, sub);
        assertArrayEquals(arr, a);
    }

    @Test
    void littleEasyTest() {
        String s = "third_test.txt";
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("third_test.txt");
            myWriter.write("abcde");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SubString.createFile(s);
        int[] arr = new int[1000];
        arr[0] = 0;
        int[] a = new int[1000];
        String sub = "a";

        SubString.find(s, a, sub);
        //System.out.println(a[0]);
        assertArrayEquals(arr, a);
    }

    @Test
    void littleDoubleTest() {
        String s = "forth_test.txt";
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("forth_test.txt");
            myWriter.write("abcdea");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SubString.createFile(s);
        int[] arr = new int[1000];
        arr[0] = 0;
        arr[1] = 5;
        int[] a = new int[1000];
        String sub = "a";

        SubString.find(s, a, sub);
        //System.out.println(a[0]);
        assertArrayEquals(arr, a);
    }

    @Test
    void littleNormalTest() {
        String s = "fifth_test.txt";
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("fifth_test.txt");
            myWriter.write("abcdeabc");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SubString.createFile(s);
        int[] arr = new int[1000];
        arr[0] = 0;
        arr[1] = 5;
        int[] a = new int[1000];
        String sub = "abc";

        SubString.find(s, a, sub);
        //System.out.println(a[0]);
        assertArrayEquals(arr, a);
    }

    @Test
    void littleCrossTest() {
        String s = "sixth_test.txt";
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("sixth_test.txt");
            myWriter.write("ababaabc");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SubString.createFile(s);
        int[] arr = new int[1000];
        arr[0] = 0;
        arr[1] = 2;
        int[] a = new int[1000];
        String sub = "aba";

        SubString.find(s, a, sub);
        //System.out.println(a[0]);
        assertArrayEquals(arr, a);
    }

    @Test
    void crossTest() {
        String s = "seventh_test.txt";
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("seventh_test.txt");
            myWriter.write("nabcabcabchcbajdklfababcabc");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SubString.createFile(s);
        int[] arr = new int[1000];
        arr[0] = 1;
        arr[1] = 4;
        arr[2] = 21;
        int[] a = new int[1000];
        String sub = "abcab";

        SubString.find(s, a, sub);
        //System.out.println(a[0]);
        assertArrayEquals(arr, a);
    }

    @Test
    void bigTest() {
        String s = "seventh_test.txt";
        FileWriter myWriter = null;
        int[] arr = new int[1000];
        try {
            myWriter = new FileWriter("seventh_test.txt");
            for (int i = 0; i < 300; i++){
                myWriter.write("nabcabcabc");
                arr[i] = i * 10;
            }
            for (int i = 0; i < 160000000; i++) {
                myWriter.write("mabcabcabchcbajdklfababcabc jdklfababcabc mabaabcabchcbajdklfababcabc jdklf");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SubString.createFile(s);
        int[] a = new int[1000];
        String sub = "nabca";

        SubString.find(s, a, sub);
        //System.out.println(a[0]);
        assertArrayEquals(arr, a);
    }
}