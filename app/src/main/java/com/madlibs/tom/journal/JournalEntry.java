package com.madlibs.tom.journal;

import android.database.Cursor;

import java.io.Serializable;

public class JournalEntry implements Serializable {
    private int id;
    private String title;
    private String content;
    private int mood;
    private String timeStamp;

    // constructor to build entry from cursor object
    public JournalEntry(Cursor c) {
        this.title = c.getString(c.getColumnIndex("title"));
        this.timeStamp = c.getString(c.getColumnIndex("timeStamp"));
        this.content = c.getString(c.getColumnIndex("content"));
        this.mood = c.getInt(c.getColumnIndex("mood"));
        this.id = c.getInt(c.getColumnIndex("_id"));
    }

    // constructor to build entry
    public JournalEntry(String title, String content, int mood, String timeStamp){
        this.title = title;
        this.content = content;
        this.mood = mood;
        this.timeStamp = timeStamp;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public int getMood() {
        return mood;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
