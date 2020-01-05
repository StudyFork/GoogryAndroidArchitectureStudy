package com.siwon.prj

import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.siwon.prj.model.Movie

class MovieHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    // with 사용( kotlin scope 함수.. with, run, apply
    // 여기선 위드
    // 한줄로 끝나는 경우는 scope로 구분하지말고 =써서
    fun bind(item: Movie) = with(itemView) {
        Glide.with(this).load(item.image).into(itemView.findViewById(R.id.img_view))
        findViewById<TextView>(R.id.movie_name).text = item.title.replace("<b>", "\"").replace("</b>", "\"")
        findViewById<RatingBar>(R.id.score).rating = item.userRating.toFloat()/2f
        findViewById<TextView>(R.id.pub_date).text = item.pubDate
        findViewById<TextView>(R.id.director).text = item.director
        findViewById<TextView>(R.id.actor).text = item.actor
    }

}