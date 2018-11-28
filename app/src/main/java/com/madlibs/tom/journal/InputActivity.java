package com.madlibs.tom.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InputActivity extends AppCompatActivity {
    int mood = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    public void onVerySadClick(View view) {
        mood = 0;
    }
    public void onSadClick(View view) {
        mood = 1;
    }
    public void onHappyClick(View view) {
        mood = 2;
    }
    public void onVeryHappyClick(View view) {
        mood = 3;
    }


    public void addEntry(View view) {
        EditText tit = (EditText) findViewById(R.id.editTitle);
        EditText cont = (EditText) findViewById(R.id.editContent);
        String time = new SimpleDateFormat("dd/MM/yyyy:hh").format(new Date());
        String title = tit.getText().toString();
        String content = cont.getText().toString();
        JournalEntry entry = new JournalEntry(title, content, mood, time);
        EntryDatabase.getInstance(getApplicationContext()).insert(entry);
        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
