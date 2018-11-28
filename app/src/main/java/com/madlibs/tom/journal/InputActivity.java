package com.madlibs.tom.journal;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InputActivity extends AppCompatActivity {
    int mood = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    // set mood
    public void onVerySadClick(View view) {
        mood = 0;
        emoteClicked(view);
    }

    // set mood
    public void onSadClick(View view) {
        mood = 1;
        emoteClicked(view);
    }

    // set mood
    public void onHappyClick(View view) {
        mood = 2;
        emoteClicked(view);
    }

    // set mood
    public void onVeryHappyClick(View view) {
        mood = 3;
        emoteClicked(view);
    }

    // display clicked image if image clicked, change all other back to not clicked
    private void emoteClicked(View view){
        // get emote imageViews
        ImageView verySad = findViewById(R.id.imageVerySad);
        ImageView sad = findViewById(R.id.imageSad);
        ImageView happy = findViewById(R.id.imageHappy);
        ImageView veryHappy = findViewById(R.id.imageVeryHappy);

        // set all images back to unclicked
        verySad.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.very_sad));
        sad.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.sad));
        happy.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.happy));
        veryHappy.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.very_happy));

        // display chosen mood as clicked image
        switch (mood) {
            case 0:
                verySad.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.very_sad_clicked));
                break;
            case 1:
                sad.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.sad_clicked));
                break;
            case 2:
                happy.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.happy_clicked));
                break;
            case 3:
                veryHappy.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.very_happy_clicked));
                break;
        }
    }

    // onclick submit, this will get all user input, check if filled in and if so add entry to database and return to MainActivity
    public void addEntry(View view) {
        EditText titleView = (EditText) findViewById(R.id.editTitle);
        EditText contentView = (EditText) findViewById(R.id.editContent);
        String title = titleView.getText().toString();

        // make sure title is not empty
        if (title.length() < 2) {
            Toast.makeText(getApplicationContext(),"Please fill in a title!", Toast.LENGTH_LONG).show();
            return;
        }
        String content = contentView.getText().toString();

        // make sure content is not empty
        if (content.length() < 2) {
            Toast.makeText(getApplicationContext(),"Please fill in a content!", Toast.LENGTH_LONG).show();
            return;
        }

        // make sure mood is selected
        if (mood == -1) {
            Toast.makeText(getApplicationContext(),"Please select your mood!", Toast.LENGTH_LONG).show();
            return;
        }

        // if all is filled in add date and put entry in database
        String time = new SimpleDateFormat("dd/MM/yyyy:hh").format(new Date());
        JournalEntry entry = new JournalEntry(title, content, mood, time);
        EntryDatabase.getInstance(getApplicationContext()).insert(entry);

        // return to MainActivity
        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
