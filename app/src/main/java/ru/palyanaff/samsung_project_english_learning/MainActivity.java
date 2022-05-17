package ru.palyanaff.samsung_project_english_learning;

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

        // TODO: set back button navigation
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

    }

    
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp();
    }


}