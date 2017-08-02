package org.flauschhaus.traces.journal.entry;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.text.IsEmptyString.isEmptyString;
import static org.junit.Assert.*;

public class JournalEntryBuilderTest {

    @Test
    public void should_build_entry_correctly() {
        JournalEntryBuilder journalEntryBuilder = new JournalEntryBuilder();

        Date date = new Date();
        JournalEntry journalEntry = journalEntryBuilder
                .forDate(date)
                .withText("a very good day")
                .withHighlight("dancing!")
                .rated(9)
                .build();

        assertThat(journalEntry.getDate(), is(equalTo(date)));
        assertThat(journalEntry.getText(), is(equalTo("a very good day")));
        assertThat(journalEntry.getHighlight(), is(equalTo("dancing!")));
        assertThat(journalEntry.getRating(), is(equalTo(9)));
    }

    @Test
    public void should_use_default_values() throws Exception {
        JournalEntryBuilder journalEntryBuilder = new JournalEntryBuilder();

        JournalEntry journalEntry = journalEntryBuilder
                .build();

        assertThat(journalEntry.getDate(), is(notNullValue()));
        assertThat(journalEntry.getText(), isEmptyString());
        assertThat(journalEntry.getHighlight(), isEmptyString());
        assertThat(journalEntry.getRating(), is(equalTo(0)));
    }
}