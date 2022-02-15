package com.test.testoll.ext

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.provider.Settings
import android.view.View
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions

@SuppressLint("HardwareIds")
fun Context.androidUUID(): String{
    return Settings.Secure.getString(
        contentResolver,
        Settings.Secure.ANDROID_ID)
}

@BindingAdapter(
    "srcUrl"
)
fun ImageView.bindSrcUrl(
    url: String,
) = Glide.with(this).load(url).transition(DrawableTransitionOptions.withCrossFade()).into(this)

fun ImageView.load(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}

fun ComponentActivity.disableBack(){
    val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {}
    }
    onBackPressedDispatcher.addCallback(this, callback)
}

fun View.visibleOrGone(visible: Boolean) {
    visibility = if(visible) View.VISIBLE else View.GONE
}