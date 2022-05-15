package ru.palyanaff.samsung_project_english_learning.screens.dictionary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.palyanaff.samsung_project_english_learning.R;
import ru.palyanaff.samsung_project_english_learning.databinding.FragmentWordBinding;
import ru.palyanaff.samsung_project_english_learning.adapter.WordAdapter;
import ru.palyanaff.samsung_project_english_learning.datasource.Datasource;

/**
 * A fragment representing a list of Items.
 */
public class WordFragment extends Fragment {
    private static final String TAG = "WordFragment";
    FragmentWordBinding binding;
    String dictionaryHeader;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public WordFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentWordBinding.inflate(getLayoutInflater());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_word_list, container, false);
        dictionaryHeader = WordFragmentArgs.fromBundle(getArguments()).getDictionaryHeader();
        initRecyclerView(view);

        return view;
    }

    public void initRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_word_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        WordAdapter wordAdapter = new WordAdapter(new Datasource().loadWords(dictionaryHeader));
        recyclerView.setAdapter(wordAdapter);
    }
}