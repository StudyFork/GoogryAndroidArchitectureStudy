package wooooooak.com.studyapp.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import kotlinx.android.synthetic.main.item_moive.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import wooooooak.com.studyapp.R
import wooooooak.com.studyapp.common.constants.DISPLAY_LIST_COUNT
import wooooooak.com.studyapp.common.constants.PAGING_OFFSET
import wooooooak.com.studyapp.common.ext.startWebView
import wooooooak.com.studyapp.model.response.movie.Movie
import wooooooak.com.studyapp.naverApi
import wooooooak.com.studyapp.ui.base.BaseSearchListAdapter
import wooooooak.com.studyapp.ui.base.Searchable

class MovieListAdapter(private val fragmentActivity: FragmentActivity) :
    BaseSearchListAdapter<Movie, MovieListAdapter.MovieViewHolder>(fragmentActivity, DiffCallback()), Searchable {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_moive, parent, false)
    )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie, View.OnClickListener {
                fragmentActivity.startWebView(movie.link)
            })
        }
        if (position > itemCount - PAGING_OFFSET) loadMore(itemCount)
    }

    override suspend fun initItemListByTitleAsync(title: String) = withContext(Dispatchers.IO) {
        naverApi.getMovies(title, DISPLAY_LIST_COUNT, null).movies
    }

    override suspend fun getMoreItemListFromStartIndexAsync(title: String, startIndex: Int) =
        withContext(Dispatchers.IO) {
            naverApi.getMovies(title, DISPLAY_LIST_COUNT, startIndex).movies
        }

    inner class MovieViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie, onClickItem: View.OnClickListener) {
            with(view) {
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
