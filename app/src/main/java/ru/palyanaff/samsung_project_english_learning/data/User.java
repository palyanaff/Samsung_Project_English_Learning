package ru.palyanaff.samsung_project_english_learning.data;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private String email;
    private List<String> completeLevels;
    private List<Word> educatedWords;

    // these fields are related by index
    private List<String> dictionaryHeaders;
    private List<List<Word>> dictionaryWords;
    //

    public User(){
        // required for dataSnapshot.getValue(User.class)
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        completeLevels = new ArrayList<>();
        educatedWords = new ArrayList<>();

        dictionaryHeaders = new ArrayList<>();
        dictionaryWords = new ArrayList<>();
    }

    // copy constructor
    public User(@NonNull User user) {
        this.name = user.name;
        this.email = user.email;

        this.completeLevels = (user.completeLevels == null ?
                new ArrayList<>() : user.completeLevels);
        this.educatedWords = (user.educatedWords == null ?
                new ArrayList<>() : user.educatedWords);

        this.dictionaryHeaders = (user.dictionaryHeaders == null ?
                new ArrayList<>() : user.dictionaryHeaders);
        this.dictionaryWords = (user.dictionaryWords == null ?
                new ArrayList<>() : user.dictionaryWords);
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

    public List<Word> getWordsFromDictionary(String header) {

        if (dictionaryHeaders.contains(header)) {
            int headerPos = dictionaryHeaders.indexOf(header);
            return dictionaryWords.get(headerPos);
        }
        return null;
    }

    public void addCompleteLevel(String id){
        this.completeLevels.add(id);
    }

    public void addEducatedWord(Word word) {
        this.educatedWords.add(word);
    }

    public void addDictionary(String header, List<Word> words) {
        dictionaryHeaders.add(header);
        dictionaryWords.add(words);
    }

    public void addWordInDictionary(String header, Word word) {

        if (!dictionaryHeaders.contains(header)) {
            addDictionary(header, new ArrayList<>());
        }
        this.getWordsFromDictionary(header).add(word);
    }

    public void deleteWordFromDictionary(String header, Word word) {
        int headerPos = dictionaryHeaders.indexOf(header);

        dictionaryWords.get(headerPos).remove(word);
    }

    public void deleteDictionary(String header) {
        if (dictionaryHeaders.contains(header)) {

            int index = dictionaryHeaders.indexOf(header);

            dictionaryHeaders.remove(index);
            dictionaryWords.remove(index);
        }
    }
}
