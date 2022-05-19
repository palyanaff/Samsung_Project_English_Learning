package ru.palyanaff.samsung_project_english_learning.screens.runner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;
import java.util.Objects;

import ru.palyanaff.samsung_project_english_learning.data.User;
import ru.palyanaff.samsung_project_english_learning.databinding.FragmentRunnerBinding;

public class RunnerFragment extends Fragment {
    private static final String TAG = "RunnerFragment";

    // TODO: replace user getting in viewModel
    private User user;

    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase database;
    private DatabaseReference usersRef;
    private String userID;

    FragmentRunnerBinding binding;
    RunnerViewModel viewModel;

    public RunnerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentRunnerBinding.inflate(getLayoutInflater());

        viewModel = new ViewModelProvider(requireActivity()).get(RunnerViewModel.class);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        userID = firebaseUser.getUid();
        database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("Users");

        setUserByUserFromDB();
    }

    private void setUserByUserFromDB() {
        usersRef.child(firebaseUser.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        user = new User(snapshot.getValue(User.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(),
                                "Failed to get actual data", Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (viewModel.getCurrentWord().getValue() != null){
            binding.runnerHeader.setText(viewModel.getCurrentWord().getValue());
        }

        // Setup a click listener for the Submit and Skip buttons.
        binding.runnerCheckButton.setOnClickListener(onSubmitWord());
        binding.runnerSkipButton.setOnClickListener(onSkipWord());
    }

    private View.OnClickListener onSubmitWord(){
        return v -> {
            String playerWord = Objects.requireNonNull(binding.runnerInputEditText.getText()).toString().toLowerCase(Locale.ROOT).trim();
            if (playerWord.equals(viewModel.getAnswerWord().getValue().toLowerCase(Locale.ROOT))){

                viewModel.getNextWord();
                binding.runnerHeader.setText(viewModel.getCurrentWord().getValue());
                binding.progressBar.setProgress(viewModel.getWordCounter().getValue());
                user.addEducatedWord(viewModel.getWord());
                setErrorTextField(false);
            } else {
                setErrorTextField(true);
            }
        };
    }

    private View.OnClickListener onSkipWord(){
        return v -> {
            viewModel.getSkipWord();
            binding.runnerHeader.setText(viewModel.getCurrentWord().getValue());
            binding.progressBar.setProgress(viewModel.getWordCounter().getValue());

            binding.runnerEditText.setErrorEnabled(false);
            binding.runnerInputEditText.setText(null);
        };
    }

    private void setErrorTextField(Boolean error) {
        if (error) {
            binding.runnerEditText.setErrorEnabled(true);
            binding.runnerEditText.setError("Try again");
        } else {
            binding.runnerEditText.setErrorEnabled(false);
            binding.runnerInputEditText.setText(null);
        }
    }

    // TODO: remove all saves in onDestroyView
    @Override
    public void onDetach() {
        super.onDetach();

        usersRef.child(userID).setValue(user)
                .addOnCompleteListener(setValueOnComplete());
    }

    @NonNull
    private OnCompleteListener<Void> setValueOnComplete() {
        return setValueTask -> {
            if (!setValueTask.isSuccessful()) {
                Toast.makeText(getContext(), "Failed to save word", Toast.LENGTH_SHORT).show();
            }
        };
    }
}