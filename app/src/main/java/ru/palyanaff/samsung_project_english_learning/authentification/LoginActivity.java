package ru.palyanaff.samsung_project_english_learning.authentification;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ru.palyanaff.samsung_project_english_learning.MainActivity;
import ru.palyanaff.samsung_project_english_learning.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private ActivityLoginBinding binding;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(signInOnComplete());
    }

    @NonNull
    private OnCompleteListener<AuthResult> signInOnComplete() {

        return (Task<AuthResult> signInTask) -> {

            if (signInTask.isSuccessful() && mAuth.getCurrentUser() != null) {

                Toast.makeText(LoginActivity.this, "Successfully logged in",
                        Toast.LENGTH_LONG).show();
                binding.loadingLogin.setVisibility(View.GONE);

                LoginActivity.this.startActivity(new Intent(
                        LoginActivity.this, MainActivity.class));
            } else {
                Toast.makeText(LoginActivity.this,
                        "Failed to log in! Please check again your credentials",
                        Toast.LENGTH_LONG).show();
                binding.loadingLogin.setVisibility(View.GONE);
            }
        };
    }
}
