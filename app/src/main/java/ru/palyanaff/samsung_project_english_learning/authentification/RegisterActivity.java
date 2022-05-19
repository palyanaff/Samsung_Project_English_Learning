package ru.palyanaff.samsung_project_english_learning.authentification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ru.palyanaff.samsung_project_english_learning.data.User;
import ru.palyanaff.samsung_project_english_learning.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private ActivityRegisterBinding binding;

    private FirebaseAuth mAuth;

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef = database.getReference("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        setListeners();
    }

    private void setListeners() {
        binding.logoReg.setOnClickListener(v -> startActivity(
                new Intent(this, LoginActivity.class)));

        binding.registerUserButton.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {

        String email = binding.emailReg.getText().toString().trim();
        String username = binding.usernameReg.getText().toString().trim();
        String password = binding.passwordReg.getText().toString().trim();

        if (email.isEmpty()) {
            binding.emailReg.setError("E-mail is required!");
            binding.emailReg.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailReg.setError("Provide valid e-mail!");
            binding.emailReg.requestFocus();
            return;
        }

        if (username.isEmpty()) {
            binding.usernameReg.setError("Username is required!");
            binding.usernameReg.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            binding.passwordReg.setError("Password is required!");
            binding.passwordReg.requestFocus();
            return;
        }

        if (password.length() < 6) {
            binding.passwordReg.setError("Password's length must be at least 6 characters!");
            binding.passwordReg.requestFocus();
            return;
        }

        binding.loadingReg.setVisibility(View.VISIBLE);


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(createUserOnComplete(email, username));
    }

    @NonNull
    private OnCompleteListener<AuthResult> createUserOnComplete(String email, String username) {
        return (Task<AuthResult> createTask) -> {

            if (createTask.isSuccessful()) {

                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                if (firebaseUser != null) {

                    User user = new User(username, email);

                    usersRef.child(firebaseUser.getUid())
                            .setValue(user)
                            .addOnCompleteListener(RegisterActivity.this.setValueOnComplete());
                }

            } else {
                Toast.makeText(RegisterActivity.this,
                        "Failed to create user!",
                        Toast.LENGTH_LONG).show();
                binding.loadingReg.setVisibility(View.GONE);
            }
        };
    }

    @NonNull
    private OnCompleteListener<Void> setValueOnComplete() {
        return (Task<Void> setTask) -> {

            if (setTask.isSuccessful()) {
                Toast.makeText(RegisterActivity.this,
                        "User has been successfully registered!",
                        Toast.LENGTH_LONG).show();
                binding.loadingReg.setVisibility(View.GONE);

                RegisterActivity.this.startActivity(new Intent(RegisterActivity.this,
                        LoginActivity.class));
            } else {

                Toast.makeText(RegisterActivity.this,
                        "Failed to register user!",
                        Toast.LENGTH_LONG).show();
                binding.loadingReg.setVisibility(View.GONE);
            }
        };
    }
}
