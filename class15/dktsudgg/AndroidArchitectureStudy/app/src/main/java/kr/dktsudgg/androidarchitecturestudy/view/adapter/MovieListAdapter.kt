package kr.dktsudgg.androidarchitecturestudy.view.adapter

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

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private val movieList: MutableList<MovieItem> = mutableListOf()

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_info_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var movieItem = movieList[position]

        // 영화 제목. HTML태그 제거 및 클릭 시 링크주소를 Toast
        holder.title.text = stripHtml(movieItem.title)
        holder.title.setOnClickListener {
            Toast.makeText(holder.itemView.context, "링크는 " + movieItem.link, Toast.LENGTH_SHORT)
                .show()
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
            .with(holder.itemView.context)
            .load(movieItem.image)
            .centerInside()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.image);
    }

    /**
     * 아이탬 목록을 갱신하는 메소드
     */
    fun refreshData(newMovieList: List<MovieItem>) {
        movieList.clear()
        movieList.addAll(newMovieList)
        notifyDataSetChanged()
    }

    /**
     * HTML 태그 제거하는 메소드 (Android N에서 deprecated된 히스토리를 반영하여 버전별 대응)
     */
    private fun stripHtml(html: String): String {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(html).toString()
        }
        return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).toString()
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