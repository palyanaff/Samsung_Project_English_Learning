package ru.palyanaff.samsung_project_english_learning.data;

import androidx.annotation.Nullable;

import java.util.Objects;

public class Word {

    private String wordText; // english word
    private String wordTranslation; // russian word

    public Word() {
    }

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

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Word) {
            Word anotherWord = (Word) obj;

            return (this.wordText.equals(anotherWord.wordText)
                    && this.wordTranslation.equals(anotherWord.wordTranslation));
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordText, wordTranslation);
    }
}