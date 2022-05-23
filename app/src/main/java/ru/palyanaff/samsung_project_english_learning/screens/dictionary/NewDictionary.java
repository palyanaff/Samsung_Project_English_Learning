package ru.palyanaff.samsung_project_english_learning.screens.dictionary;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ru.palyanaff.samsung_project_english_learning.data.User;
import ru.palyanaff.samsung_project_english_learning.databinding.FragmentNewDictionaryBinding;

/**
 * A {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class NewDictionary extends Fragment {

    private static final String TAG = "NewDictionary";
    private FragmentNewDictionaryBinding binding;
    private FirebaseUser firebaseUser;
    private DatabaseReference usersRef;
    private User user;

    public NewDictionary() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentNewDictionaryBinding.inflate(getLayoutInflater());

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        usersRef = FirebaseDatabase.getInstance().getReference("Users");

        setUserByUserFromDB();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding.addButton.setOnClickListener(addHeader());
        return binding.getRoot();
    }

    private View.OnClickListener addHeader(){
        return v -> {
            String header = binding.textInputEditText.getText().toString().trim();

            if (header.isEmpty()) {
                binding.textInputEditText.setError("Header cannot be empty!");
                binding.textInputEditText.requestFocus();
                return;
            }

            if (header.length() > 255) {
                binding.textInputEditText.setError("Your header is too long!");
                binding.textInputEditText.requestFocus();
                return;
            }

            if (user != null) {
                user.addDictionary(header, new ArrayList<>());
            } else {
                Log.d(TAG, "User is null on adding dictionary");
            }

            binding.textInputEditText.getText().clear();
            Toast.makeText(NewDictionary.this.getContext(),
                    "New dictionary added!", Toast.LENGTH_LONG).show();
        };
    }

    private void setUserByUserFromDB() {
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
                    .setValue(user).addOnCompleteListener(setValueOnComplete());
        } else {
            Log.d(TAG, "User is null on saving user in DB");
        }
    }

    @NonNull
    private OnCompleteListener<Void> setValueOnComplete() {
        return setValueTask -> {

            if (!setValueTask.isSuccessful()) {
                Toast.makeText(NewDictionary.this.getContext(),
                        "Failed to save new dictionary",
                        Toast.LENGTH_LONG).show();
            }
        };
    }
}
