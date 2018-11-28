package com.madlibs.tom.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EntryDatabase db;
    EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the database to keep track of all entry's
        db = EntryDatabase.getInstance(getApplicationContext());
        Cursor c = db.selectAll();
        adapter = new EntryAdapter(getApplicationContext(), c);
        ListView list = findViewById(R.id.listView);

        // set adapter to show content and listeners to act when clicked on an entry
        list.setAdapter(adapter);
        list.setOnItemClickListener(new listItemClickListener());
        list.setOnItemLongClickListener(new longClickListener());
    }

    public void onAddButtonClick(View view) {
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    // get cursor to the clicked entry and pass entry to DetailActivity to show whole entry
    private class listItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            Cursor c = (Cursor) parent.getItemAtPosition(position);
            JournalEntry entry = new JournalEntry(c);
            intent.putExtra("entry", entry);
            startActivity(intent);
        }
    }

    // delete entry if user clicks long on entry
    private class longClickListener implements ListView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            // delete entry
            Cursor c = (Cursor) parent.getItemAtPosition(position);
            long tmp = c.getInt(c.getColumnIndex("_id"));
            db.delete(tmp);

            // display message : entry deleted
            Toast.makeText(getApplicationContext(),"Entry deleted", Toast.LENGTH_SHORT).show();
            updateData();
            return true;
        }
    }

    @Override
    // when coming back you mainActivity update database to display made changes
    protected void onResume() {
        super.onResume();
        updateData();
    }

    // update database to display changes
    private void updateData() {
        Cursor c = db.selectAll();
        adapter.swapCursor(c);
    }

    @Override
    //override back button so user cant go back and edit an submitted entry
    public void onBackPressed() {
        this.finishAffinity();
    }
}
