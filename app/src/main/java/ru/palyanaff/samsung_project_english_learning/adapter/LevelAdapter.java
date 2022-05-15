<<<<<<< Updated upstream:app/src/main/java/ru/palyanaff/samsung_project_english_learning/screens/levels/adapter/ItemAdapter.java
package ru.palyanaff.samsung_project_english_learning.screens.levels.adapter;
=======
package ru.palyanaff.samsung_project_english_learning.adapter;
>>>>>>> Stashed changes:app/src/main/java/ru/palyanaff/samsung_project_english_learning/adapter/LevelAdapter.java

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


import ru.palyanaff.samsung_project_english_learning.R;
import ru.palyanaff.samsung_project_english_learning.screens.levels.data.Level;

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.ItemViewHolder> {

    private static final String TAG = "LevelAdapter";
    private ArrayList<Level> arrayList;

    public LevelAdapter(ArrayList<Level> arrayList) {
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
<<<<<<< Updated upstream:app/src/main/java/ru/palyanaff/samsung_project_english_learning/screens/levels/adapter/ItemAdapter.java
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: switch to next fragment with level content
                Toast.makeText(v.getContext(), arrayList.get(position).getHeader().toString(), Toast.LENGTH_SHORT).show();
=======
        holder.button.setOnClickListener(v -> {
            try {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
               @NonNull LevelsFragmentDirections.ActionLevelsFragmentToTaskFragment action
                        = LevelsFragmentDirections.actionLevelsFragmentToTaskFragment(arrayList.get(position).getTaskArr());
                navController.navigate(action);
            } catch (Exception e){
                Log.e(TAG,e.getMessage());
>>>>>>> Stashed changes:app/src/main/java/ru/palyanaff/samsung_project_english_learning/adapter/LevelAdapter.java
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
