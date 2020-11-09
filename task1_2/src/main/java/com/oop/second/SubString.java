package com.oop.second;
import java.io.*;

public class SubString {

    public void find(String file_name, int[] a, String sub){
        int length = sub.length();
        int beg = 0;
        int amount = 0;
        int index = 0;
        int count = 0;
        BufferEntry[] buffer = new BufferEntry[length * 4 + 10];
        BufferEntry buf = new BufferEntry();
        for(int i = 0; i < 4 * sub.length() + 10; i++){
            buffer[i] = new BufferEntry();
        }
        //считываем первый символ
        BufferedReader reader = null;
        int symbol = 0;
        try {
            reader = new BufferedReader(new FileReader(file_name));
            symbol = reader.read();
            while (symbol != -1) {//пока не дошли до конца файла
                if (amount == 0) {//считываем, пока не найдем первое совпадение
                    while ((char) symbol != sub.charAt(0) && symbol != -1) {
                        symbol = reader.read();// Читаем символ
                        index++;
                    }
                    if (symbol == -1) {
                        break;
                    }
                }
                for (int i = amount; i < sub.length() && symbol != -1; i++) {
                //записываем символы в массив и сразу вычияляем z - функцию
                //пока не считаем length символов или не дойдем до конца строки

                    buffer[i + beg].z_function = 0;
                    buffer[i + beg].symbol_code = symbol;
                    buffer[i + beg].index = index;
                    for (int j = 0; j <= i; j++) {//вычисление z - функции
                        if ((char) buffer[i + beg].symbol_code == sub.charAt(buffer[j + beg].z_function)) {
                            buffer[j + beg].z_function++;
                        }
                    }
                    amount++;
                    symbol = reader.read();// Читаем символ
                    index++;
                }
                if (amount < sub.length()) {
                    break;
                }
                int j = 0;
                if (buffer[beg].z_function == sub.length()) {//если с первого символа в буфере начинается вхождение подстроки
                    a[count] = buffer[beg].index;//сохраняем ого индекс
                    count++;

                    buf.makeZero(beg, buffer);

                    beg++;
                    amount--;
                    j++;
                }
                while (buffer[beg].z_function < sub.length() - j) {
                    buf.makeZero(beg, buffer);
                    j++;
                    amount--;
                    beg++;
                }
            //если буфер заполнен на половину, сдвигаем данные в нём в начало
                if (beg >= sub.length() * 2) {
                    buf.moveToBegin(amount, beg, buffer);
                    beg = 0;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
