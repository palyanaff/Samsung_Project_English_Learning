package ru.palyanaff.samsung_project_english_learning.screens.levels;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ru.palyanaff.samsung_project_english_learning.R;
import ru.palyanaff.samsung_project_english_learning.adapter.ItemAdapter;
import ru.palyanaff.samsung_project_english_learning.authentication.LoginActivity;
import ru.palyanaff.samsung_project_english_learning.data.User;
import ru.palyanaff.samsung_project_english_learning.databinding.FragmentLevelsBinding;
import ru.palyanaff.samsung_project_english_learning.datasource.Datasource;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class LevelsFragment extends Fragment {
    private static final String TAG = "LevelsFragment";
    private FragmentLevelsBinding binding;
    private ItemAdapter itemAdapter;

    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference usersRef;

    private User user;

    public LevelsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentLevelsBinding.inflate(getLayoutInflater());

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("Users");
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_levels, container, false);

        workWithUser(view);

        return view;
    }
    
    public void initRecyclerView(View view, User user) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        itemAdapter = new ItemAdapter(new Datasource(getContext()).loadLevel(), user);
        recyclerView.setAdapter(itemAdapter);
        itemAdapter.notifyDataSetChanged();
    }

    private void workWithUser(View view) {

        usersRef.child(firebaseUser.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.getValue(User.class) == null) {
                            mAuth.signOut();
                            Toast.makeText(LevelsFragment.this.getActivity(),
                                    "Successfully logged out", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(
                                    LevelsFragment.this.getActivity(), LoginActivity.class));
                            LevelsFragment.this.getActivity().finish();

                        } else {
                            User newUser = new User(snapshot.getValue(User.class));
                            initRecyclerView(view, newUser);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e(TAG, "Failed to get current user data", error.toException());
                    }
                });
    }
}
