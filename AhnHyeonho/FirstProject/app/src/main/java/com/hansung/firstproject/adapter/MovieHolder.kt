package com.hansung.firstproject.adapter

import android.net.Uri
import android.os.Build
import android.text.Html
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hansung.firstproject.ui.movieinformation.MovieInformationActivity
import com.hansung.firstproject.data.MovieModel
import kotlinx.android.synthetic.main.movie_item.view.*

@Suppress("DEPRECATION")
open class MovieHolder(layoutId: Int, parents: ViewGroup) :
    BaseViewHolder<MovieModel>(layoutId, parents) {

    private lateinit var webPage: Uri

    init {
        itemView.setOnClickListener {
            MovieInformationActivity.getIntent(it.context).apply {
                putExtra(MovieInformationActivity.TAG, webPage.toString())
            }.also(itemView.context::startActivity)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun bindItems(item: MovieModel) {
        //image 바인딩
        with(itemView) {
            // userRating(별점)의 경우 단순 문자열로 전달됨. 이를 실수형으로 변환한 뒤 10점만점을 별 5개 기준으로 변경하기 위해 2로 나누고 이를 기반으로 ratingBar(별점)의 값을 변경한다.
            grade_movieitem.rating = item.userRating.toFloat() / 2
            releasedate_movieitem.text = item.pubDate
            // 검색어와 일치하는 부분은 태그로 감싸져 오는 제목 String에서 HTML Tag를 제거하는 method
            title_movieitem.text = Html.fromHtml(item.title, Html.FROM_HTML_MODE_LEGACY)
            director_movieitem.text = item.director
            actor_movieitem.text = item.actor

            Glide.with(context).load(item.image).apply(RequestOptions().override(300, 450))
                .apply(RequestOptions.centerCropTransform()).into(posterimage_movieitem)

            // binding.item = item // 이 형식으로 진행 되게 끔...


            //클릭시 웹사이트 연결을 위한 uri 바인딩
            webPage = Uri.parse(item.link)
        }

    }
}