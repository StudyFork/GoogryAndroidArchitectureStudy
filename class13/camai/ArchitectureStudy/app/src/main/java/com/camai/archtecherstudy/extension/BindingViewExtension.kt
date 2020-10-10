package com.camai.archtecherstudy.extension

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import android.widget.LinearLayout
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
fun imageViewAdapter(view: ImageView, res: String?, error: Drawable) {
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
