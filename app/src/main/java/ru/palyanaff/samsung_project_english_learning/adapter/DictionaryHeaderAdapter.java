package ru.palyanaff.samsung_project_english_learning.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import ru.palyanaff.samsung_project_english_learning.R;
import ru.palyanaff.samsung_project_english_learning.screens.dictionary.DictionaryFragmentDirections;

public class DictionaryHeaderAdapter extends RecyclerView.Adapter<DictionaryHeaderAdapter.ItemViewHolder>{
    private static final String TAG = "DictionaryHeaderAdapter";
    ArrayList<String> arrayList;

    public DictionaryHeaderAdapter (ArrayList<String> arrayList){
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dictionary_header_item,parent,false);
        return new DictionaryHeaderAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.dictionaryHeader.setText(arrayList.get(position));
        holder.cardView.setOnClickListener(v -> {
            try {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
                @NonNull DictionaryFragmentDirections.ActionDictionaryFragmentToWordListFragment action
                        = DictionaryFragmentDirections.actionDictionaryFragmentToWordListFragment(arrayList.get(position));
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

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView dictionaryHeader;
        CardView cardView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.dictionary_card_view);
            dictionaryHeader = itemView.findViewById(R.id.dictionary_header_text);
        }
    }
}