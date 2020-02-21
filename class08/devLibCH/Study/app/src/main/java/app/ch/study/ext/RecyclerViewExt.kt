package app.ch.study.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import app.ch.study.data.remote.response.MovieModel
import app.ch.study.ui.main.view.MovieAdapter

@BindingAdapter(value = ["movieResult"])
fun RecyclerView.setMovieResult(list: MutableList<MovieModel>) {
    (adapter as? MovieAdapter)?.run {
        replaceAll(list)
    }
}