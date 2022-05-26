package ru.palyanaff.samsung_project_english_learning.screens.runner;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ru.palyanaff.samsung_project_english_learning.data.Word;
import ru.palyanaff.samsung_project_english_learning.datasource.Datasource;

public class RunnerViewModel extends AndroidViewModel {

    private static final String TAG = "RunnerViewModel";

    @SuppressLint("StaticFieldLeak")
    private final Context context;
    private final Datasource datasource;
    private final List<Word> words;

    private MutableLiveData<Integer> wordCounter;

    private Word word;

    private final MutableLiveData<String> _currentWord;
    private final MutableLiveData<String> _answerWord;

    public RunnerViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        datasource = new Datasource(context);
        words = datasource.getWordsForRunner();

        wordCounter= new MutableLiveData<>(0);
        word = words.get(wordCounter.getValue());

        _currentWord = new MutableLiveData<>(word.getWordText());
        _answerWord = new MutableLiveData<>(word.getWordTranslation());
    }


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
        if (wordCounter.getValue() < words.size()){
            word = words.get(wordCounter.getValue());
            _currentWord.setValue(word.getWordText());
            _answerWord.setValue(word.getWordTranslation());
        }
        wordCounter.setValue(wordCounter.getValue() + 1);
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

    public void setWordCounter(int currIndex) {
        this.wordCounter = new MutableLiveData<>(currIndex);
    }
}
