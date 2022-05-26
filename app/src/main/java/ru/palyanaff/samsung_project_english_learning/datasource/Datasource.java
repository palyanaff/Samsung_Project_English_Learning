package ru.palyanaff.samsung_project_english_learning.datasource;

import android.content.Context;
import android.content.res.AssetManager;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Contract;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import ru.palyanaff.samsung_project_english_learning.data.Level;
import ru.palyanaff.samsung_project_english_learning.data.Word;

/**
 * Class which work with data of levels
 */
public class Datasource {

    private static final String TAG = "Datasource";

    private final Context context;

    private final List<Word> wordsAtoG;
    private final List<Word> wordsHtoM;
    private final List<Word> wordsNtoS;
    private final List<Word> wordsTtoZ;

    public Datasource(Context context) {
        this.context = context;

        List<Word>[] wordsLists = Datasource.getSourceLists(context);

        wordsAtoG = wordsLists[0];
        wordsHtoM = wordsLists[1];
        wordsNtoS = wordsLists[2];
        wordsTtoZ = wordsLists[3];
    }

    public List<Word> getWordsAtoG() {
        return wordsAtoG;
    }

    public List<Word> getWordsHtoM() {
        return wordsHtoM;
    }

    public List<Word> getWordsNtoS() {
        return wordsNtoS;
    }

    public List<Word> getWordsTtoZ() {
        return wordsTtoZ;
    }

    @NonNull
    @Contract("_ -> new")
    private static List<Word>[] getSourceLists(@NonNull Context context) {
        List<Word> wordsAtoG = new LinkedList<>();
        List<Word> wordsHtoM = new LinkedList<>();
        List<Word> wordsNtoS = new LinkedList<>();
        List<Word> wordsTtoZ = new LinkedList<>();

        AssetManager assetManager = context.getAssets();

        try {
            InputStream inputStream = assetManager.open("WordsWithTranslations.txt");

            Scanner scanner = new Scanner(inputStream);

            int i = 0;
            while (i++ < 1000 && scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isEmpty()) {
                    String[] wordAsArray = line.split(" : ");

                    // Word(eng, rus)
                    Word word = new Word(wordAsArray[1], wordAsArray[0]);
                    word.setToLowerCase(Locale.ROOT);

                    char firstLetter = word.getWordText().charAt(0);

                    if (firstLetter >= 'a' && firstLetter <= 'g') {
                        wordsAtoG.add(word);
                    } else if (firstLetter >= 'h' && firstLetter <= 'm') {
                        wordsHtoM.add(word);
                    } else if (firstLetter >= 'n' && firstLetter <= 's') {
                        wordsNtoS.add(word);
                    } else if (firstLetter >= 't' && firstLetter <= 'z') {
                        wordsTtoZ.add(word);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new List[] {
                wordsAtoG,
                wordsHtoM,
                wordsNtoS,
                wordsTtoZ
        };
    }

    /**
     * Load levels recourse
     * @return list with data(¿numbers?) of levels
     */
    public List<Level> loadLevel() {
        List<Level> levels = new ArrayList<>();

        levels.add(new Level("1", "First level", "First task", "Enter: first", "first"));
        levels.add(new Level("2", "Second level", "Enter the correct form", "I have ... (do) my homework", "done"));
        levels.add(new Level("3", "Third level", "Third task", "DO A FLIP x3", "3"));
        levels.add(new Level("4", "Fourth level", "Fourth task", "Enter: first", "first"));
        levels.add(new Level("5", "Fifth level", "Fifth task", "DO A FLIP x2", "2"));
        levels.add(new Level("6", "Sixth level", "Sixth task", "DO A FLIP x3", "3"));
        levels.add(new Level("7", "Seventh level", "Seventh task", "Enter: first", "first"));
        levels.add(new Level("8", "Eighth level", "Eighth task", "DO A FLIP x2", "2"));
        levels.add(new Level("9", "Ninth level", "Ninth task", "DO A FLIP x3", "3"));
        levels.add(new Level("10", "Tenth level", "Tenth task", "Enter: first", "first"));
        levels.add(new Level("11", "Eleventh level", "Eleventh task", "DO A FLIP x2", "2"));
        levels.add(new Level("12", "Twelfth level", "Twelfth task", "DO A FLIP x3", "3"));
        return levels;
    }

    /**
     * Load words for dictionary
     * @return list with data of words
     */
    public List<Word> loadWords(String dictionaryHeader) {

        if (dictionaryHeader.equals("Starts with A-G")){
            return getWordsAtoG();
        }

        if (dictionaryHeader.equals("Starts with H-M")){
            return getWordsHtoM();
        }

        if (dictionaryHeader.equals("Starts with N-S")){
            return getWordsNtoS();
        }

        if (dictionaryHeader.equals("Starts with T-Z")){
            return getWordsTtoZ();
        }

        return null;
    }

    /**
     * Load dictionary headers
     * @return list with data of headers
     */

    public List<String> loadDictionaryHeaders(){
        List<String> headers = new ArrayList<>();

        headers.add("Starts with A-G");
        headers.add("Starts with H-M");
        headers.add("Starts with N-S");
        headers.add("Starts with T-Z");


        return headers;
    }

    public List<Word> loadWordsForRunner() {
        List<Word> words = new ArrayList<>();
        words.addAll(getWordsAtoG());
        words.addAll(getWordsHtoM());
        words.addAll(getWordsNtoS());
        words.addAll(getWordsTtoZ());
        return words;
    }
}
