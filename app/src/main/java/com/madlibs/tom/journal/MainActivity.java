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
        db = EntryDatabase.getInstance(getApplicationContext());
        Cursor c = db.selectAll();

        adapter = new EntryAdapter(getApplicationContext(), c);
        ListView list = findViewById(R.id.listView);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new listItemClickListener());
        list.setOnItemLongClickListener(new longClickListener());
    }

    public void onAddButtonClick(View view) {
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    private class listItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            Cursor c = (Cursor) parent.getItemAtPosition(position); // cursor to the clicked entry
            JournalEntry entry = new JournalEntry(c);
            intent.putExtra("entry", entry);
            startActivity(intent);
        }
    }

    private class longClickListener implements ListView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor c = (Cursor) parent.getItemAtPosition(position);
            long tmp = c.getInt(c.getColumnIndex("_id"));
            db.delete(tmp);
            updateData();
            return true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateData();
    }

    private void updateData() {
        Cursor c = db.selectAll();
        adapter.swapCursor(c);
    }

    public void onBackPressed() {
        finish();
    }
}
