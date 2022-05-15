package ru.palyanaff.samsung_project_english_learning.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import ru.palyanaff.samsung_project_english_learning.R;
import ru.palyanaff.samsung_project_english_learning.screens.dictionary.data.Word;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ItemViewHolder> {

    private static final String TAG = "WordAdapter";
    private ArrayList<Word> arrayList;

    public WordAdapter(ArrayList<Word> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_word,parent,false);
        return new WordAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.wordText.setText(arrayList.get(position).getWordText());
        holder.wordTranslation.setText((arrayList.get(position).getWordTranslation()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView wordText;
        TextView wordTranslation;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            wordText = itemView.findViewById(R.id.item_word_text);
            wordTranslation = itemView.findViewById(R.id.item_word_translation);
        }
    }
}