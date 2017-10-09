package com.example.admin.weekend6assigment;

import com.orm.SugarRecord;

/**
 * Created by Admin on 10/9/2017.
 */

public class Note extends SugarRecord {

    String Title;
    String Note;
    public Note() {
    }

    public Note(String title, String note) {
        Title = title;
        Note = note;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

}
