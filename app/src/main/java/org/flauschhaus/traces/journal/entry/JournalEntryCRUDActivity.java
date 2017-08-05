package org.flauschhaus.traces.journal.entry;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.flauschhaus.traces.R;
import org.flauschhaus.traces.TracesApplication;
import org.flauschhaus.traces.databinding.ActivityJournalEntryCrudBinding;

import java.util.Date;

public class JournalEntryCRUDActivity extends AppCompatActivity {

    private JournalEntryDao journalEntryDao;
    private ActivityJournalEntryCrudBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_journal_entry_crud);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        journalEntryDao = ((TracesApplication) getApplication()).getDaoSession().getJournalEntryDao();

        JournalEntry journalEntry;
        if (getIntent().hasExtra(JournalEntry.class.getName())) {
            journalEntry = (JournalEntry) getIntent().getSerializableExtra(JournalEntry.class.getName());
        }
        else {
            journalEntry = new JournalEntry();
            journalEntry.setDate(new Date());
        }
        binding.setEntry(journalEntry);

        Button buttonSave = (Button) findViewById(R.id.button_journal_entry_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                journalEntryDao.save(binding.getEntry());
                JournalEntryCRUDActivity.this.finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_journal_entry_crud, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_crud_delete) {
            deleteExistingEntry();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteExistingEntry() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.dialog_crud_delete_message)
                .setTitle(R.string.dialog_crud_delete_title);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                journalEntryDao.delete(binding.getEntry());
                JournalEntryCRUDActivity.this.finish();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
