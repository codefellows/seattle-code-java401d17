package com.reyjroliva.lecture28demo;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
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
public class OrderFormFlowIntegrationTest {

  @Rule
  public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
    new ActivityScenarioRule<>(MainActivity.class);

  @Test
  public void orderFormFlowIntegrationTest() {
    ViewInteraction appCompatEditText = onView(
      allOf(withId(R.id.mainActivityInputEditText),
        childAtPosition(
          childAtPosition(
            withId(android.R.id.content),
            0),
          5),
        isDisplayed()));
    appCompatEditText.perform(replaceText("product"), closeSoftKeyboard());

    ViewInteraction appCompatEditText2 = onView(
      allOf(withId(R.id.mainActivityInputEditText), withText("product"),
        childAtPosition(
          childAtPosition(
            withId(android.R.id.content),
            0),
          5),
        isDisplayed()));
    appCompatEditText2.perform(pressImeActionButton());

    ViewInteraction materialButton = onView(
      allOf(withId(R.id.mainActivityOrderFormButton), withText("Order Form"),
        childAtPosition(
          childAtPosition(
            withId(android.R.id.content),
            0),
          1),
        isDisplayed()));
    materialButton.perform(click());

    ViewInteraction textView = onView(
      allOf(withId(R.id.OrderFormTitleTextView), withText("Order Form"),
        withParent(withParent(withId(android.R.id.content))),
        isDisplayed()));
    textView.check(matches(withText("Order Form")));

    ViewInteraction textView2 = onView(
      allOf(withId(R.id.orderFormActivityOrderFormInfoTextView), withText("product"),
        withParent(withParent(withId(android.R.id.content))),
        isDisplayed()));
    textView2.check(matches(withText("product")));
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
