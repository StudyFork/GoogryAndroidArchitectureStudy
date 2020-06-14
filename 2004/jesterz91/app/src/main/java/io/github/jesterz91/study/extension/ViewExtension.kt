package io.github.jesterz91.study.extension

import android.net.Uri
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.databinding.BindingAdapter

@BindingAdapter("launchUrl")
fun View.launchUrl(url: String?) {
    url?.run(Uri::parse)?.let { uri ->
        setOnClickListener {
            CustomTabsIntent.Builder().build().launchUrl(context, uri)
        }
    }
}