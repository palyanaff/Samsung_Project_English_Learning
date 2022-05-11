package ru.palyanaff.samsung_project_english_learning;

import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.customview.widget.Openable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

import ru.palyanaff.samsung_project_english_learning.databinding.ActivityMainBinding;
import ru.palyanaff.samsung_project_english_learning.screens.dictionary.DictionaryFragment;
import ru.palyanaff.samsung_project_english_learning.screens.ExamsFragment;
import ru.palyanaff.samsung_project_english_learning.screens.levels.LevelsFragment;
import ru.palyanaff.samsung_project_english_learning.screens.MenuFragment;
import ru.palyanaff.samsung_project_english_learning.screens.RunnerFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private NavController navController;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        // TODO: fix bottom navigation
        /*BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.dictionaryFragment,
                R.id.levelsFragment,
                R.id.examsFragment,
                R.id.menuFragment,
                R.id.runnerFragment)
                .build();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavController navController = Objects.requireNonNull(navHostFragment).getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);*/

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.dictionary:
                    navController.navigate(R.id.dictionaryFragment);
                    break;
                case R.id.exams:
                    navController.navigate(R.id.examsFragment);
                    break;
                case R.id.level:
                    navController.navigate(R.id.levelsFragment);
                    break;
                case R.id.menu:
                    navController.navigate(R.id.menuFragment);
                    break;
                case R.id.runner:
                    navController.navigate(R.id.runnerFragment);
                    break;

            }

            return true;
        });

    }

    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp();
    }


}