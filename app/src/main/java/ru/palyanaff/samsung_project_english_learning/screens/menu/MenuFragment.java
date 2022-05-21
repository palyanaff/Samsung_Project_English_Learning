package ru.palyanaff.samsung_project_english_learning.screens.menu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ru.palyanaff.samsung_project_english_learning.R;
import ru.palyanaff.samsung_project_english_learning.authentication.LoginActivity;
import ru.palyanaff.samsung_project_english_learning.data.User;
import ru.palyanaff.samsung_project_english_learning.databinding.FragmentMenuBinding;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {

    private static final String TAG = "MenuFragment";

    private User user;

    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference usersRef;

    private FragmentMenuBinding binding;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentMenuBinding.inflate(getLayoutInflater());

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("Users");

        // FIXME: 'user' keeps being null for some reason
        setUserByUserFromDB();
        // and so it crashes here
        setProfileData();
    }

    private void setUserByUserFromDB() {
        usersRef.child(firebaseUser.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        user = new User(snapshot.getValue(User.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e(TAG, "Failed to get current user data", error.toException());
                    }
                });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        verifyButtonController();
        binding.verifyEmailButton.setOnClickListener(verifyEmailOnClick());

        return binding.getRoot();
    }

    @NonNull
    private View.OnClickListener verifyEmailOnClick() {
        return (View v) -> firebaseUser.sendEmailVerification()
                .addOnCompleteListener(sendOnComplete());
    }

    @NonNull
    private OnCompleteListener<Void> sendOnComplete() {
        return (Task<Void> sendTask) -> {
            String toastSendText;
            if (sendTask.isSuccessful()) {
                toastSendText = "Check your e-mail to verify your account " +
                        "and try again after verifying";
            } else {
                toastSendText = "Failed to send verify message on your e-mail. " +
                        "Please try again";
            }

            Toast.makeText(getContext(), toastSendText, Toast.LENGTH_LONG).show();
        };
    }

    private void verifyButtonController() {
        if (firebaseUser.isEmailVerified()) {
            binding.verifyEmailButton.setBackgroundTintList(
                    AppCompatResources.getColorStateList(getContext(), R.color.green));
            binding.verifyEmailButton.setText(getResources().getString(R.string.email_verified));
            binding.verifyEmailButton.setEnabled(false);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.logOutButton.setOnClickListener(logOut());
    }

    private View.OnClickListener logOut() {
        return (View v) -> {
            mAuth.signOut();
            Toast.makeText(MenuFragment.this.getActivity(), "Successfully logged out", Toast.LENGTH_LONG).show();
            MenuFragment.this.startActivity(new Intent(MenuFragment.this.getActivity(), LoginActivity.class));
        };
    }

    private void setProfileData() {
        if (user != null) {
            binding.usernameInput.setText(user.getName());
            binding.emailInput.setText(user.getEmail());
        }
    }
}
