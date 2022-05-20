package ru.palyanaff.samsung_project_english_learning.screens.dictionary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.palyanaff.samsung_project_english_learning.R;
import ru.palyanaff.samsung_project_english_learning.data.Word;
import ru.palyanaff.samsung_project_english_learning.databinding.FragmentNewWordBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewWordFragment extends Fragment {

    private static final String TAG = "NewWordFragment";
    FragmentNewWordBinding binding;

    public NewWordFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentNewWordBinding.inflate(getLayoutInflater());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding.addButton.setOnClickListener(v -> {
            String wordText = binding.textInputEditTextWord.getText().toString().trim();
            String wordTranslation = binding.textInputEditTextTranslation.getText().toString().trim();
            Word word = new Word(wordText, wordTranslation);
            // TODO: add new Word in user data
            Log.d(TAG, wordText + " " + wordTranslation);
        });

        return binding.getRoot();
    }
}