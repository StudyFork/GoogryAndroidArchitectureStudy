package wooooooak.com.studyapp.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import kotlinx.android.synthetic.main.item_moive.view.*
import wooooooak.com.studyapp.R
import wooooooak.com.studyapp.data.model.response.movie.Movie
import wooooooak.com.studyapp.ui.base.BaseSearchListAdapter

class MovieListAdapter(
    private val itemListener: ItemListener<Movie>
) :
    BaseSearchListAdapter<Movie, MovieListAdapter.MovieViewHolder>(itemListener, DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_moive, parent, false)
    )

    override fun bindItemViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie, View.OnClickListener {
                itemListener.renderWebView(movie.link)
            })
        }
    }


    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie, onClickItem: View.OnClickListener) {
            with(itemView) {
                movie_title.text = HtmlCompat.fromHtml(movie.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
                movie_image.load(movie.image)
                rating_bar.rating = movie.userRating
                director.text = HtmlCompat.fromHtml(movie.director, HtmlCompat.FROM_HTML_MODE_COMPACT)
                actors.text = HtmlCompat.fromHtml(movie.actor, HtmlCompat.FROM_HTML_MODE_COMPACT)
                movie_card.setOnClickListener(onClickItem)
            }
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
        oldItem == newItem
}
