package com.get.businesslayer.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Resonance {
    private ArrayList<Scheme> schemes;

    public List<Item> flatten() {
        List<Item> items = new ArrayList<>();
        for (Scheme scheme : schemes) {
            items.addAll(scheme.getItems());
        }
        return items;
    }
}
