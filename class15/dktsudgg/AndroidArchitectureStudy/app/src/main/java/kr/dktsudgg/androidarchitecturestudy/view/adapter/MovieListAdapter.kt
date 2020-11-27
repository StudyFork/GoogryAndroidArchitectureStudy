package kr.dktsudgg.androidarchitecturestudy.view.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kr.dktsudgg.androidarchitecturestudy.R
import kr.dktsudgg.androidarchitecturestudy.data.model.MovieItem
import kr.dktsudgg.androidarchitecturestudy.databinding.MovieInfoLayoutBinding

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private val movieList: MutableList<MovieItem> = mutableListOf()

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            MovieInfoLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
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

    inner class ViewHolder(val binding: MovieInfoLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var movieItem: MovieItem

        fun bindData(item: MovieItem) {
            /**
             * 원본 MovieItem을 적절히 가공하여 바인딩 데이터로 사용
             */
            movieItem = MovieItem(
                stripHtml(item.title),
                stripHtml(item.subtitle),
                stripSpecificString(item.director, "|"),
                item.actor,
                item.userRating,
                item.image,
                item.link,
                item.pubDate
            )

            binding.movieItem = movieItem
            binding.viewHolder = this
            binding.executePendingBindings()
        }

        fun click(clickedView: View) {
            when (clickedView.id) {
                R.id.title -> {    // 타이틀 클릭 시, 링크내용 토스트
                    Toast.makeText(
                        binding.title.context,
                        "\"" + movieItem.title + "\"" + "의 링크는 " + movieItem.link,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                }
            }
        }

    }

}