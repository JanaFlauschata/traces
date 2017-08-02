package org.flauschhaus.traces.journal.entry;

import java.util.Date;

public class JournalEntryBuilder {

    private Date date;
    private String text = "";
    private String highlight = "";
    private int rating = 0;

    public JournalEntryBuilder forDate(Date date) {
        this.date = date;
        return this;
    }

    public JournalEntryBuilder withText(String text) {
        this.text = text;
        return this;
    }

    public JournalEntryBuilder withHighlight(String highlight) {
        this.highlight = highlight;
        return this;
    }

    public JournalEntryBuilder rated(int rating) {
        this.rating = rating;
        return this;
    }

    public JournalEntry build() {
        JournalEntry journalEntry = new JournalEntry();

        if (date == null) {
            date = new Date();
        }

        journalEntry.setDate(date);
        journalEntry.setText(text);
        journalEntry.setHighlight(highlight);
        journalEntry.setRating(rating);

        return journalEntry;
    }
}
