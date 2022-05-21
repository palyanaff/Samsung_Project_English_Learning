package ru.palyanaff.samsung_project_english_learning.data;

import androidx.annotation.Nullable;

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
}