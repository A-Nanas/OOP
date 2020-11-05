package com.oop.second;

public class BufferEntry {
    int z_function;
    int symbol_code;
    int index;
    public void makeZero(int i, BufferEntry[] buffer){
        buffer[i].z_function = 0;
        buffer[i].symbol_code = 0;
        buffer[i].index = 0;
    }

    public void moveToBegin(int amount, int beg, BufferEntry[] buffer){ //сдвигает все символы в буфере в начало
        for(int i = 0; i < amount; i++){
            buffer[i].z_function = buffer[i + beg].z_function;
            buffer[i].symbol_code = buffer[i + beg].symbol_code;
            buffer[i].index = buffer[i + beg].index;
        }
    }
}
