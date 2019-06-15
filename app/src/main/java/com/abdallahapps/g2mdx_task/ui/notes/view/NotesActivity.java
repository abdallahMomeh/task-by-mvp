package com.abdallahapps.g2mdx_task.ui.notes.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.abdallahapps.g2mdx_task.R;
import com.abdallahapps.g2mdx_task.generalUtils.Constants;
import com.abdallahapps.g2mdx_task.model.data.dto.Note;
import com.abdallahapps.g2mdx_task.ui.base.view.BaseActivity;
import com.abdallahapps.g2mdx_task.ui.notes.persenter.NotesPersenter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NotesActivity extends BaseActivity implements NoteView, View.OnClickListener {

    NotesPersenter notesPersenter;
    NotesRVAdapter notesRVAdapter;
    List<Note> noteList;
    Toolbar toolbar;
    EditText mContentNoteET;
    TextView mDateTV,mMessageTV;
    Button mSaveBtn;
    RecyclerView mNotesRV;
    Note note;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        noteList = new ArrayList<>();

        initViews();
        notesPersenter = new NotesPersenter(this);
        notesPersenter.loadAllNotes();

    }

    void initViews(){
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back_white_24);
        setSupportActionBar(toolbar);
        mContentNoteET = findViewById(R.id.mContentNoteET);
        mMessageTV = findViewById(R.id.mMessageTV);
        mDateTV = findViewById(R.id.mDateTV);
        mSaveBtn = findViewById(R.id.mSaveBtn);
        mNotesRV = findViewById(R.id.mNotesRV);

        mSaveBtn.setOnClickListener(this);

        notesRVAdapter = new NotesRVAdapter(noteList);
        mNotesRV.setLayoutManager(new LinearLayoutManager(this));
        mNotesRV.setAdapter(notesRVAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mSaveBtn:{
                note  = new Note();
                note.setContent(mContentNoteET.getText().toString());
                note.setTime(10000000);
                notesPersenter.saveNote(note);
                break;
            }
        }
    }

    @Override
    public void onSuccess() {
    }

    @Override
    public void onError(int type) {

        switch (type){

            case Constants.empty_note:{
                Toast.makeText(this, R.string.ensureFromNote, Toast.LENGTH_SHORT).show();
                break;
            }

        }

    }


    @Override
    public void onSuccess(Note note) {
        mMessageTV.setVisibility(View.GONE);
        noteList.add(note);
        notesRVAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccessLoadNotes(List<Note> notes) {
        if (notes.size()>0)
            mMessageTV.setVisibility(View.GONE);
        noteList.clear();
        noteList.addAll(notes);
        notesRVAdapter.notifyDataSetChanged();
    }
}
