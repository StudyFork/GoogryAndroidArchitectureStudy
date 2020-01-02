package com.hansung.firstproject.adapter

import android.content.Intent
import android.net.Uri
import android.text.Html
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hansung.firstproject.MovieInformationActivity
import com.hansung.firstproject.data.MovieModel
import kotlinx.android.synthetic.main.movie_item.view.*


//MovieView 재사용하는 Holder
@Suppress("DEPRECATION")
class MovieHolder(private val movieView: View) : RecyclerView.ViewHolder(movieView) {
    fun bindItems(data: MovieModel) {
        //image 바인딩
        with(itemView) {
            // userRating(별점)의 경우 단순 문자열로 전달됨. 이를 실수형으로 변환한 뒤 10점만점을 별 5개 기준으로 변경하기 위해 2로 나누고 이를 기반으로 ratingBar(별점)의 값을 변경한다.
            grade_movieitem.rating = data.userRating.toFloat() / 2
            releasedate_movieitem.text = data.pubDate
            // 검색어와 일치하는 부분은 태그로 감싸져 오는 제목 String에서 HTML Tag를 제거하는 method
            title_movieitem.text = Html.fromHtml(data.title, Html.FROM_HTML_MODE_LEGACY)
            director_movieitem.text = data.director
            actor_movieitem.text = data.actor

            Glide.with(context).load(data.image).apply(RequestOptions().override(300, 450))
                .apply(RequestOptions.centerCropTransform()).into(posterimage_movieitem)

            //클릭시 웹사이트 연결
            setOnClickListener {
                val webPage = Uri.parse(data.link)
                val webIntent = Intent(context, MovieInformationActivity::class.java).apply {
                    putExtra("WEB_PAGE", webPage.toString())
                }
                movieView.context.startActivity(webIntent)
            }
        }
    }
}