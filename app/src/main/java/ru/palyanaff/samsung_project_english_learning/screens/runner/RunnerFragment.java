package ru.palyanaff.samsung_project_english_learning.screens.runner;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //binding.runnerHeader.setText(viewModel.wordCounter.toString());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // TODO: update counter and words 
        binding.runnerHeader.setText(viewModel.wordCounter.getValue().toString());

        binding.runnerCheckButton.setOnClickListener(onSubmitWord());
        binding.runnerSkipButton.setOnClickListener(onSkipWord());
    }

    private View.OnClickListener onSubmitWord(){
        return v -> {
            String playerWord = binding.runnerInputEditText.getText().toString().toLowerCase(Locale.ROOT).trim();
            Toast.makeText(getContext(), "Submit", Toast.LENGTH_LONG).show();
            if (playerWord.equals("1")){
                setErrorTextField(false);
            } else {
                setErrorTextField(true);

            }
        };
    }

    private View.OnClickListener onSkipWord(){
        return v -> {
            Toast.makeText(getContext(), "Skip", Toast.LENGTH_LONG).show();

            viewModel.getNextWord();
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