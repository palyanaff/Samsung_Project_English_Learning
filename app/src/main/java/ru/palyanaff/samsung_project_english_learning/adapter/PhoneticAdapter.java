package ru.palyanaff.samsung_project_english_learning.adapter;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.palyanaff.samsung_project_english_learning.R;
import ru.palyanaff.samsung_project_english_learning.translation.models.Phonetics;

public class PhoneticAdapter extends RecyclerView.Adapter<PhoneticAdapter.PhoneticViewHolder>{

    private final List<Phonetics> phoneticsList;

    public PhoneticAdapter(List<Phonetics> phoneticsList) {
        this.phoneticsList = phoneticsList;
    }

    @NonNull
    @Override
    public PhoneticViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.phonetic_item,parent,false);
        return new PhoneticAdapter.PhoneticViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneticViewHolder holder, int position) {
        holder.textView_translation_phonetic.setText(phoneticsList.get(position).getText());
        holder.imageButton_translation_audio.setOnClickListener(v->{
            MediaPlayer player = new MediaPlayer();
            try {
                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                player.setDataSource("https" + phoneticsList.get(position).getAudio());
                player.prepare();
                player.start();
            } catch (Exception e){
                e.printStackTrace();
                Toast.makeText(v.getContext(),"Couldn't play audio", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return phoneticsList.size();
    }

    class PhoneticViewHolder extends RecyclerView.ViewHolder{
        TextView textView_translation_phonetic;
        ImageButton imageButton_translation_audio;

        public PhoneticViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_translation_phonetic = itemView.findViewById(R.id.textView_translation_phonetic);
            imageButton_translation_audio = itemView.findViewById(R.id.imageButton_translation_audio);

        }
    }
}
