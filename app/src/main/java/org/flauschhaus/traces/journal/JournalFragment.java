package org.flauschhaus.traces.journal;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.flauschhaus.traces.R;
import org.flauschhaus.traces.journal.entry.JournalEntryRecyclerViewAdapter;
import org.flauschhaus.traces.journal.entry.JournalEntryOpenHelper;

public class JournalFragment extends Fragment {

    private JournalEntryOpenHelper journalEntryOpenHelper;
    private Cursor cursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_journal_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            cursor = journalEntryOpenHelper.query();
            recyclerView.setAdapter(new JournalEntryRecyclerViewAdapter(context, cursor));
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        journalEntryOpenHelper = new JournalEntryOpenHelper(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        cursor.requery();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        cursor.close();
        journalEntryOpenHelper.close();
    }
}
