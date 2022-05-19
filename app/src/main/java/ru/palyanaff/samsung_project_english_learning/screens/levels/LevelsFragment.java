package ru.palyanaff.samsung_project_english_learning.screens.levels;

import android.os.Bundle;
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

        FirebaseAuth auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("Users");

        setUserByUserFromDB();
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
                        Toast.makeText(getContext(),
                                "Failed to get actual data", Toast.LENGTH_LONG).show();
                    }
                });
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_levels, container, false);

        initRecyclerView(view);

        return view;
    }

    /**
     * Initialise RecyclerView
     * @param view
     */
    public void initRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        ItemAdapter itemAdapter = new ItemAdapter(new Datasource().loadLevel(), user);
        recyclerView.setAdapter(itemAdapter);
    }
}