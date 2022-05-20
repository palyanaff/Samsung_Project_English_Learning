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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import ru.palyanaff.samsung_project_english_learning.R;
import ru.palyanaff.samsung_project_english_learning.adapter.DictionaryHeaderAdapter;
import ru.palyanaff.samsung_project_english_learning.databinding.FragmentDictionaryBinding;
import ru.palyanaff.samsung_project_english_learning.datasource.Datasource;
import ru.palyanaff.samsung_project_english_learning.screens.levels.LevelsFragmentDirections;

public class DictionaryFragment extends Fragment {

    private static final String TAG = "DictionaryFragment";
    private FragmentDictionaryBinding binding;
    private FloatingActionButton button;
    // TODO: try add new header
    public ArrayList<String> headers;

    public DictionaryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentDictionaryBinding.inflate(getLayoutInflater());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dictionary, container, false);
        initRecyclerView(view);

        button = view.findViewById(R.id.add_button);
        button.setOnClickListener(v -> {
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
            //@NonNull DictionaryFragmentDirections action =  DictionaryFragmentDirections.actionDictionaryFragmentToNewDictionary();

            navController.navigate(R.id.action_dictionaryFragment_to_newDictionary);

        });

        return view;
    }

    public void initRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.dictionary_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        // TODO: load dictionary headers from user data
        DictionaryHeaderAdapter dictionaryHeaderAdapter = new DictionaryHeaderAdapter(new Datasource().loadDictionaryHeader());
        recyclerView.setAdapter(dictionaryHeaderAdapter);
    }
}