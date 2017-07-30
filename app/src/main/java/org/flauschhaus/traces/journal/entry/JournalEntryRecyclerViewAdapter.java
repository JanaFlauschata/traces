package org.flauschhaus.traces.journal.entry;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import org.flauschhaus.traces.R;

/**
 * {@see https://stackoverflow.com/questions/26517855/using-the-recyclerview-with-a-database}
 */
public class JournalEntryRecyclerViewAdapter extends RecyclerView.Adapter<JournalEntryRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private CursorAdapter cursorAdapter;

    //TODO OnListFragmentInteractionListener
    public JournalEntryRecyclerViewAdapter(Context context, Cursor cursor) {
        this.context = context;

        cursor.registerContentObserver(new ChangeObserver());
        cursor.registerDataSetObserver(new MyDataSetObserver());

        cursorAdapter = new JournalEntryCursorAdapter(context, cursor);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = cursorAdapter.newView(context, cursorAdapter.getCursor(), parent);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        cursorAdapter.getCursor().moveToPosition(position);
        cursorAdapter.bindView(holder.itemView, context, cursorAdapter.getCursor());
    }

    @Override
    public int getItemCount() {
        return cursorAdapter.getCount();
    }


    private class ChangeObserver extends ContentObserver {
        public ChangeObserver() {
            super(new Handler());
        }

        @Override
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override
        public void onChange(boolean selfChange) {
            notifyDataSetChanged();
        }
    }

    private class MyDataSetObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            notifyDataSetChanged();
        }

        @Override
        public void onInvalidated() {
            notifyItemRangeRemoved(0, getItemCount());
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View view) {
            super(view);
        }
    }
}
