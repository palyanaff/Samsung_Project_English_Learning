package ru.palyanaff.samsung_project_english_learning.screens.dictionary;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ru.palyanaff.samsung_project_english_learning.R;
import ru.palyanaff.samsung_project_english_learning.databinding.FragmentWordBinding;
import ru.palyanaff.samsung_project_english_learning.adapter.WordAdapter;
import ru.palyanaff.samsung_project_english_learning.databinding.FragmentWordListBinding;
import ru.palyanaff.samsung_project_english_learning.datasource.Datasource;

/**
 * A fragment representing a list of Items.
 */
public class WordListFragment extends Fragment {
    private static final String TAG = "WordFragment";
    FragmentWordListBinding binding;
    String dictionaryHeader;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public WordListFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentWordListBinding.inflate(getLayoutInflater());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dictionaryHeader = WordListFragmentArgs.fromBundle(getArguments()).getDictionaryHeader();
        binding.addWordButton.setOnClickListener(v -> {
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
            //@NonNull WordFragmentDirections action = WordFragmentDirections.actionWordFragmentToNewWordFragment();
            navController.navigate(R.id.action_wordFragment_to_newWordFragment);
        });

        initRecyclerView(binding.getRoot());

        return binding.getRoot();
    }

    public void initRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_word_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        WordAdapter wordAdapter = new WordAdapter(new Datasource().loadWords(dictionaryHeader));
        recyclerView.setAdapter(wordAdapter);
    }
}