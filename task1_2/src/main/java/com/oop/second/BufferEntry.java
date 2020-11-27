package com.oop.second;

public class BufferEntry {
    private int z_function;
    private int symbol_code;
    private int index;

    public void makeZero(){
        this.z_function = 0;
        this.symbol_code = 0;
        this.index = 0;
    }

    public int getZ_function() {
        return z_function;
    }

    public void setZ_function(int z_function) {
        this.z_function = z_function;
    }

    public int getSymbol_code() {
        return symbol_code;
    }

    public void setSymbol_code(int symbol_code) {
        this.symbol_code = symbol_code;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
