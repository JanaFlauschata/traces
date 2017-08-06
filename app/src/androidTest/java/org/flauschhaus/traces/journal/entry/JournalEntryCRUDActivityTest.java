package org.flauschhaus.traces.journal.entry;

import android.content.Context;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.flauschhaus.traces.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.pressBackUnconditionally;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.flauschhaus.traces.util.CustomScrollActions.nestedScrollTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class JournalEntryCRUDActivityTest {

    @Rule
    public ActivityTestRule<JournalEntryCRUDActivity> journalEntryCRUDActivityActivityTestRule = new ActivityTestRule<>(JournalEntryCRUDActivity.class);

    @Test
    public void click_on_button_finishes_activity(){
        onView(withId(R.id.button_journal_entry_save))
                .perform(nestedScrollTo(), click());

        assertThat(journalEntryCRUDActivityActivityTestRule.getActivity().isFinishing(),is(true));
    }

    @Test
    public void click_on_back_button_finishes_activity(){
        pressBackUnconditionally();

        assertThat(journalEntryCRUDActivityActivityTestRule.getActivity().isFinishing(),is(true));
    }

    @Test
    public void shows_delete_action_item_when_updating_an_entry(){
        Context targetContext = getInstrumentation().getTargetContext();
        Intent intent = new Intent(targetContext, JournalEntryCRUDActivity.class);
        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setDate(new Date());
        intent.putExtra(JournalEntry.class.getName(), journalEntry);

        journalEntryCRUDActivityActivityTestRule.launchActivity(intent);

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText(R.string.action_crud_delete))
                .check(matches(isDisplayed()));
    }

    @Test
    public void does_not_show_any_menu_items_when_creating_an_entry(){
        onView(withContentDescription("More options"))
                .check(doesNotExist());
    }

}