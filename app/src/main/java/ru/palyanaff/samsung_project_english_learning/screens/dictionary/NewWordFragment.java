package ru.palyanaff.samsung_project_english_learning.screens.dictionary;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

import ru.palyanaff.samsung_project_english_learning.R;
import ru.palyanaff.samsung_project_english_learning.data.User;
import ru.palyanaff.samsung_project_english_learning.data.Word;
import ru.palyanaff.samsung_project_english_learning.databinding.FragmentNewWordBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewWordFragment extends Fragment {

    private static final String TAG = "NewWordFragment";

    private FirebaseUser firebaseUser;
    private DatabaseReference usersRef;

    private AppCompatActivity activity;
    private NavController navController;

    private FragmentNewWordBinding binding;
    private User user;
    private String dictionaryHeader;

    public NewWordFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentNewWordBinding.inflate(getLayoutInflater());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // header
        setUser();
        dictionaryHeader = NewWordFragmentArgs.fromBundle(getArguments()).getHeader();

        binding.addButton.setOnClickListener(addButtonListener());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getView().setOnKeyListener((View v, int keyCode, KeyEvent event) -> {
        // FIXME: not working correctly, not even getting into this 'if' statement
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                Log.e(TAG, "Back button pressed");
                activity = (AppCompatActivity) getContext();
                navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
                navController.navigate(R.id.action_newWordFragment_to_dictionaryFragment);
            }

            return false;
        });
    }

    @NonNull
    private View.OnClickListener addButtonListener() {
        return (View v) -> {
            String wordEng = binding.textInputEditTextWord.getText().toString()
                    .trim().toLowerCase(Locale.ROOT);
            String wordRus = binding.textInputEditTextTranslation.getText().toString()
                    .trim().toLowerCase(Locale.ROOT);

            if (wordEng.isEmpty()) {
                binding.textInputEditTextWord.setError("Word cannot be empty!");
                binding.textInputEditTextWord.requestFocus();
                return;
            }

            if (wordEng.length() > 255) {
                binding.textInputEditTextWord.setError("Your word is too long!");
                binding.textInputEditTextWord.requestFocus();
                return;
            }

            if (wordRus.isEmpty()) {
                binding.textInputEditTextTranslation.setError("Word cannot be empty!");
                binding.textInputEditTextTranslation.requestFocus();
                return;
            }

            if (wordRus.length() > 255) {
                binding.textInputEditTextTranslation.setError("Your word is too long!");
                binding.textInputEditTextTranslation.requestFocus();
                return;
            }

            Word word = new Word(wordEng, wordRus)  ;
            user.addWordInDictionary(dictionaryHeader, word);

            binding.textInputEditTextWord.getText().clear();
            binding.textInputEditTextTranslation.getText().clear();
            binding.textInputEditTextWord.requestFocus();

            Toast.makeText(NewWordFragment.this.getContext(),
                    "New word added!", Toast.LENGTH_SHORT).show();
        };
    }

    private void setUser() {

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        usersRef = FirebaseDatabase.getInstance().getReference("Users");

        usersRef.child(firebaseUser.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue(User.class) != null) {
                    user = new User(snapshot.getValue(User.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),
                        "Failed to get actual data", Toast.LENGTH_LONG).show();
                Log.d(TAG, "Failed to get actual data");
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();

        if (user != null) {

            usersRef.child(firebaseUser.getUid())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            usersRef.child(firebaseUser.getUid())
                                    .setValue(user).addOnCompleteListener(setValueOnComplete());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.d(TAG, "User is null on saving user in DB");
                        }
                    });
        }
    }

    @NonNull
    private OnCompleteListener<Void> setValueOnComplete() {
        return (Task<Void> setValueTask) -> {
            if (!setValueTask.isSuccessful()) {
                Toast.makeText(NewWordFragment.this.getContext(),
                        "Failed to save new word(s)",
                        Toast.LENGTH_LONG).show();
            }
        };
    }


}
