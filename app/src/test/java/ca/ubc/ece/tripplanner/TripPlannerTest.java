package ca.ubc.ece.tripplanner;

import android.content.Intent;
import android.support.test.filters.LargeTest;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TripPlannerTest {

    @Rule
    public ActivityTestRule<ParentActivity> parentActivitiyHandle =
            new ActivityTestRule(ParentActivity.class);

    @Rule
    public ActivityTestRule<MapActivity> mapActivitiyHandle =
            new ActivityTestRule(ParentActivity.class);

    @Rule
    public ActivityTestRule<AccountActivity> accountActivityHandle =
            new ActivityTestRule(AccountActivity.class);

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityHandle =
            new ActivityTestRule(LoginActivity.class);

    @Test
    public void testStartingScreen() {
        onView(withId(R.id.dest_list)).check(matches(isDisplayed()));
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
    }

    @Test
    public void testToolbarButtons() {
        onView(withId(R.id.action_route_map)).perform(click());
        onView(withId(R.id.map)).check(matches(isDisplayed()));

        onView(withId(R.id.action_preferences_list)).perform(click());
        onView(withId(R.id.pref_chart)).check(matches(isDisplayed()));

        onView(withId(R.id.action_friends_list)).perform(click());
        onView(withId(R.id.friends_list)).check(matches(isDisplayed()));

        onView(withId(R.id.action_account_profile)).perform(click());
        onView(withId(R.id.login)).check(matches(isDisplayed()));
    }

    @Test
    public void testAddDestination() {
        onView(withId(R.id.new_dest_field)).perform(typeText("Stanley Park"));
        onView(withId(R.id.new_dest_ok_button)).perform(click());

        onData(allOf(is(instanceOf(String.class)), is("Stanley Park"))).perform(scrollTo()).check(matches(isDisplayed()));
    }

    @Test
    public void testAddPreference() {
        accountActivityHandle.launchActivity(new Intent());

        onView(withId(R.id.new_pref_field)).perform(typeText("Arts"));
        onView(withId(R.id.new_pref_ok_button)).perform(click());

        onData(allOf(is(instanceOf(String.class)), is("Arts"))).perform(scrollTo()).check(matches(isDisplayed()));
    }

    @Test
    public void testLogin() {
        loginActivityHandle.launchActivity(new  Intent());

        onView(withId(R.id.email_login_form)).perform(typeText("foo@example.com"));
        onView(withId(R.id.password)).perform(typeText("hello"));
        onView(withId(R.id.email_sign_in_button)).perform(click());

        onView(withId(R.id.account_name)).check(isDisplayed(), withText("foo"));
    }
}