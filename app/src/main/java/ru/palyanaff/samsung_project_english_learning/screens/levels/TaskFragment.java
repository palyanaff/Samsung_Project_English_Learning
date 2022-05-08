package ru.palyanaff.samsung_project_english_learning.screens.levels;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.palyanaff.samsung_project_english_learning.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskFragment extends Fragment {
    private static final String TAG = "TaskFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //val args : TaskFragmentArgs by navArgs<>()
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String taskHeaderText;
    private String taskTextText;

    public TaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param taskId Parameter 1.
     * @return A new instance of fragment TaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskFragment newInstance(String taskId) {
        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, taskId);
        /*args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);*/
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //taskId = getArguments().getString(ARG_PARAM1);
            /*mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);*/
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task, container, false);

        TextView taskHeader = view.findViewById(R.id.task_header);
        TextView taskText = view.findViewById(R.id.task_text);
        taskHeaderText = TaskFragmentArgs.fromBundle(getArguments()).getTask()[0];
        taskTextText = TaskFragmentArgs.fromBundle(getArguments()).getTask()[1];
        taskHeader.setText(taskHeaderText);
        taskText.setText(taskTextText);

        return view;
    }
}