package ru.palyanaff.samsung_project_english_learning.screens.runner;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import ru.palyanaff.samsung_project_english_learning.data.Word;
import ru.palyanaff.samsung_project_english_learning.datasource.Datasource;

public class RunnerViewModel extends ViewModel {

    Datasource datasource = new Datasource();
    ArrayList<Word> words = datasource.runnerWords();

    private Word word = words.get(0);
    MutableLiveData<Integer> wordCounter = new MutableLiveData<>(0);
    MutableLiveData<String> _currentWord = new MutableLiveData<>(word.getWordText());
    MutableLiveData<String> _answerWord = new MutableLiveData<>(word.getWordTranslation());

    public String getCurrentWord() {
        return _currentWord.getValue();
    }

    public String getAnswerWord() {
        return _answerWord.getValue();
    }

    public void getNextWord() {
        wordCounter.setValue(wordCounter.getValue() + 1);
        if (wordCounter.getValue() < words.size()){
            word = words.get(wordCounter.getValue());
            _currentWord.setValue(word.getWordText());
            _answerWord.setValue(word.getWordTranslation());
        }
    }
}
