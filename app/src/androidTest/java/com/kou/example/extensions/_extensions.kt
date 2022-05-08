package com.kou.example.extensions

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId

fun @receiver:IdRes Int.onView(): ViewInteraction = onView(withId(this))
fun ViewInteraction.isVisible(): ViewInteraction = this.check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
fun ViewInteraction.isGone(): ViewInteraction = this.check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
fun ViewInteraction.isInvisible(): ViewInteraction = this.check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)))