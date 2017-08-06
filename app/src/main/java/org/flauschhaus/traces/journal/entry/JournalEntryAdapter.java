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

        holder.date.setText(JournalEntry.convert(journalEntry.getDate()));
        holder.rating.setText(String.valueOf(journalEntry.getRating()));
        holder.text.setText(journalEntry.getText());
        holder.highlight.setText(journalEntry.getHighlight());
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
        TextView date;
        TextView rating;
        TextView text;
        TextView highlight;

        public JournalEntryViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.card_date);
            rating = (TextView) itemView.findViewById(R.id.card_rating);
            text = (TextView) itemView.findViewById(R.id.card_text);
            highlight = (TextView) itemView.findViewById(R.id.card_highlight);
        }
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(JournalEntry journalEntry);
    }
}
