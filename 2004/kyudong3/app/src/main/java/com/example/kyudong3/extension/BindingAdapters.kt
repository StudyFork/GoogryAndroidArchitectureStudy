package com.example.kyudong3.extension

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kyudong3.adapter.SearchMovieRvAdapter
import com.example.kyudong3.viewModel.MainViewModel
import com.kyudong.data.model.Movie

@BindingAdapter(value = ["setItems"])
fun RecyclerView.setItems(movies: List<Movie>?) {
    movies?.let { (adapter as? SearchMovieRvAdapter)?.setMovieList(it) }
}

@BindingAdapter(value = ["loadUrl"])
fun ImageView.loadUrl(url: String?) {
    url?.let {
        Glide.with(this.context)
            .load(url)
            .into(this)
    }
}

@BindingAdapter(value = ["setOnEditorActionListener"])
fun setOnEditorActionListener(view: EditText, vm: MainViewModel) {
    view.setOnEditorActionListener { _, actionId, _ ->
        when (actionId) {
            EditorInfo.IME_ACTION_UNSPECIFIED,
            EditorInfo.IME_ACTION_GO -> {
                vm.searchMovie()
                true
            }
            else -> false
        }
    }
}