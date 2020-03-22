package com.mglowinski;

import com.mglowinski.custom.CustomHashMap;

public class Main {

    public static void main(String[] args) {
        CustomHashMap<Integer, String> customHashMap = new CustomHashMap<>();
        customHashMap.put(1, "Maciej");
        customHashMap.put(2, "Asia");

        customHashMap.display();

        customHashMap.remove(2);
        customHashMap.display();
    }
}
