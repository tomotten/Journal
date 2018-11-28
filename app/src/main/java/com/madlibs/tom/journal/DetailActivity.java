package com.madlibs.tom.journal;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // get clicked entry from intent
        Intent intent = getIntent();
        JournalEntry entry = (JournalEntry) intent.getSerializableExtra("entry");

        // display corresponding titel
        String title = entry.getTitle();
        TextView titleView = findViewById(R.id.titleField);
        titleView.setText(title);

        // display corresponding content
        String content = entry.getContent();
        TextView contentView = findViewById(R.id.contentField);
        contentView.setText(content);

        // display corresponding timestamp
        String time = entry.getTimeStamp();
        TextView timeView = findViewById(R.id.timeStampField);
        timeView.setText(time);

        // display corresponding emote for mood
        int mood = entry.getMood();
        ImageView moodEmote = findViewById(R.id.moodImage);
        int id = 0;
        switch (mood) {
            case 0:
                id = R.drawable.very_sad;
                break;
            case 1:
                id = R.drawable.sad;
                break;
            case 2:
                id = R.drawable.happy;
                break;
            case 3:
                id = R.drawable.very_happy;
                break;
        }
        moodEmote.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), id));
    }
}
