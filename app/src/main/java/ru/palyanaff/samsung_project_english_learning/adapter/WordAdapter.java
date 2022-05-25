package ru.palyanaff.samsung_project_english_learning.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import ru.palyanaff.samsung_project_english_learning.R;
import ru.palyanaff.samsung_project_english_learning.data.User;
import ru.palyanaff.samsung_project_english_learning.data.Word;
import ru.palyanaff.samsung_project_english_learning.screens.dictionary.WordListFragment;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ItemViewHolder> {

    private static final String TAG = "WordAdapter";

    private FirebaseUser firebaseUser;
    private DatabaseReference usersRef;

    private final Context context;
    private final String header;
    private final List<Word> list;

    public WordAdapter(Context context, String header, List<Word> list) {
        this.context = context;
        this.header = header;
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
        holder.closeButton.setOnClickListener(closeButtonListener(list.get(position)));
    }

    @NonNull
    private View.OnClickListener closeButtonListener(Word word) {
        return (View v) -> {

            if (WordListFragment.isDefaultHeader(header)) {
                Toast.makeText(context, "Impossible to delete words from default dictionaries",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            usersRef = FirebaseDatabase.getInstance().getReference("Users");

            usersRef.child(firebaseUser.getUid())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.getValue(User.class) != null) {

                                User user = new User(snapshot.getValue(User.class));
                                user.deleteWordFromDictionary(header, word);
                                setNewUserToDB(user);
                                Toast.makeText(context, "Word has been successfully deleted!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(context, "Word deleting has been canceled!",
                                    Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Failed to get actual user");
                        }
                    });
        };
    }

    private void setNewUserToDB(User user) {
        usersRef.child(firebaseUser.getUid())
                .setValue(user).addOnCompleteListener(setValueOnComplete());
        notifyDataSetChanged();
    }

    @NonNull
    private OnCompleteListener<Void> setValueOnComplete() {
        return (Task<Void> setValueTask) -> {
            if (!setValueTask.isSuccessful()) {
                Toast.makeText(context, "Failed to update user data!",
                        Toast.LENGTH_LONG).show();
            }
        };
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView wordText;
        TextView wordTranslation;
        ImageButton closeButton;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            wordText = itemView.findViewById(R.id.item_word_text);
            wordTranslation = itemView.findViewById(R.id.item_word_translation);
            closeButton = itemView.findViewById(R.id.close_button);
        }
    }
}
