package ru.palyanaff.samsung_project_english_learning.screens.runner;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import ru.palyanaff.samsung_project_english_learning.R;
import ru.palyanaff.samsung_project_english_learning.data.Word;
import ru.palyanaff.samsung_project_english_learning.databinding.FragmentRunnerBinding;
import ru.palyanaff.samsung_project_english_learning.datasource.Datasource;

public class RunnerFragment extends Fragment {
    private static final String TAG = "RunnerFragment";

    FragmentRunnerBinding binding;
    RunnerViewModel viewModel = new RunnerViewModel();

    public RunnerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentRunnerBinding.inflate(getLayoutInflater());

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.runnerHeader.setText(viewModel.getCurrentWord());

        // Setup a click listener for the Submit and Skip buttons.
        binding.runnerCheckButton.setOnClickListener(onSubmitWord());
        binding.runnerSkipButton.setOnClickListener(onSkipWord());
    }

    private View.OnClickListener onSubmitWord(){
        return v -> {
            String playerWord = Objects.requireNonNull(binding.runnerInputEditText.getText()).toString().toLowerCase(Locale.ROOT).trim();
            if (playerWord.equals(viewModel.getAnswerWord().toLowerCase(Locale.ROOT))){

                viewModel.getNextWord();
                binding.runnerHeader.setText(viewModel.getCurrentWord());
                binding.progressBar.setProgress(viewModel.wordCounter.getValue());

                setErrorTextField(false);
            } else {
                setErrorTextField(true);

            }
        };
    }

    private View.OnClickListener onSkipWord(){
        return v -> {
            viewModel.getSkipWord();
            binding.runnerHeader.setText(viewModel.getCurrentWord());
            binding.progressBar.setProgress(viewModel.wordCounter.getValue().intValue());

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
}