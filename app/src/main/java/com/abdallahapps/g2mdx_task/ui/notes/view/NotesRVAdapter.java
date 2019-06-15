package com.abdallahapps.g2mdx_task.ui.notes.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abdallahapps.g2mdx_task.R;
import com.abdallahapps.g2mdx_task.model.data.dto.Note;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotesRVAdapter extends RecyclerView.Adapter<NotesRVAdapter.ViewHolder> {

    List<Note> noteList;
    public NotesRVAdapter(List<Note> noteList){
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mNoteContentTV.setText(""+noteList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView mNoteContentTV,mDateTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mDateTV = (TextView) itemView.findViewById(R.id.mDateTV);
            mNoteContentTV = (TextView) itemView.findViewById(R.id.mNoteContentTV);
        }
    }

}
