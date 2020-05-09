package com.tsdev.tsandroid.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.tsdev.tsandroid.util.widget.CustomImageWithGlide
import com.tsdev.tsandroid.util.widget.DebounceClickListener
import io.reactivex.rxjava3.disposables.CompositeDisposable

@BindingAdapter("isVisible")
fun View.visibleBindingAdapter(isLoading: Boolean) {
    visibility = if (isLoading) {
        View.VISIBLE
    }
    else {
        View.GONE
    }
}

@BindingAdapter("onDebounceClickEvent")
fun ImageView.debounceClickEvent(listener: View.OnClickListener) {
    setOnClickListener(DebounceClickListener(disposable = CompositeDisposable()) {
        it.run(listener::onClick)
    })
}

@BindingAdapter("moviePosterImg")
fun CustomImageWithGlide.moviePosterBindingAdapter(imageUrl: String) {
    loadMovieImage(imageUrl)
}