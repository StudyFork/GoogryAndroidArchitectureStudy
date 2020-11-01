package com.example.androidarchitecturestudy.adapter

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidarchitecturestudy.R
import com.example.androidarchitecturestudy.data.GetMovieInfo

class MovieListRecyclerViewAdapter(private val context: Context) :
    RecyclerView.Adapter<MovieListRecyclerViewAdapter.MovieItemViewHolder>() {


    private var movieList = ArrayList<GetMovieInfo.MovieData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_main_recyclerview, parent, false)
        return MovieItemViewHolder(view)
    }


    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(movieList[position])
    }


    override fun getItemCount(): Int = movieList.size


    fun getMovieData(movieList: ArrayList<GetMovieInfo.MovieData>) {
        this.movieList = movieList
        notifyDataSetChanged()

    }

    inner class MovieItemViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        val tvMovieTitle = itemview.findViewById<TextView>(R.id.tv_movie_title)
        val imgMoviePoster = itemview.findViewById<ImageView>(R.id.img_movie_poster)
        val rtbMovieGrade = itemview.findViewById<RatingBar>(R.id.rtb_movie_grade)


        //각 뷰에  데이터 연결
        fun bind(movieData: GetMovieInfo.MovieData?) {

            //영화 제목
            if (movieData?.title != null) {
                tvMovieTitle.text = removeHtmlTag(htmlText = movieData.title)
            } else {
                tvMovieTitle.text = movieData?.title
            }

            //영화 포스터
            Glide.with(itemView.context).load(movieData?.image)
                .error(R.drawable.ic_launcher_background)
                .into(imgMoviePoster)


            //rating은 null일경우 0f 적용
            if (movieData != null) {

                //10점으로 옮으로 2로나눠줌
                rtbMovieGrade.rating = movieData.userRating / 2
            } else {
                rtbMovieGrade.rating = 0f
            }

        }

    }//MovieItemViewHolder 끝


    //제목에  html 태그 섞여와서 없앰.
    private fun removeHtmlTag(htmlText: String?): String {

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY).toString()

        } else {
            Html.fromHtml(htmlText).toString()
        }

    }//removeHtmlTag 끝

}