package com.example.kyudong3.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kyudong3.adapter.SearchMovieRvAdapter
import com.example.kyudong3.data.model.Movie

@BindingAdapter(value = ["setItems"])
fun RecyclerView.setItems(movies: List<Movie>?) {
    if (adapter is SearchMovieRvAdapter) {
        movies?.let {
            (adapter as SearchMovieRvAdapter).setMovieList(it)
        }
    }
}

@BindingAdapter(value = ["loadUrl"])
fun ImageView.loadUrl(url: String?) {
    url?.let {
        Glide.with(this.context)
            .load(url)
            .into(this)
    }
}

//@BindingAdapter(value = ["onEditorAction"])
//fun EditText.setEditorAction(f: ObservableField<Unit>) {
//    setOnEditorActionListener { v, actionId, event ->
//        when (actionId) {
//            EditorInfo.IME_ACTION_UNSPECIFIED,
//            EditorInfo.IME_ACTION_GO -> true
//            else -> false
//        }
//    }
//}