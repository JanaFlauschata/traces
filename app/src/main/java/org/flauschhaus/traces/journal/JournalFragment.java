package org.flauschhaus.traces.journal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.flauschhaus.traces.R;
import org.flauschhaus.traces.TracesApplication;
import org.flauschhaus.traces.journal.entry.JournalEntry;
import org.flauschhaus.traces.journal.entry.JournalEntryAdapter;
import org.flauschhaus.traces.journal.entry.JournalEntryCRUDActivity;
import org.flauschhaus.traces.journal.entry.JournalEntryDao;
import org.greenrobot.greendao.query.Query;

public class JournalFragment extends Fragment implements JournalEntryAdapter.OnListFragmentInteractionListener {

    private JournalEntryAdapter journalEntryAdapter;
    private Query<JournalEntry> journalEntryQuery;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_journal_list, container, false);
        Context context = recyclerView.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        journalEntryAdapter = new JournalEntryAdapter();
        journalEntryAdapter.setListener(this);
        recyclerView.setAdapter(journalEntryAdapter);

        JournalEntryDao journalEntryDao = ((TracesApplication) getActivity().getApplication()).getDaoSession().getJournalEntryDao();
        journalEntryQuery = journalEntryDao.queryBuilder().orderDesc(JournalEntryDao.Properties.Date).build();

        updateEntries();

        return recyclerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateEntries();
    }

    private void updateEntries() {
        journalEntryAdapter.setJournalEntries(journalEntryQuery.list());
    }

    @Override
    public void onListFragmentInteraction(JournalEntry journalEntry) {
        Intent intent = new Intent(getContext(), JournalEntryCRUDActivity.class);
        intent.putExtra(JournalEntry.class.getName(), journalEntry);
        startActivity(intent);
    }
}
