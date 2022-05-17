package ru.palyanaff.samsung_project_english_learning.data;

public class Word {

    private final String wordText;
    private final String wordTranslation;

    public Word(String wordText, String wordTranslation) {
        this.wordText = wordText;
        this.wordTranslation = wordTranslation;
    }

    public String getWordText() {
        return wordText;
    }

    public String getWordTranslation() {
        return wordTranslation;
    }
}