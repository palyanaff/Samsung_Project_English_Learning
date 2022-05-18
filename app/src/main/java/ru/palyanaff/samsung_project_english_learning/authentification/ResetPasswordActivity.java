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
import com.google.firebase.auth.FirebaseAuth;

import ru.palyanaff.samsung_project_english_learning.databinding.ActivityResetPasswordBinding;

public class ResetPasswordActivity extends AppCompatActivity {

    private ActivityResetPasswordBinding binding;
    
    private FirebaseAuth mAuth;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        mAuth = FirebaseAuth.getInstance();
        
        binding.resetPasswordButton.setOnClickListener(resetPassOnClick());
    }

    @NonNull
    private View.OnClickListener resetPassOnClick() {
        return (View v) -> {

            String email = binding.emailReset.getText().toString().trim();

            if (email.isEmpty()) {
                binding.emailReset.setError("E-mail is required!");
                binding.emailReset.requestFocus();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailReset.setError("Provide valid e-mail!");
                binding.emailReset.requestFocus();
                return;
            }

            binding.loadingReset.setVisibility(View.VISIBLE);
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(sendPassResetOnComplete());

        };
    }


    @NonNull
    private OnCompleteListener<Void> sendPassResetOnComplete() {
        return (Task<Void> sendPassResetTask) -> {

            if (sendPassResetTask.isSuccessful()) {
                Toast.makeText(this,
                        "Check your e-mail to reset your password", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, LoginActivity.class));

            } else {
                
                Toast.makeText(this,
                        "Failed to send password reset mail. Try again!",
                        Toast.LENGTH_LONG).show();
            }
        };
    }

}