package ru.palyanaff.samsung_project_english_learning.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import ru.palyanaff.samsung_project_english_learning.R;
import ru.palyanaff.samsung_project_english_learning.translation.models.Definitions;

public class DefinitionAdapter extends RecyclerView.Adapter<DefinitionAdapter.DefinitionViewHolder> {

    private final List<Definitions> definitionsList;
    private final Context context;

    public DefinitionAdapter(Context context, List<Definitions> definitionsList) {
        this.definitionsList = definitionsList;
        this.context = context;
    }

    @NonNull
    @Override
    public DefinitionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.definition_item,parent,false);
        return new DefinitionAdapter.DefinitionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DefinitionViewHolder holder, int position) {
        holder.textView_translation_definition.setText("Definitions: " + definitionsList.get(position).getDefinition());
        holder.textView_translation_example.setText("Example: " + definitionsList.get(position).getExample());

        StringBuilder synonyms = new StringBuilder();
        StringBuilder antonyms = new StringBuilder();

        synonyms.append(definitionsList.get(position).getSynonyms());
        antonyms.append(definitionsList.get(position).getAntonyms());

        holder.textView_translation_antonyms.setText(antonyms);
        holder.textView_translation_synonyms.setText(synonyms);

        holder.textView_translation_synonyms.setSelected(true);
        holder.textView_translation_antonyms.setSelected(true);


    }

    @Override
    public int getItemCount() {
        return definitionsList.size();
    }

    class DefinitionViewHolder extends RecyclerView.ViewHolder {

        TextView textView_translation_definition,
                textView_translation_example,
                textView_translation_synonyms,
                textView_translation_antonyms;

        public DefinitionViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_translation_definition = itemView.findViewById(R.id.textView_translation_definition);
            textView_translation_example = itemView.findViewById(R.id.textView_translation_example);
            textView_translation_synonyms = itemView.findViewById(R.id.textView_translation_synonyms);
            textView_translation_antonyms = itemView.findViewById(R.id.textView_translation_antonyms);
        }
    }
}
