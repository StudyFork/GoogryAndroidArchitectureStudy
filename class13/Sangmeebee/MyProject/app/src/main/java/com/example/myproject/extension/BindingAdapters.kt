package com.example.myproject.extension

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myproject.data.model.Items
import com.example.myproject.ui.MainActivity
import com.example.myproject.ui.MovieAdapter
import com.example.myproject.ui.TitleAdapter
import com.example.myproject.viewmodel.MainViewModel

@BindingAdapter("imageUrl")
fun ImageView.setLoadUrl(imageUrl: String) {
    Glide.with(this)
        .load(imageUrl)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

@BindingAdapter("htmlText")
fun TextView.setHtmlText(text: String) {
    this.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT)
}

//open WebView
@BindingAdapter("webUrl")
fun View.openWebView(url: String) {
    this.setOnClickListener {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        context.startActivity(intent)
    }
}
