package com.prodius.service;

import com.prodius.exception.IncorrectString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ReadService {

    private ArrayList<HashMap<String, String>> csv = new ArrayList<>();

    public ArrayList<HashMap<String, String>> readFile() throws IncorrectString{
        boolean addMap = false;
        String s;
        ArrayList<String> head = null;
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (final InputStream resourceAsStream = classLoader.getResourceAsStream("products.csv")) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream))) {
                s = br.readLine();
                if (s.length() > 0) {
                    head = new ArrayList<>(Arrays.asList(s.split(",")));
                }
                while ((s = br.readLine()) != null) {
                    String[] data = s.split(",");
                    HashMap<String, String> csvDataLine = new HashMap<>();
                    for (int i = 0; i < data.length; i++) {
                        try {
                            if (!(data[i].isBlank())) {
                                csvDataLine.put(head.get(i), data[i]);
                                addMap = true;
                            } else {
                                throw new IncorrectString();
                            }
                        } catch (IncorrectString ex) {
                            ex.printStackTrace();
                            addMap = false;
                            break;
                        }
                    }
                    if (addMap) {
                        csv.add(csvDataLine);
                    }
                    addMap = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csv;
    }
}