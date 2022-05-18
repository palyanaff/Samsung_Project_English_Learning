package ru.palyanaff.samsung_project_english_learning.adapter;

import static android.widget.Toast.LENGTH_LONG;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


import ru.palyanaff.samsung_project_english_learning.R;
import ru.palyanaff.samsung_project_english_learning.screens.levels.LevelsFragmentDirections;
import ru.palyanaff.samsung_project_english_learning.data.Level;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private ArrayList<Level> arrayList;
    private static final String TAG = "ItemAdapter";

    public ItemAdapter(ArrayList<Level> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.level_item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.button.setText(arrayList.get(position).getLevelId());
        holder.textView.setText(arrayList.get(position).getHeader());
        // TODO: set background color if complete
        holder.button.setOnClickListener(v -> {
            try {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
               @NonNull LevelsFragmentDirections.ActionLevelsFragmentToTaskFragment action
                        = LevelsFragmentDirections.actionLevelsFragmentToTaskFragment(arrayList.get(position).getTaskArr());
                navController.navigate(action);
            } catch (Exception e){
                Log.e(TAG,e.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        Button button;
        TextView textView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.button_level_item);
            textView = itemView.findViewById(R.id.textView_level_item);
        }
    }
}
