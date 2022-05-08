package com.kou.example.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.view.View
import androidx.core.view.isVisible

/**
 * Define utility functions
 */
object Utils {
    fun swapVisibility(duration: Long, visibleView: View, vararg invisibleViews: View) {
        visibleView.alpha = 0f
        visibleView.visibility = View.VISIBLE

        val valueAnimator = ValueAnimator.ofFloat(0f, 1f)
        valueAnimator.addUpdateListener {
            visibleView.alpha = it.animatedValue as Float
            invisibleViews.forEach { view ->
                if (view.isVisible) view.alpha = 1f - (it.animatedValue as Float)
            }
        }
        valueAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                invisibleViews.forEach { it.visibility = View.GONE}
            }
        })
        valueAnimator.duration = duration
        valueAnimator.start()
    }
}