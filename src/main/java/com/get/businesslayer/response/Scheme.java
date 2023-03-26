package com.get.businesslayer.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Scheme {
    private String scheme;
    private String explanation;
    private String experience_id;
    private String display;
    private ArrayList<Item> items;
}
