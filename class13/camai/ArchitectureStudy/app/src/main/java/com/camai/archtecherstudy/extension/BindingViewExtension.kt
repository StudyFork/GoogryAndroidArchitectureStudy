package com.camai.archtecherstudy.extension

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


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
fun onClickWeb(linearLayout: LinearLayout, url: String?) {
    linearLayout.setOnClickListener {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        linearLayout.context.startActivity(intent)
    }
}
