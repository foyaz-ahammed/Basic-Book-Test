package com.kou.example

import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kou.example.activities.MainActivity
import com.kou.example.extensions.isGone
import com.kou.example.extensions.isVisible
import com.kou.example.extensions.onView
import org.junit.Test
import org.junit.runner.RunWith

/**
 * UI test for MainActivity
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Test
    fun checkLoadingView() {
        launchActivity<MainActivity>().use {
            R.id.progress_bar.onView().isVisible()
            R.id.recycler_view.onView().isGone()
            R.id.error_views.onView().isGone()
        }
    }
}