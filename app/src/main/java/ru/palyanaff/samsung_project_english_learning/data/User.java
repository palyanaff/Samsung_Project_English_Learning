package ru.palyanaff.samsung_project_english_learning.data;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private String email;
    private List<String> completeLevels;
    private List<Word> educatedWords;
    private List<Dictionary> dictionaries;


    public User(){
        // required for dataSnapshot.getValue(User.class)
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.completeLevels = new ArrayList<>();
        this.educatedWords = new ArrayList<>();
        this.dictionaries = new ArrayList<>();
    }

    // copy constructor
    public User(@NonNull User user) {
        this.name = user.name;
        this.email = user.email;

        this.completeLevels = (user.completeLevels == null ?
                new ArrayList<>() : user.completeLevels);
        this.educatedWords = (user.educatedWords == null ?
                new ArrayList<>() : user.educatedWords);
        this.dictionaries = (user.dictionaries == null ?
                new ArrayList<>() : user.dictionaries);
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

    public List<Dictionary> getDictionaries() {
        return dictionaries;
    }

    public List<String> getDictionaryHeaders() {
        List<String> dictionaryHeaders = new ArrayList<>();

        for (Dictionary dict : dictionaries) {
            dictionaryHeaders.add(dict.getHeader());
        }

        return dictionaryHeaders;
    }

    public Dictionary getDictionary(String header) {
        for (Dictionary dict : dictionaries) {
            if (dict.getHeader().equals(header)) {
                return dict;
            }
        }
        // if not found
        return null;
    }

    public List<Word> getWordsFromDictionary(String header) {

        Dictionary dict = this.getDictionary(header);
        if (dict != null) {
            return dict.getWords();
        }

        return null;
    }

    public void addCompleteLevel(String id){
        if (!completeLevels.contains(id)) {
            completeLevels.add(id);
        }
    }

    public void addEducatedWord(Word word) {
        if (!educatedWords.contains(word)) {
            educatedWords.add(word);
        }
    }

    public void addDictionary(String header, List<Word> words) {
        Dictionary newDict = new Dictionary(header, words);
        if (!dictionaries.contains(newDict)) {
            dictionaries.add(newDict);
        }
    }

    public void addWordInDictionary(String header, Word word) {
        Dictionary dict = this.getDictionary(header);
        if (dict != null) { // if found
            if (!dict.getWords().contains(word)) {
                dict.getWords().add(word);
            }
        } else {
            List<Word> newWords = new ArrayList<>();
            newWords.add(word);
            dictionaries.add(new Dictionary(header, newWords));
        }
    }

    public void deleteWordFromDictionary(String header, Word word) {
        this.getDictionary(header).getWords().remove(word);
    }

    public void deleteDictionary(String header) {
        dictionaries.remove(getDictionary(header));
    }
}
