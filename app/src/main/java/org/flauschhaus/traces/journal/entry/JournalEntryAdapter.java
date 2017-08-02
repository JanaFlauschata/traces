package org.flauschhaus.traces.journal.entry;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.flauschhaus.traces.R;

import java.util.Collections;
import java.util.List;

public class JournalEntryAdapter extends RecyclerView.Adapter<JournalEntryAdapter.JournalEntryViewHolder> {

    private List<JournalEntry> journalEntries = Collections.emptyList();

    @Override
    public JournalEntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_journal, parent, false);
        return new JournalEntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JournalEntryViewHolder holder, int position) {
        JournalEntry journalEntry = journalEntries.get(position);
        holder.textview1.setText(journalEntry.getText());
        holder.textview2.setText(String.valueOf(journalEntry.getRating()));
    }

    @Override
    public int getItemCount() {
        return journalEntries.size();
    }

    public void setJournalEntries(List<JournalEntry> journalEntries) {
        this.journalEntries = journalEntries;
        notifyDataSetChanged();
    }

    class JournalEntryViewHolder extends RecyclerView.ViewHolder{

        TextView textview1;
        TextView textview2;

        public JournalEntryViewHolder(View itemView) {
            super(itemView);
            textview1 = (TextView) itemView.findViewById(R.id.id);
            textview2 = (TextView) itemView.findViewById(R.id.content);
        }
    }
}
