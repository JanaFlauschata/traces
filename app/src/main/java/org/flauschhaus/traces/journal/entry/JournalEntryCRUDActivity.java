package org.flauschhaus.traces.journal.entry;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import org.flauschhaus.traces.R;
import org.flauschhaus.traces.TracesApplication;

import java.util.Date;

public class JournalEntryCRUDActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_entry_crud);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button buttonSave = (Button) findViewById(R.id.button_journal_entry_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textView = (EditText) findViewById(R.id.edit_journal_entry_text);
                EditText highlightView = (EditText) findViewById(R.id.edit_journal_entry_highlight);
                SeekBar seekBar = (SeekBar) findViewById(R.id.seek_journal_entry_rating);

                JournalEntry journalEntry = new JournalEntryBuilder()
                        .forDate(new Date())
                        .withText(textView.getText().toString())
                        .withHighlight(highlightView.getText().toString())
                        .rated(seekBar.getProgress())
                        .build();

                JournalEntryDao journalEntryDao = ((TracesApplication) getApplication()).getDaoSession().getJournalEntryDao();
                journalEntryDao.save(journalEntry);

                JournalEntryCRUDActivity.this.finish();
            }
        });

    }

}
