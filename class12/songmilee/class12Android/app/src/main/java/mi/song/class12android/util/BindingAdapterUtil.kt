package mi.song.class12android.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.Observable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import mi.song.class12android.data.model.MovieInfo
import mi.song.class12android.ui.MovieAdapter
import mi.song.class12android.viewmodel.MovieViewModel

@BindingAdapter("bind:imgUrl")
fun loadImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imgView)
            .load(imgUrl)
            .apply(RequestOptions().override(80, 80))
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(imgView)
    }
}

@BindingAdapter("bind:updateMovieList")
fun updateMovieList(recyclerView: RecyclerView, movieInfoList: List<MovieInfo>?) {
    movieInfoList?.let {
        val adapter = recyclerView.adapter as MovieAdapter

        adapter.clearMovieList()
        adapter.addMovieInfo(movieInfoList)
    }
}