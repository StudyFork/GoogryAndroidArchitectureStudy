package m.woong.architecturestudy.ui.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import m.woong.architecturestudy.R
import m.woong.architecturestudy.data.source.remote.model.MovieResponse

class MovieAdapter() :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movieList: ArrayList<MovieResponse.Item> = ArrayList()

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {

        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.rvitem_movie, parent, false)

        return MovieViewHolder(
            v
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Glide.with(holder.imageImageView.context)
            .load(movieList[position].image)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.imageImageView)
        holder.titleTextView.text = parseHtmlTag(movieList[position].title)
        holder.pubDateTextView.text = parseHtmlTag(movieList[position].pubDate)
        holder.directorTextView.text = parseHtmlTag(movieList[position].director)
    }

    private fun parseHtmlTag(words: String): String {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Html.fromHtml(words, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            Html.fromHtml(words).toString()
        }
    }

    fun resetData(data: List<MovieResponse.Item>) {
        movieList.clear()
        movieList.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount() = movieList.size

    class MovieViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val imageImageView: ImageView = v.findViewById(R.id.movie_image)
        val titleTextView: TextView = v.findViewById(R.id.movie_title)
        val pubDateTextView: TextView = v.findViewById(R.id.movie_pubdate)
        val directorTextView: TextView = v.findViewById(R.id.movie_director)
    }
}