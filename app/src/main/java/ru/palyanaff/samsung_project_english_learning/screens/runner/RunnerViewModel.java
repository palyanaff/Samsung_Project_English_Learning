package ru.palyanaff.samsung_project_english_learning.screens.runner;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import ru.palyanaff.samsung_project_english_learning.data.Word;
import ru.palyanaff.samsung_project_english_learning.datasource.Datasource;

public class RunnerViewModel extends ViewModel {

    private final Datasource datasource = new Datasource();
    private final ArrayList<Word> words = datasource.runnerWords();

    private final MutableLiveData<Integer> wordCounter = new MutableLiveData<>(0
            /*TODO: wordCounter = user.getEducatedWords.size()*/);

    private Word word = words.get(wordCounter.getValue());

    MutableLiveData<String> _currentWord = new MutableLiveData<>(word.getWordText());
    MutableLiveData<String> _answerWord = new MutableLiveData<>(word.getWordTranslation());

    public LiveData<Integer> getWordCounter() {
        return wordCounter;
    }

    public LiveData<String> getCurrentWord() {
        return _currentWord;
    }

    public LiveData<String> getAnswerWord() {
        return _answerWord;
    }

    public void getNextWord() {
        wordCounter.setValue(wordCounter.getValue() + 1);
        if (wordCounter.getValue() < words.size()){
            word = words.get(wordCounter.getValue());
            _currentWord.setValue(word.getWordText());
            _answerWord.setValue(word.getWordTranslation());
        }
    }

    public Word getWord() {
        return word;
    }

    public void getSkipWord(){
        words.add(words.get(wordCounter.getValue()));
        wordCounter.setValue(wordCounter.getValue() + 1);
        word = words.get(wordCounter.getValue());
        _currentWord.setValue(word.getWordText());
        _answerWord.setValue(word.getWordTranslation());
    }
}
