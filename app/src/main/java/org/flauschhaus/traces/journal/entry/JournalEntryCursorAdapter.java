package org.flauschhaus.traces.journal.entry;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import org.flauschhaus.traces.R;

public class JournalEntryCursorAdapter extends CursorAdapter {

    public JournalEntryCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflator = LayoutInflater.from(context);
        return inflator.inflate(R.layout.fragment_journal, null);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        int columnIndexText = cursor.getColumnIndex(JournalEntryOpenHelper.TEXT);
        int columnIndexRating = cursor.getColumnIndex(JournalEntryOpenHelper.RATING);

        TextView textview1 = (TextView) view.findViewById(R.id.id);
        TextView textview2 = (TextView) view.findViewById(R.id.content);

        textview1.setText(cursor.getString(columnIndexText));
        textview2.setText(String.valueOf(cursor.getInt(columnIndexRating)));
    }
}
