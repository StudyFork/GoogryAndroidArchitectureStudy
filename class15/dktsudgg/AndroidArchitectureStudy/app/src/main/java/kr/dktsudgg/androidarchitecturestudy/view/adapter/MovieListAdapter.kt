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
import kr.dktsudgg.androidarchitecturestudy.data.model.MovieItem

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
        holder.bindData(movieList[position])
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

        fun bindData(item: MovieItem) {
            // 영화 제목. HTML태그 제거 및 클릭 시 링크주소를 Toast
            itemView.title.text = stripHtml(item.title)
            itemView.title.setOnClickListener {
                Toast.makeText(itemView.context, "링크는 " + item.link, Toast.LENGTH_SHORT)
                    .show()
            }
            // 영화 소제목
            itemView.subtitle.text = item.subtitle
            // 영화 감독. 맨 마지막에 파이프(|)문자가 삽입된 부분을 제거
            itemView.director.text = stripSpecificString(item.director, "|")
            // 영화 출연진 목록
            itemView.actor.text = item.actor
            // 영화 개봉년도
            itemView.pubDate.text = item.pubDate
            // 영화 평점
            itemView.userRating.text = item.userRating
            // 영화 커버 이미지. Glide 사용하여 이미지 로딩
            Glide
                .with(itemView.context)
                .load(item.image)
                .centerInside()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(itemView.image)
        }
    }

}