package ru.palyanaff.samsung_project_english_learning.datasource;

import java.util.ArrayList;

import ru.palyanaff.samsung_project_english_learning.R;
import ru.palyanaff.samsung_project_english_learning.screens.dictionary.data.Word;
import ru.palyanaff.samsung_project_english_learning.screens.levels.data.Level;

/**
 * Class which work with data of levels
 */
public class Datasource {
    /**
     * Load levels recourse
     * @return list with data(¿numbers?) of levels
     */
    public ArrayList<Level> loadLevel() {
        // TODO: Need to update (load from server or add normal string resource)
        ArrayList<Level> levels = new ArrayList<>();

        // TODO: make normal counter of levels(Id)
        levels.add(new Level("1", "First level", "First task", "Enter: first", "first"));
        levels.add(new Level("2", "Second level", "Second task", "DO A FLIP x2", "2"));
        levels.add(new Level("3", "Third level", "Third task", "DO A FLIP x3", "3"));

        return levels;
    }

    /**
     * Load words for dictionary
     * @return list with data of words
     */
    public ArrayList<Word> loadWords(String dictionaryHeader) {

        // TODO: Need to update (load from server or add normal string resource)
        ArrayList<Word> words = new ArrayList<>();
        if (dictionaryHeader.equals("A1")){
            words.add(new Word("Hello", "Привет"));
            words.add(new Word("Word", "Мир"));
            words.add(new Word("English", "Английский"));
        }

        if (dictionaryHeader.equals("B2")){
            words.add(new Word("Abandon", "Покидать"));
            words.add(new Word("Edition", "Версия"));
            words.add(new Word("Minister", "Министр"));
        }

        return words;
    }
    /**
     * Load dictionary headers
     * @return list with data of headers
     */
    public ArrayList<String> loadDictionaryHeader(){
        ArrayList<String> headers = new ArrayList<>();

        headers.add("A1");
        headers.add("B2");

        return headers;
    }
}
