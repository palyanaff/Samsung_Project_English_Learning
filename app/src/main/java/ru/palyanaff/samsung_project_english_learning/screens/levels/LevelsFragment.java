package ru.palyanaff.samsung_project_english_learning.screens.levels;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.palyanaff.samsung_project_english_learning.R;
import ru.palyanaff.samsung_project_english_learning.data.User;
import ru.palyanaff.samsung_project_english_learning.databinding.FragmentLevelsBinding;
import ru.palyanaff.samsung_project_english_learning.adapter.ItemAdapter;
import ru.palyanaff.samsung_project_english_learning.datasource.Datasource;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class LevelsFragment extends Fragment {
    private static final String TAG = "LevelsFragment";
    FragmentLevelsBinding binding;
    private User user = new User("w", "s");

    public LevelsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentLevelsBinding.inflate(getLayoutInflater());
        // TODO: get user from db
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