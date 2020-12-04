package com.jay.aas.binding

import android.os.Build
import android.text.Html
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jay.aas.model.Movie
import com.jay.aas.model.SearchHistory
import com.jay.aas.ui.history.SearchHistoryAdapter
import com.jay.aas.ui.movie.MovieAdapter

@BindingAdapter("onEditorEnterAction")
fun EditText.bindOnEditorEnterAction(action: (() -> Unit)? = null) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            action?.invoke()
            return@setOnEditorActionListener true
        }
        return@setOnEditorActionListener false
    }
}

@Suppress("DEPRECATION")
@BindingAdapter("htmlText")
fun TextView.bindHtmlText(text: String) {
    this.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(text)
    }
}

@BindingAdapter("loadUrl")
fun ImageView.bindLoadUrl(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}

@BindingAdapter("items")
fun RecyclerView.bindItems(items: List<Any>) {
    when (adapter) {
        is MovieAdapter -> (adapter as MovieAdapter).setMovies(items as List<Movie>)
        is SearchHistoryAdapter -> (adapter as SearchHistoryAdapter).setSearchHistories(items as List<SearchHistory>)
    }
}

@BindingAdapter(value = ["visible"])
fun View.bindVisible(visible: Boolean) {
    isVisible = visible
}
