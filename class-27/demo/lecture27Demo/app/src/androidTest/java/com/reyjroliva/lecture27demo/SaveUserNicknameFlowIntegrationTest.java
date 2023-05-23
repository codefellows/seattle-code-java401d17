package com.reyjroliva.lecture27demo;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SaveUserNicknameFlowIntegrationTest {

  @Rule
  public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
    new ActivityScenarioRule<>(MainActivity.class);

  @Test
  public void saveUserNicknameFlowIntegrationTest() {
    ViewInteraction appCompatImageView = onView(
      allOf(withId(R.id.mainActivitySettingsImageView), withContentDescription("settings"),
        childAtPosition(
          childAtPosition(
            withId(android.R.id.content),
            0),
          6),
        isDisplayed()));
    appCompatImageView.perform(click());

    ViewInteraction appCompatEditText = onView(
      allOf(withId(R.id.userProfileActivityNicknameInputEditText),
        childAtPosition(
          allOf(withId(R.id.userProfileActivity),
            childAtPosition(
              withId(android.R.id.content),
              0)),
          1),
        isDisplayed()));
    appCompatEditText.perform(replaceText("rey"), closeSoftKeyboard());

    ViewInteraction materialButton = onView(
      allOf(withId(R.id.userProfileActivitySaveButton), withText("Save"),
        childAtPosition(
          allOf(withId(R.id.userProfileActivity),
            childAtPosition(
              withId(android.R.id.content),
              0)),
          2),
        isDisplayed()));
    materialButton.perform(click());

    pressBack();

    ViewInteraction textView = onView(
      allOf(withId(R.id.mainActivityNicknameTextView), withText("rey"),
        withParent(withParent(withId(android.R.id.content))),
        isDisplayed()));
    textView.check(matches(withText("rey")));
  }

  private static Matcher<View> childAtPosition(
    final Matcher<View> parentMatcher, final int position) {

    return new TypeSafeMatcher<View>() {
      @Override
      public void describeTo(Description description) {
        description.appendText("Child at position " + position + " in parent ");
        parentMatcher.describeTo(description);
      }

      @Override
      public boolean matchesSafely(View view) {
        ViewParent parent = view.getParent();
        return parent instanceof ViewGroup && parentMatcher.matches(parent)
          && view.equals(((ViewGroup) parent).getChildAt(position));
      }
    };
  }
}
