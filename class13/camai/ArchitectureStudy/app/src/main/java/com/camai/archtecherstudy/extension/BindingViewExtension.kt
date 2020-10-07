package com.camai.archtecherstudy.extension

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.camai.archtecherstudy.data.model.Items
import com.camai.archtecherstudy.data.source.local.room.RecentSearchName
import com.camai.archtecherstudy.observer.MainViewModel
import com.camai.archtecherstudy.observer.RecentViewModel
import com.camai.archtecherstudy.ui.main.MovieSearchAdapter
import com.camai.archtecherstudy.ui.rencentdialog.RecentMovieAdapter


@BindingAdapter("ImageDrawable", "ImageDrawableError")
fun ImageViewAdapter(view: ImageView, res: String?, error: Drawable) {
    val options = RequestOptions()
        .error(error)

    Glide.with(view.context)
        .load(res)
        .apply(options)
        .into(view)

}

@BindingAdapter("clickWeb")
fun onClickWeb(linearLayout: LinearLayout, url: String?){
    linearLayout.setOnClickListener{
        val intent =  Intent(Intent.ACTION_VIEW, Uri.parse(url))
        linearLayout.context.startActivity(intent)
    }
}


@BindingAdapter("setMovieItems")
fun RecyclerView.setAdapterAndRecyclerViewInit(items: List<Items>?){
    val movieSearchAdapter = MovieSearchAdapter()

    this.run {
        adapter = movieSearchAdapter
        setHasFixedSize(false)
        addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }

    items?.let{ movieSearchAdapter.setClearAndAddList(items)}
}



@BindingAdapter("setRecentItems", "setVm")
fun RecyclerView.setAdapterAndRecycleInit(items: List<RecentSearchName>?, setBindVm: RecentViewModel?){
    val recentMovieAdapter = RecentMovieAdapter{
        setBindVm?.clickKeyword?.set(it)
        setBindVm?.clickName()
    }
    this.run {
        adapter = recentMovieAdapter
        setHasFixedSize(false)
        addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

    }

    items?.let{ recentMovieAdapter.setClearAndAddList(items)}
}
