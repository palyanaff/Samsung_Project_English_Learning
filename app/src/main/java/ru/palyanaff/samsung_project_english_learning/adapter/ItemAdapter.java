package ru.palyanaff.samsung_project_english_learning.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.palyanaff.samsung_project_english_learning.R;
import ru.palyanaff.samsung_project_english_learning.data.Level;
import ru.palyanaff.samsung_project_english_learning.data.User;
import ru.palyanaff.samsung_project_english_learning.screens.levels.LevelsFragmentDirections;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private final List<Level> list;
    private final User user;
    private static final String TAG = "ItemAdapter";

    public ItemAdapter(List<Level> list, @NonNull User user) {
        this.list = list;
        this.user = user;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.level_item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Level currentLevel = list.get(position);

        holder.button.setText(currentLevel.getLevelId());
        holder.textView.setText(currentLevel.getHeader());

        if (user != null) {
            // check if level complete
            if (user.getCompleteLevels().contains(currentLevel.getLevelId())){
                holder.button.setBackgroundTintList(AppCompatResources.getColorStateList(holder.itemView.getContext(), R.color.green));
            }
        }
        holder.button.setOnClickListener(v -> {
            try {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
               @NonNull LevelsFragmentDirections.ActionLevelsFragmentToTaskFragment action
                        = LevelsFragmentDirections.actionLevelsFragmentToTaskFragment(
                               currentLevel.getTaskArr(),
                               currentLevel.getLevelId());

                navController.navigate(action);
            } catch (Exception e){
                Log.e(TAG,e.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
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
