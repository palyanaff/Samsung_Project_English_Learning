package ru.palyanaff.samsung_project_english_learning.screens.dictionary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ru.palyanaff.samsung_project_english_learning.R;
import ru.palyanaff.samsung_project_english_learning.databinding.FragmentNewDictionaryBinding;
import ru.palyanaff.samsung_project_english_learning.datasource.Datasource;

/**
 * A {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class NewDictionary extends Fragment {

    private static final String TAG = "NewDictionary";
    private FragmentNewDictionaryBinding binding;

    public NewDictionary() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentNewDictionaryBinding.inflate(getLayoutInflater());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding.addButton.setOnClickListener(addHeader());
        return binding.getRoot();
    }

    private View.OnClickListener addHeader(){
        return v -> {
            // TODO: add headers in adapter(Dictionary fragment)
            String str = binding.textInputEditText.getText().toString().trim();
            Log.d(TAG, str);

        };
    }
}