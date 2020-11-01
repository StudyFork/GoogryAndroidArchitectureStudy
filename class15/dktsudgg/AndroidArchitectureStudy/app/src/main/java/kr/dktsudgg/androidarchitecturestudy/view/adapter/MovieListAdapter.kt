package kr.dktsudgg.androidarchitecturestudy.view.adapter

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_info_layout.view.*
import kr.dktsudgg.androidarchitecturestudy.R
import kr.dktsudgg.androidarchitecturestudy.api.naver.data.MovieItem

class MovieListAdapter(val context: Context, var movieList: MutableList<MovieItem>) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.movie_info_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var movieItem = movieList[position]

        // 영화 제목. HTML태그 제거 및 클릭 시 링크주소를 Toast
        holder.title.text = stripHtml(movieItem.title)
        holder.title.setOnClickListener {
            Toast.makeText(context, "링크는 " + movieItem.link, Toast.LENGTH_SHORT).show()
        }
        // 영화 소제목
        holder.subtitle.text = movieItem.subtitle
        // 영화 감독. 맨 마지막에 파이프(|)문자가 삽입된 부분을 제거
        holder.director.text = stripSpecificString(movieItem.director, "|")
        // 영화 출연진 목록
        holder.actor.text = movieItem.actor
        // 영화 개봉년도
        holder.pubDate.text = movieItem.pubDate
        // 영화 평점
        holder.userRating.text = movieItem.userRating
        // 영화 커버 이미지. Glide 사용하여 이미지 로딩
        Glide
            .with(context)
            .load(movieItem.image)
            .centerInside()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.image);
    }

    /**
     * HTML 태그 제거하는 메소드
     */
    private fun stripHtml(html: String): String {
        return Html.fromHtml(html).toString()
    }

    /**
     * 특정 문자열 제거하는 메소드
     */
    private fun stripSpecificString(inputStr: String, targetReplaceStr: String): String {
        return inputStr.replace(targetReplaceStr, "")
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.title
        val subtitle = itemView.subtitle
        val director = itemView.director
        val actor = itemView.actor
        val userRating = itemView.userRating
        val image = itemView.image

        //        val link = itemView.link
        val pubDate = itemView.pubDate
    }

}