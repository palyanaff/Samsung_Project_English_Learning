package ru.palyanaff.samsung_project_english_learning.data;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private String email;
    private List<String> completeLevels;
    private List<Word> educatedWords;
    private List<String> dictionaryHeaders;

    public User(){
        // required for dataSnapshot.getValue(User.class)
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        completeLevels = new ArrayList<>();
        educatedWords = new ArrayList<>();
        dictionaryHeaders = new ArrayList<>();
    }

    // copy constructor
    public User(User user) {
        this.name = user.name;
        this.email = user.email;

        this.completeLevels = (user.completeLevels == null ?
                new ArrayList<>() : user.completeLevels);
        this.educatedWords = (user.educatedWords == null ?
                new ArrayList<>() : user.educatedWords);
        this.dictionaryHeaders = (user.dictionaryHeaders == null ?
                new ArrayList<>() : user.dictionaryHeaders);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getCompleteLevels() {
        return completeLevels;
    }

    public List<Word> getEducatedWords() {
        return educatedWords;
    }

    public List<String> getDictionaryHeaders() {
        return dictionaryHeaders;
    }

    public void addCompleteLevel(String id){
        this.completeLevels.add(id);
    }

    public void addEducatedWord(Word word) {
        this.educatedWords.add(word);
    }

    public void addDictionaryHeader(String header) {
        this.dictionaryHeaders.add(header);
    }
}
