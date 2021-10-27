package com.matheuscordeiro;

import java.util.*;

public class MyDictionary {
    Map<String, String> wordMap;

    public MyDictionary(Map<String, String> wordMap) {
        this.wordMap = wordMap;
    }

    public MyDictionary() {
        wordMap = new HashMap<String, String>();
    }
    public void add(final String word, final String meaning) {
        wordMap.put(word, meaning);
    }
    public String getMeaning(final String word) {
        return wordMap.get(word);
    }
}