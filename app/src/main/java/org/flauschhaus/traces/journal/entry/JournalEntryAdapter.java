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

    private OnListFragmentInteractionListener listener;
    private List<JournalEntry> journalEntries = Collections.emptyList();

    @Override
    public JournalEntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_journal, parent, false);
        return new JournalEntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final JournalEntryViewHolder holder, int position) {
        JournalEntry journalEntry = journalEntries.get(position);
        holder.textview1.setText(journalEntry.getText());
        holder.textview2.setText(String.valueOf(journalEntry.getRating()));
        holder.journalEntry = journalEntry;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onListFragmentInteraction(holder.journalEntry);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return journalEntries.size();
    }

    public void setJournalEntries(List<JournalEntry> journalEntries) {
        this.journalEntries = journalEntries;
        notifyDataSetChanged();
    }

    public void setListener(OnListFragmentInteractionListener listener) {
        this.listener = listener;
    }

    class JournalEntryViewHolder extends RecyclerView.ViewHolder{

        JournalEntry journalEntry;
        TextView textview1;
        TextView textview2;

        public JournalEntryViewHolder(View itemView) {
            super(itemView);
            textview1 = (TextView) itemView.findViewById(R.id.id);
            textview2 = (TextView) itemView.findViewById(R.id.content);
        }
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(JournalEntry journalEntry);
    }
}
