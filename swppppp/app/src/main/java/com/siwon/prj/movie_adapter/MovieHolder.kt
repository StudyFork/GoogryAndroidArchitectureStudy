package com.siwon.prj.movie_adapter

import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.siwon.prj.R
import com.siwon.prj.common.adapter.BaseViewHolder
import com.siwon.prj.model.Movie

// TODO 시간 되면 제네릭 타입으로 데이터 받아 보세요!
class MovieHolder (itemView: View, private val action: ((Int) -> Unit)? = null) : BaseViewHolder<Movie>(itemView), View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }
    override fun onBind(item: Movie) {
        with(itemView) {
            Glide.with(this).load(item.image).into(itemView.findViewById(R.id.img_view))
            findViewById<TextView>(R.id.movie_name).text =
                item.title.replace("<b>", "\"").replace("</b>", "\"")
            findViewById<RatingBar>(R.id.score).rating = item.userRating.toFloat() / 2f
            findViewById<TextView>(R.id.pub_date).text = item.pubDate
            findViewById<TextView>(R.id.director).text = item.director
            findViewById<TextView>(R.id.actor).text = item.actor
        }
    }

    override fun onClick(v: View?) {
        action?.let { it(adapterPosition) }
    }
}