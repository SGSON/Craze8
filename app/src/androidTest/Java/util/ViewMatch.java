package util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ViewMatch {
    public static Matcher<View> withListSize (final int size) {
        return new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely (final View view) {
                return ((ListView) view).getCount () == size;
            }

            @Override
            public void describeTo (org.hamcrest.Description description) {
                description.appendText ("ListView should have " + size + " items");
            }
        };
    }

    public static Matcher<View> nthChildOf(final Matcher<View> parentMatcher, final int childPosition) {
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(org.hamcrest.Description description) {
                description.appendText("position " + childPosition + " of parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view.getParent() instanceof ViewGroup)) {
                    return parentMatcher.matches(view.getParent());
                }

                ViewGroup group = (ViewGroup) view.getParent();
                return parentMatcher.matches(view.getParent()) && group.getChildAt(childPosition).equals(view);
            }
        };
    }
}
