package ru.palyanaff.samsung_project_english_learning.screens.runner;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import ru.palyanaff.samsung_project_english_learning.data.Word;
import ru.palyanaff.samsung_project_english_learning.datasource.Datasource;

public class RunnerViewModel extends ViewModel {

    Datasource datasource = new Datasource();
    ArrayList<Word> words = datasource.runnerWords();

    MutableLiveData<Integer> wordCounter = new MutableLiveData<>(0);


    public void getNextWord() {
        wordCounter.setValue(wordCounter.getValue().intValue() + 1);
    }
}
