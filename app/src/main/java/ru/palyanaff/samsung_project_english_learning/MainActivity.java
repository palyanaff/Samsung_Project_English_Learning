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

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

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

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        //navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        navController = NavHostFragment.findNavController(getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment));
        /*NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        replaceFragment(new LevelsFragment());*/
        //navController = navHostFragment.getNavController();


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

    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp();
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
        fragmentTransaction.commit();
    }


}