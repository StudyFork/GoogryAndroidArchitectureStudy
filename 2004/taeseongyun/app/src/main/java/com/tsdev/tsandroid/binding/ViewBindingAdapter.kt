package com.tsdev.tsandroid.binding

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.tsdev.tsandroid.util.widget.CustomImageWithGlide
import com.tsdev.tsandroid.util.widget.ThrottleClickListener
import io.reactivex.rxjava3.disposables.CompositeDisposable

@BindingAdapter("isVisible")
fun View.visibleBindingAdapter(isLoading: Boolean) {
    visibility = if (isLoading) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("onDebounceClickEvent")
fun ImageView.debounceClickEvent(listener: View.OnClickListener) {
    setOnClickListener(ThrottleClickListener(disposable = CompositeDisposable()) {
        it.run(listener::onClick)
    })
}

@BindingAdapter("moviePosterImg")
fun CustomImageWithGlide.moviePosterBindingAdapter(imageUrl: String) {
    loadMovieImage(imageUrl)
}

@BindingAdapter("setMoveWebClickListener")
fun CardView.moveWebBindingAdapter(uri: String?) {
    setOnClickListener {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(uri)
            )
        )
    }
}