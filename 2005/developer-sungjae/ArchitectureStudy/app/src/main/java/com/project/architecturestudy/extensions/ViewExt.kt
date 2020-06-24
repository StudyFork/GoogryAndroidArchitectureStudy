package com.project.architecturestudy.extensions

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("onClick")
fun View.onClick(url: String) {
    setOnClickListener {
        url.let { url ->
            ContextCompat.startActivity(
                context,
                Intent(
                    Intent.ACTION_VIEW, Uri.parse(url)
                ),
                null
            )
        }
    }
}