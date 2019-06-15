package com.abdallahapps.g2mdx_task.ui.notes.persenter;

import com.abdallahapps.g2mdx_task.generalUtils.Constants;
import com.abdallahapps.g2mdx_task.model.DataManager;
import com.abdallahapps.g2mdx_task.model.data.dto.Note;
import com.abdallahapps.g2mdx_task.ui.notes.view.NoteView;

public class NotesPersenter {

    NoteView noteView;

    public NotesPersenter(NoteView noteView){
        this.noteView=noteView;
    }


    public void saveNote(Note note){
        if (note!=null){
            if (note.getContent()!=null){
                if (note.getTime() != 0){
                    DataManager.getInstance().insertNote(note);
                    noteView.onSuccess(note);
                    return;
                }
            }
        }
        noteView.onError(Constants.empty_note);
    }


    public void loadAllNotes(){
        noteView.onSuccessLoadNotes( DataManager.getInstance().getAllNotes() );
    }

}
