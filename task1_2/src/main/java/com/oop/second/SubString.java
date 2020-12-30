package com.oop.second;
import java.io.*;

public class SubString {

    private BufferEntry[] buffer;
    private int length;
    private int beg;
    private int amount;
    private char firstSymbol;
    private int symbol;
    private int index;
    private int j;
    private String substring;
    BufferedReader reader;

    public void moveToBegin(int amount, int beg, BufferEntry[] buffer){ //сдвигает все символы в буфере в начало
        for(int i = 0; i < amount; i++){
            buffer[i].setZ_function(buffer[i + beg].getZ_function());
            buffer[i].setSymbol_code(buffer[i + beg].getSymbol_code());
            buffer[i].setIndex(buffer[i + beg].getIndex());
        }
    }

    private void initBuff(){
        buffer = new BufferEntry[length * 4 + 10];
        BufferEntry buf = new BufferEntry();
        for(int i = 0; i < 4 * length + 10; i++){
            buffer[i] = new BufferEntry();
        }
    }

    private void findFirstEqual() throws IOException {
        while ((char) symbol != firstSymbol && symbol != -1) {
            symbol = reader.read();// Читаем символ
            index++;
        }
    }

    private void whileNoMatchMakeZero(){
        while (buffer[beg].getZ_function() < length - j) {
            buffer[beg].makeZero();
            j++;
            amount--;
            beg++;
        }
    }

    private void getLengthSymbols() throws IOException {
        for (int i = amount; i < length && symbol != -1; i++) {
            buffer[i + beg].setZ_function(0);
            buffer[i + beg].setSymbol_code(symbol);
            buffer[i + beg].setIndex(index);
            for (int j = 0; j <= i; j++) {//вычисление z - функции
                if ((char) buffer[i + beg].getSymbol_code() == substring.charAt(buffer[j + beg].getZ_function())) {
                    buffer[j + beg].setZ_function(buffer[j + beg].getZ_function() + 1);;
                }
            }
            amount++;
            symbol = reader.read();// Читаем символ
            index++;
        }
    }

    public void find(String file_name, int[] a, String sub) throws IOException {
        length = sub.length();
        substring = sub;
        beg = 0;
        amount = 0;
        firstSymbol = sub.charAt(0);
        index = 0;
        int count = 0;
        reader = null;
        symbol = 0;
        initBuff();
        reader = new BufferedReader(new FileReader(file_name));
        symbol = reader.read();
        while (symbol != -1) {//пока не дошли до конца файла
            if (amount == 0) {//считываем, пока не найдем первое совпадение
                findFirstEqual();
                if (symbol == -1) {
                    break;
                }
            }
            getLengthSymbols();
            if (amount < sub.length()) {
                break;
            }
            j = 0;
            if (buffer[beg].getZ_function() == length) {//если с первого символа в буфере начинается вхождение подстроки
                a[count] = buffer[beg].getIndex();//сохраняем ого индекс
                count++;
                buffer[beg].makeZero();
                beg++;
                amount--;
                j++;
            }
            whileNoMatchMakeZero();
            if (beg >= sub.length() * 2) {
                moveToBegin(amount, beg, buffer);
                beg = 0;
            }
        }
        reader.close();
    }
}
