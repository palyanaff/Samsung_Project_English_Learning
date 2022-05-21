package ru.palyanaff.samsung_project_english_learning.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.palyanaff.samsung_project_english_learning.R;
import ru.palyanaff.samsung_project_english_learning.translation.models.Meanings;

public class MeaningAdapter extends RecyclerView.Adapter<MeaningAdapter.MeaningViewHolder> {

    private final List<Meanings> meaningsList;
    private Context context;

    public MeaningAdapter(Context context, List<Meanings> meaningsList) {
        this.meaningsList = meaningsList;
        this.context = context;
    }

    @NonNull
    @Override
    public MeaningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meaning_item,parent,false);
        return new MeaningAdapter.MeaningViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return meaningsList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MeaningViewHolder holder, int position) {
        holder.textView_translation_partOfSpeech.setText("Part of speech: " + meaningsList.get(position).getPartOfSpeech());
        holder.recycler_view_definition_translation.setHasFixedSize(true);
        holder.recycler_view_definition_translation.setLayoutManager(new GridLayoutManager(context, 1));
        DefinitionAdapter definitionAdapter = new DefinitionAdapter(context, meaningsList.get(position).getDefinitions());
        holder.recycler_view_definition_translation.setAdapter(definitionAdapter);
    }

    class MeaningViewHolder extends RecyclerView.ViewHolder {

        TextView textView_translation_partOfSpeech;
        RecyclerView recycler_view_definition_translation;

        public MeaningViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_translation_partOfSpeech = itemView.findViewById(R.id.textView_translation_partOfSpeech);
            recycler_view_definition_translation = itemView.findViewById(R.id.recycler_view_definition_translation);
        }
    }
}
