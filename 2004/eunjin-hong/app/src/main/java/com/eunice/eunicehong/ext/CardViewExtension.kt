package com.eunice.eunicehong.ext

import android.content.Intent
import android.net.Uri
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:setMovieItemClickListener")
fun showDetail(view: CardView, url: String) {
    view.setOnClickListener {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        view.context.startActivity(intent)
    }
}