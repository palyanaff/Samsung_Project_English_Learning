package ru.palyanaff.samsung_project_english_learning.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dictionary {
    private String header;
    private final List<Word> words;

    public Dictionary() {
        this.words = new ArrayList<>();
    }

    public Dictionary(String header, List<Word> words) {
        this.header = header;
        this.words = words;
    }

    // copy constructor
    public Dictionary(Dictionary dictionary) {
        this.header = dictionary.header;
        this.words = (dictionary.words == null ?
                new ArrayList<>() : dictionary.words);
    }

    public String getHeader() {
        return header;
    }

    public List<Word> getWords() {
        return words;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof Dictionary)) {
            Dictionary anotherDictionary = (Dictionary) obj;

            return (header.equals(anotherDictionary.header)
                    && words.equals(anotherDictionary.words));
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, words);
    }
}
