package ru.palyanaff.samsung_project_english_learning.screens;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ru.palyanaff.samsung_project_english_learning.MainActivity;
import ru.palyanaff.samsung_project_english_learning.R;
import ru.palyanaff.samsung_project_english_learning.authentification.LoginActivity;
import ru.palyanaff.samsung_project_english_learning.data.User;
import ru.palyanaff.samsung_project_english_learning.databinding.FragmentMenuBinding;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {

    private static final String TAG = "MenuFragment";

    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase database;
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
        database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("Users");

        this.setProfileData();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.logOutButton.setOnClickListener(logOut());
    }

    private View.OnClickListener logOut() {
        return v -> {
            mAuth.signOut();
            Toast.makeText(MenuFragment.this.getActivity(), "Succefully logged out", Toast.LENGTH_LONG).show();
            MenuFragment.this.startActivity(new Intent(MenuFragment.this.getActivity(), LoginActivity.class));
        };
    }

    private void setProfileData() {

        usersRef.child(firebaseUser.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);

                String name = user.getName();
                String email = user.getEmail();

                binding.usernameInput.setText(name);
                binding.emailInput.setText(email);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),
                        "Failed to get actual data", Toast.LENGTH_LONG).show();
            }
        });
    }
}
