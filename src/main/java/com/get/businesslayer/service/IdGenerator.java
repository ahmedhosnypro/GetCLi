package com.get.businesslayer.service;

import java.util.ArrayList;
import java.util.List;

public class IdGenerator {
    private IdGenerator() {
    }

    public static List<String> generateIds() {
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            String id = String.format("%07d", i);
            ids.add(id + "P");
        }
        return ids;
    }

}
