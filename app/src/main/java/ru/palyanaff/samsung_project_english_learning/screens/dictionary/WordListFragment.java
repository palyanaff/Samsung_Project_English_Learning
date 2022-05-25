package ru.palyanaff.samsung_project_english_learning.screens.dictionary;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ru.palyanaff.samsung_project_english_learning.R;
import ru.palyanaff.samsung_project_english_learning.adapter.WordAdapter;
import ru.palyanaff.samsung_project_english_learning.data.User;
import ru.palyanaff.samsung_project_english_learning.data.Word;
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dictionaryHeader = WordListFragmentArgs.fromBundle(getArguments()).getDictionaryHeader();
        setHasOptionsMenu(true);
        binding.addWordButton.setOnClickListener(addWordListener());

        View view = binding.getRoot();

        if (isDefaultHeader(dictionaryHeader)) {
            Datasource datasource = new Datasource(getContext());
            List<Word> words = datasource.loadWords(dictionaryHeader);
            //noinspection ComparatorCombinators
            Collections.sort(words,
                    (o1, o2) -> o1.getWordText().compareTo(o2.getWordText()));
            initRecyclerView(view, words);
        } else {
            workWithWords(view);
        }

        return view;
    }

    @NonNull
    private View.OnClickListener addWordListener() {
        return (View v) -> {
            if (isDefaultHeader(dictionaryHeader)) {
                Toast.makeText(WordListFragment.this.getActivity(),
                                "Impossible to add words in default dictionaries", Toast.LENGTH_SHORT)
                        .show();
                return;
            }

            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
            WordListFragmentDirections.ActionWordFragmentToNewWordFragment action =
                    WordListFragmentDirections.actionWordFragmentToNewWordFragment(dictionaryHeader);
            navController.navigate(action);
        };
    }

    public static boolean isDefaultHeader(String header) {
        final String[] defaultHeaders = {
                "Starts with A-G",
                "Starts with H-M",
                "Starts with N-S",
                "Starts with T-Z"
        };

        return Arrays.asList(defaultHeaders).contains(header);
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_word_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    public void initRecyclerView(View view, List<Word> words) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_word_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        WordAdapter wordAdapter = new WordAdapter(
                WordListFragment.this.getContext(), dictionaryHeader, words);
        recyclerView.setAdapter(wordAdapter);
    }

    private void workWithWords(View view) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("Users");

        usersRef.child(firebaseUser.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.getValue(User.class) != null) {
                            User newUser = new User(snapshot.getValue(User.class));

                            List<Word> words = newUser.getWordsFromDictionary(dictionaryHeader);
                            //noinspection ComparatorCombinators
                            Collections.sort(words,
                                    (o1, o2) -> o1.getWordText().compareTo(o2.getWordText()));

                            initRecyclerView(view, words);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d(TAG, "Failed on getting actual words");
                    }
                });
    }
}
