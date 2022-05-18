package ru.palyanaff.samsung_project_english_learning.authentification;

import java.util.ArrayList;
import java.util.List;

import ru.palyanaff.samsung_project_english_learning.data.Word;

public class User {

    private String name;
    private String email;
    private List<String> completeLevels;
    private List<Word> educatedWords;

    public User(){
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        completeLevels = new ArrayList<>();
        educatedWords = new ArrayList<>();
    }

    public void addCompleteLevel(String id){
        completeLevels.add(id);
    }

    public List<Word> getEducatedWords() {
        return educatedWords;
    }

    public void addEducatedWord(Word word) {
        this.educatedWords.add(word);
    }
    public List<String> getCompleteLevels() {
        return completeLevels;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
