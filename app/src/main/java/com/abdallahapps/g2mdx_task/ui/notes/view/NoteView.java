package com.abdallahapps.g2mdx_task.ui.notes.view;

import com.abdallahapps.g2mdx_task.model.data.dto.Note;
import com.abdallahapps.g2mdx_task.ui.base.view.BaseView;

import java.util.List;

public interface NoteView extends BaseView {


    void onSuccess(Note note);
    void onSuccessLoadNotes(List<Note> notes);

}
