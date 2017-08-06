package org.flauschhaus.traces.journal;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.flauschhaus.traces.MainActivity;
import org.flauschhaus.traces.R;
import org.flauschhaus.traces.journal.entry.JournalEntry;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.flauschhaus.traces.util.CustomScrollActions.nestedScrollTo;
import static org.flauschhaus.traces.util.RecyclerViewAssertions.hasItemsCount;
import static org.flauschhaus.traces.util.RecyclerViewMatcher.withRecyclerView;
import static org.flauschhaus.traces.util.SeekBarMatchers.withProgress;
import static org.flauschhaus.traces.util.SeekBarViewActions.seek;
import static org.flauschhaus.traces.util.ToolbarMatchers.withToolbarTitle;

@RunWith(AndroidJUnit4.class)
public class JournalFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> journalEntryCRUDActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private Date date;

    @Before
    public void setUp() {
        date = new Date();
    }

    @Test
    public void create_new_entry() {
        clickButtonToCreateNewEntry();

        checkDisplayedDateIs(JournalEntry.convert(date));
        checkOriginalEntryTextIs("");
        checkOriginalEntryHighlightIs("");
        checkOriginalRatingIs(0);

        typeEntryText("a very good day");
        typeEntryHighlight("dancing!");
        rateEntry(7);

        saveOrUpdate();

        checkEntriesCount(1);
        checkFirstEntryHasId("a very good day");
        checkFirstEntryHasContent("7");
    }

    @Test
    public void update_entry() {
        clickFirstEntry();

        checkDisplayedDateIs(JournalEntry.convert(date));
        checkOriginalEntryTextIs("a very good day");
        checkOriginalEntryHighlightIs("dancing!");
        checkOriginalRatingIs(7);

        typeEntryText(" today");
        rateEntry(9);

        saveOrUpdate();

        checkEntriesCount(1);
        checkFirstEntryHasId("a very good day today");
        checkFirstEntryHasContent("9");
    }

    @Test
    public void delete_entry() {
        clickFirstEntry();
        deleteEntry();
        checkEntriesCount(0);
    }

    private void deleteEntry() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText(R.string.action_crud_delete)).perform(click());
        onView(withText(R.string.ok)).perform(click());
    }

    private void clickFirstEntry() {
        onView(withId(R.id.list)).perform(actionOnItemAtPosition(0, click()));
    }

    private void saveOrUpdate() {
        onView(withId(R.id.button_journal_entry_save)).perform(closeSoftKeyboard(), nestedScrollTo(), click());
    }

    private void typeEntryText(String text) {
        onView(withId(R.id.edit_journal_entry_text)).perform(typeText(text));
    }

    private void typeEntryHighlight(String highlight) {
        onView(withId(R.id.edit_journal_entry_highlight)).perform(closeSoftKeyboard(), typeText(highlight));
    }

    private void rateEntry(int rating) {
        onView(withId(R.id.seek_journal_entry_rating)).perform(seek(rating));
    }

    private void clickButtonToCreateNewEntry() {
        onView(withId(R.id.fab)).perform(click());
    }

    private void checkEntriesCount(int count) {
        onView(withId(R.id.list)).check(hasItemsCount(count));
    }

    private void checkFirstEntryHasId(String id) {
        onView(withRecyclerView(R.id.list).atPositionOnView(0, R.id.id)).check(matches(withText(id)));
    }

    private void checkFirstEntryHasContent(String content) {
        onView(withRecyclerView(R.id.list).atPositionOnView(0, R.id.content)).check(matches(withText(content)));
    }

    private void checkOriginalEntryTextIs(String text) {
        onView(withId(R.id.edit_journal_entry_text)).check(matches(withText(text)));
    }

    private void checkOriginalEntryHighlightIs(String highlight) {
        onView(withId(R.id.edit_journal_entry_highlight)).check(matches(withText(highlight)));
    }

    private void checkOriginalRatingIs(int rating) {
        onView(withId(R.id.seek_journal_entry_rating)).check(matches(withProgress(rating)));
    }

    private void checkDisplayedDateIs(String formattedDate) {
        onView(withId(R.id.toolbar)).check(matches(withToolbarTitle(formattedDate)));
    }
}