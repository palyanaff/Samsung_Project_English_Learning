package ru.palyanaff.samsung_project_english_learning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;

import ru.palyanaff.samsung_project_english_learning.databinding.ActivityMainBinding;
import ru.palyanaff.samsung_project_english_learning.screens.DictionaryFragment;
import ru.palyanaff.samsung_project_english_learning.screens.ExamsFragment;
import ru.palyanaff.samsung_project_english_learning.screens.LevelsFragment;
import ru.palyanaff.samsung_project_english_learning.screens.MenuFragment;
import ru.palyanaff.samsung_project_english_learning.screens.RunnerFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new LevelsFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.dictionary:
                    replaceFragment(new DictionaryFragment());
                    break;
                case R.id.exams:
                    replaceFragment(new ExamsFragment());
                    break;
                case R.id.level:
                    replaceFragment(new LevelsFragment());
                    break;
                case R.id.menu:
                    replaceFragment(new MenuFragment());
                    break;
                case R.id.runner:
                    replaceFragment(new RunnerFragment());
                    break;

            }

            return true;
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}