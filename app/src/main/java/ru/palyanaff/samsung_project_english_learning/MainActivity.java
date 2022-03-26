package ru.palyanaff.samsung_project_english_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton button = findViewById(R.id.levels_button);
        button.setOnClickListener(this::openLevel);
    }

    public void openLevel(View view)
    {
        Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();
    }

}