package ru.palyanaff.samsung_project_english_learning.screens.levels.task;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Locale;

import ru.palyanaff.samsung_project_english_learning.R;
import ru.palyanaff.samsung_project_english_learning.databinding.FragmentTaskBinding;
import ru.palyanaff.samsung_project_english_learning.screens.levels.LevelsFragment;

public class TaskFragment extends Fragment {
    private static final String TAG = "TaskFragment";

    FragmentTaskBinding binding;

    private String taskHeaderText;
    private String taskTextText;
    private String taskAnswer;

    public TaskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentTaskBinding.inflate(getLayoutInflater());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        taskHeaderText = TaskFragmentArgs.fromBundle(getArguments()).getTask()[0];
        taskTextText = TaskFragmentArgs.fromBundle(getArguments()).getTask()[1];
        taskAnswer = TaskFragmentArgs.fromBundle(getArguments()).getTask()[2];
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.taskHeader.setText(taskHeaderText);
        binding.taskText.setText(taskTextText);
        binding.taskCheckButton.setOnClickListener(onSubmitWord());
        binding.taskHintButton.setOnClickListener(onHintWord());
    }

    private View.OnClickListener onSubmitWord(){
        return v -> {
            String playerWord = binding.textInputEditText.getText().toString().toLowerCase(Locale.ROOT).trim();
            if (playerWord.equals(taskAnswer)){
                setErrorTextField(false);
                Toast.makeText(getContext(), "Correct", Toast.LENGTH_LONG).show();
            } else {
                setErrorTextField(true);

            }
        };
    }

    private View.OnClickListener onHintWord(){
        return v -> {
            String playerWord = binding.textInputEditText.getText().toString().toLowerCase(Locale.ROOT).trim();
            String hintWord = getHintWord(playerWord);

            binding.taskEditText.setErrorEnabled(false);
            binding.textInputEditText.setText(hintWord);
        };
    }

    private String getHintWord(String playerWord){
        int i = 0;
        String hintWord = "";
        if (!playerWord.equals("")) {
            for (i = 0; i < playerWord.length(); i++) {
                if (playerWord.charAt(i) == taskAnswer.charAt(i)) {
                    hintWord += taskAnswer.charAt(i);
                } else {
                    hintWord += taskAnswer.charAt(i);
                    return hintWord;
                }
            }

            if (i < taskAnswer.length()){
                hintWord += taskAnswer.charAt(i);
            }
        }
        else {
            hintWord += taskAnswer.charAt(i);
        }
        return hintWord;
    }

    private void setErrorTextField(Boolean error) {
        if (error) {
            binding.taskEditText.setErrorEnabled(true);
            binding.taskEditText.setError("Try again");
        } else {
            binding.taskEditText.setErrorEnabled(false);
            binding.textInputEditText.setText(null);
        }
    }


}