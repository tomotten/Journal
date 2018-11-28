package com.madlibs.tom.journal;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import static java.security.AccessController.getContext;

public class EntryAdapter extends ResourceCursorAdapter {
    private EntryDatabase entryDatabase;

    public EntryAdapter(Context context, Cursor cursor){
        super(context, R.layout.entry_row, cursor,true);
    }

    @Override
    // show entry in listView as specified in entry_row.xml
    public void bindView(View view, Context context, Cursor cursor) {
        TextView title = view.findViewById(R.id.titleView);
        TextView timeStamp = view.findViewById(R.id.timeStampView);
        ImageView mood = view.findViewById(R.id.moodView);

        // set Views to display info passed by cursor object
        title.setText(cursor.getString(cursor.getColumnIndex("title")));
        timeStamp.setText(cursor.getString(cursor.getColumnIndex("timeStamp")));
        int m = cursor.getInt(cursor.getColumnIndex("mood"));

        int id = 0;
        switch (m) {
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
        mood.setImageDrawable(ContextCompat.getDrawable(context, id));
    }
}
