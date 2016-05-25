package com.netcracker.edu.java.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Sony on 21.07.2015.
 */

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("What name do you want to search?");
        InputStream inStream = System.in;
        InputStreamReader inReader = new InputStreamReader(inStream);
        BufferedReader inPut = new BufferedReader(inReader);
        String input = inPut.readLine();
        FileSearchImpl findName = new FileSearchImpl(input);
        findName.search("C:\\Users\\Sony\\Desktop\\Java\\");
        findName.getResult();
    }
}
