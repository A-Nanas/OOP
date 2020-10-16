package com.oop.second;
import java.io.*;

public class SubString {

    public static void moveToBegin(int[][] array, int amount, int beg){ //сдвигает все символы в буфере в начало
        for(int i = 0; i < amount; i++){
            array[i][0] = array[i + beg][0];
            array[i][1] = array[i + beg][1];
            array[i][2] = array[i + beg][2];
        }
    }
    public static void createFile(String s){
        File myObj = new File(s);
        try {
            myObj.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void find(String s, int[] a, String sub){
        int length = sub.length();
        int beg = 0;
        int amount = 0;
        int index = 0;
        int count = 0;
        //буфер
        int[][] array = new int[length * 4 + 10][3];
        //считываем первый символ
        BufferedReader reader = null;
        int symbol = 0;
        try {
            reader = new BufferedReader(new FileReader(s));
            symbol = reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (symbol != -1) {//пок не дошли до конца файла
            if(amount == 0) {//считываем, пока не найдем первое совпадение
                while ((char) symbol != sub.charAt(0) && symbol != -1) {
                    try {
                        symbol = reader.read();// Читаем символ
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    index++;
                }
                if (symbol == -1) {
                    break;
                }
            }
            for(int i = amount; i < sub.length() && symbol != -1; i++) {
                //записываем символы в массив и сразу вычияляем z - функцию
                //пока не считаем length символов или не дойдем до конца строки
                array[i + beg][0] = 0;
                array[i + beg][1] = symbol;
                array[i + beg][2] = index;
                for(int j = 0; j <= i; j++){//вычисление z - функции
                    if((char)array[i + beg][1] == sub.charAt(array[j + beg][0])){
                        array[j + beg][0]++;
                    }
                }
                amount++;
                try {
                    symbol = reader.read();// Читаем символ
                } catch (IOException e) {
                    e.printStackTrace();
                }
                index++;
            }
            if(amount < sub.length()){
                break;
            }
            int j = 0;
            if(array[beg][0] == sub.length()){//если с первого символа в буфере начинается вхождение подстроки
                a[count] = array[beg][2];//сохраняем ого индекс
                count++;

                array[beg][0] = 0;//очищаем поле буфера
                array[beg][1] = 0;
                array[beg][2] = 0;

                beg++;
                amount--;
                j++;
            }
            while(array[beg][0] < sub.length() - j) {
                array[beg][0] = 0;
                array[beg][1] = 0;
                array[beg][2] = 0;
                j++;
                amount--;
                beg++;
            }
            //если буфер заполнен на половину, сдвигаем данные в нём в начало
            if (beg >= sub.length() * 2){
                moveToBegin(array, amount, beg);
                beg = 0;
            }
            //if(amount < 0){amount = 0;}
            //System.out.println((char)symbol);
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
