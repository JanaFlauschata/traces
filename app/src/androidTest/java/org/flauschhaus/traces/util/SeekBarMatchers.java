package org.flauschhaus.traces.util;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.SeekBar;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class SeekBarMatchers {

    public static Matcher<View> withProgress(final int expectedProgress) {

        return new BoundedMatcher<View, SeekBar>(SeekBar.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("expected: ");
                description.appendText("" + expectedProgress);
            }

            @Override
            public boolean matchesSafely(SeekBar seekBar) {
                return seekBar.getProgress() == expectedProgress;
            }
        };
    }
}
