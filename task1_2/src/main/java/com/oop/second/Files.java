package com.oop.second;

import java.io.File;
import java.io.IOException;

public class Files {
    public void createFile(String s){
        File myObj = new File(s);
        try {
            myObj.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
