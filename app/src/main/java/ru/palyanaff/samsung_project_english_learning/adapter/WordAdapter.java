package ru.palyanaff.samsung_project_english_learning.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.palyanaff.samsung_project_english_learning.R;
import ru.palyanaff.samsung_project_english_learning.data.Word;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ItemViewHolder> {

    private static final String TAG = "WordAdapter";
    private List<Word> list;

    public WordAdapter(List<Word> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_word,parent,false);
        return new WordAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.wordText.setText(list.get(position).getWordText());
        holder.wordTranslation.setText((list.get(position).getWordTranslation()));
    }

    @Override
    public int getItemCount() {
        return list.size();
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