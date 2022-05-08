package com.kou.example.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.view.View
import androidx.core.view.isVisible
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

/**
 * Add a callback to run when image is finished being loaded
 */
@SuppressLint("CheckResult")
fun <T> RequestBuilder<T>.onEndLoading(onLoadingEnd: () -> Unit) = apply {
    listener(object : RequestListener<T> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<T>?,
            isFirstResource: Boolean
        ): Boolean {
            onLoadingEnd()
            return false
        }

        override fun onResourceReady(
            resource: T?,
            model: Any?,
            target: Target<T>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            onLoadingEnd()
            return false
        }
    })
}