package ru.palyanaff.samsung_project_english_learning.authentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ru.palyanaff.samsung_project_english_learning.MainActivity;
import ru.palyanaff.samsung_project_english_learning.R;
import ru.palyanaff.samsung_project_english_learning.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private ActivityLoginBinding binding;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        setListeners();
    }

    private void setListeners() {
        binding.loginButton.setOnClickListener(v -> loginUser());

        binding.registerButton.setOnClickListener(v -> startActivity(
                new Intent(this, RegisterActivity.class)));

        binding.resetButton.setOnClickListener(v -> startActivity(
                new Intent(this, ResetPasswordActivity.class)));

        binding.skipLogIn.setOnClickListener(v -> startActivity(
                new Intent(this, MainActivity.class)));

    }

    private void loginUser() {

        String email = binding.emailLogin.getText().toString().trim();
        String password = binding.passwordLogin.getText().toString().trim();

        if (email.isEmpty()) {
            binding.emailLogin.setError("E-mail is required!");
            binding.emailLogin.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailLogin.setError("Provide valid e-mail!");
            binding.emailLogin.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            binding.passwordLogin.setError("Password is required!");
            binding.passwordLogin.requestFocus();
            return;
        }

        if (password.length() < 6) {
            binding.passwordLogin.setError("Password's length must be at least 6 characters!");
            binding.passwordLogin.requestFocus();
            return;
        }

        binding.loadingLogin.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                if (firebaseUser != null) {

                    if (firebaseUser.isEmailVerified()) {
                        Toast.makeText(this, "Successfully logged in",
                                Toast.LENGTH_LONG).show();
                        binding.loadingLogin.setVisibility(View.GONE);
                        startActivity(new Intent(this, MainActivity.class));
                    } else {
                        firebaseUser.sendEmailVerification()
                                .addOnCompleteListener((Task<Void> sendTask) -> {

                                    String toastSendText;
                                    if (sendTask.isSuccessful()) {
                                        toastSendText = "Check your e-mail to verify your account " +
                                        "and try again after verifying";
                                    } else {
                                        toastSendText = "Failed to send verify message on your e-mail." +
                                                " Please try again";
                                    }

                                    Toast.makeText(this, toastSendText,
                                            Toast.LENGTH_LONG).show();
                                    binding.loadingLogin.setVisibility(View.GONE);
                        });
                    }
                }

            } else {

                Toast.makeText(this,
                        "Failed to log in! Please check again your credentials",
                        Toast.LENGTH_LONG).show();
                binding.loadingLogin.setVisibility(View.GONE);
            }
        });
    }
}
