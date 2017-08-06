package org.flauschhaus.traces.util;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.Toolbar;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class ToolbarMatchers {

    public static Matcher<Object> withToolbarTitle(final String expectedTitle) {

        return new BoundedMatcher<Object, Toolbar>(Toolbar.class) {

            @Override
            public boolean matchesSafely(Toolbar toolbar) {
                return toolbar.getTitle().equals(expectedTitle);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("expected: ");
                description.appendText("" + expectedTitle);
            }
        };
    }
}
