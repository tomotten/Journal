package com.madlibs.tom.journal;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class EntryDatabase extends SQLiteOpenHelper {

    private static EntryDatabase instance;
    public static final String ID_COL = "_id";
    public static final String TITLE_COL = "title";
    public static final String CONTENT_COL = "content";
    public static final String TIMESTAMP_COL = "timeStamp";
    public static final String MOOD_COL = "mood";


    private EntryDatabase(Context context) {
        super(context, "myJournal.db", null, 1);
    }

    public static EntryDatabase getInstance(Context context){
        // check if instance exist, if not initialise
        if (instance == null) {
            instance = new EntryDatabase(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE myJournal (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, timeStamp TEXT, mood INTEGER);");

        db.execSQL("INSERT INTO myJournal( title, content, timeStamp, mood) VALUES (\"My first entry\", \"Dit is mijn eerste entry, dit is een test.\", \"02/08/1994:08\",3);");
//        db.execSQL("INSERT INTO myJournal( title, content, timeStamp, mood) VALUES (\"test titel222\", \"test context22\", \"02-08-1220\",2);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "myJournal");
        onCreate(db);
    }

    // return cursor to all JournalEntry objects in database
    public Cursor selectAll() {
        return instance.getWritableDatabase().rawQuery("SELECT * FROM myJournal",null);
    }

    public void insert(JournalEntry entry){
        String title = entry.getTitle();
        String content = entry.getContent();
        String timeStamp = entry.getTimeStamp();
        int mood = entry.getMood();
        String query = "INSERT INTO myJournal( title, content, timeStamp, mood) VALUES (\""+ title + "\", \"" + content + "\",\"" + timeStamp + "\"," + mood + ");";
        instance.getWritableDatabase().execSQL(query);
    }

    public void delete(long id) {
        String query = "DELETE from myJournal WHERE _id==" + id;
        instance.getWritableDatabase().execSQL(query);
    }
}
