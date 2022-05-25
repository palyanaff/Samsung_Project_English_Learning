package ru.palyanaff.samsung_project_english_learning.screens.menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
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
    private UserSettings userSettings;

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
        this.workWithUser();

        userSettings = (UserSettings) MenuFragment.this.getActivity().getApplication();
        this.loadSharedPreferences();
        this.initSwitchListener();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        verifyButtonController();
        binding.verifyEmailButton.setOnClickListener(verifyEmailOnClick());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.logOutButton.setOnClickListener(logOut());
    }

    private void workWithUser() {
        usersRef.child(firebaseUser.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.getValue(User.class) != null) {
                            user = new User(snapshot.getValue(User.class));
                        }

                        setProfileData();
                        setProfileProgress();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e(TAG, "Failed to get current user data", error.toException());
                    }
                });
    }

    private void setProfileProgress() {
        if (user != null) {
            binding.usernameEducatedWordsCounter.setText(
                    String.valueOf(user.getEducatedWords().size()));
            binding.usernameCompletedLevelsCounter.setText(
                    String.valueOf(user.getCompleteLevels().size()));
            }
    }

    private void setProfileData() {
        if (user != null) {
            binding.usernameInput.setText(user.getName());
            binding.emailInput.setText(user.getEmail());
        }
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
                toastSendText = "Check your e-mail to verify your account";
            } else {
                toastSendText = "Failed to send verify message on your e-mail. " +
                        "Please try again later";
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

    private View.OnClickListener logOut() {
        return (View v) -> {
            mAuth.signOut();
            Toast.makeText(MenuFragment.this.getActivity(),
                    "Successfully logged out", Toast.LENGTH_LONG).show();
            startActivity(new Intent(MenuFragment.this.getActivity(), LoginActivity.class));
        };
    }

    private void loadSharedPreferences() {
        SharedPreferences sharedPreferences = getActivity()
                .getSharedPreferences(UserSettings.PREFERENCES, Context.MODE_PRIVATE);
        String theme = sharedPreferences.getString(
                UserSettings.CUSTOM_THEME, UserSettings.LIGHT_THEME);
        userSettings.setCustomTheme(theme);
    }

    private void initSwitchListener() {
        binding.themeSwitch.setOnCheckedChangeListener(
                (CompoundButton buttonView, boolean isChecked) -> {

            String theme = (isChecked ? UserSettings.DARK_THEME : UserSettings.LIGHT_THEME);
            userSettings.setCustomTheme(theme);


            SharedPreferences.Editor editor = MenuFragment.this.getActivity()
                    .getSharedPreferences(UserSettings.PREFERENCES, Context.MODE_PRIVATE)
                    .edit();
            editor.putString(UserSettings.CUSTOM_THEME, userSettings.getCustomTheme());
            editor.apply();

            MenuFragment.this.updateTheme();
        });
    }

    private void updateTheme() {
        final int colorBlack = ContextCompat.getColor(getActivity(), R.color.black);
        final int colorWhite = ContextCompat.getColor(getActivity(), R.color.white);


        if (userSettings.getCustomTheme().equals(UserSettings.DARK_THEME)) {
            setTextColor(colorWhite);
            binding.menuParentView.setBackgroundColor(colorBlack);
            binding.themeSwitch.setChecked(true);
        } else {
            setTextColor(colorBlack);
            binding.menuParentView.setBackgroundColor(colorWhite);
            binding.themeSwitch.setChecked(false);
        }
    }

    private void setTextColor(int colorWhite) {
        binding.profileText.setTextColor(colorWhite);
        binding.emailText.setTextColor(colorWhite);
        binding.emailInput.setTextColor(colorWhite);
        binding.usernameText.setTextColor(colorWhite);
        binding.usernameInput.setTextColor(colorWhite);
        binding.themeSwitch.setTextColor(colorWhite);
    }
}
