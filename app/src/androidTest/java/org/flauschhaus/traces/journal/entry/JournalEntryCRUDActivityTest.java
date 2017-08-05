package org.flauschhaus.traces.journal.entry;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.flauschhaus.traces.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.flauschhaus.traces.journal.entry.CustomScrollActions.nestedScrollTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class JournalEntryCRUDActivityTest {

    @Rule
    public ActivityTestRule<JournalEntryCRUDActivity> journalEntryCRUDActivityActivityTestRule = new ActivityTestRule<JournalEntryCRUDActivity>(JournalEntryCRUDActivity.class);

    @Test
    public void click_on_button_finishes_activity(){

        onView(withId(R.id.button_journal_entry_save))
                .perform(nestedScrollTo(), click());

        assertThat(journalEntryCRUDActivityActivityTestRule.getActivity().isFinishing(),is(true));
    }

}