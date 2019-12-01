package wooooooak.com.studyapp.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import kotlinx.android.synthetic.main.item_moive.view.*
import kotlinx.coroutines.launch
import wooooooak.com.studyapp.R
import wooooooak.com.studyapp.common.constants.DISPLAY_LIST_COUNT
import wooooooak.com.studyapp.common.constants.PAGING_OFFSET
import wooooooak.com.studyapp.common.ext.startWebView
import wooooooak.com.studyapp.model.response.movie.Movie
import wooooooak.com.studyapp.naverApi
import wooooooak.com.studyapp.ui.base.Searchable

class MovieListAdapter(private val fragmentActivity: FragmentActivity) :
    ListAdapter<Movie, MovieListAdapter.MovieViewHolder>(DiffCallback()), Searchable {

    private var textOnEditTextView = ""

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
        if (position > itemCount - PAGING_OFFSET) loadMore()
    }

    override fun searchByTitle(title: String) {
        fragmentActivity.lifecycleScope.launch {
            try {
                val response = naverApi.getMovies(title, DISPLAY_LIST_COUNT, null)
                submitList(response.movies)
            } catch (e: Exception) {
                Toast.makeText(fragmentActivity, e.toString(), Toast.LENGTH_SHORT).show()
            } finally {
                textOnEditTextView = title
            }
        }
    }

    private fun loadMore() {
        fragmentActivity.lifecycleScope.launch {
            try {
                val response = naverApi.getMovies(textOnEditTextView, DISPLAY_LIST_COUNT, itemCount + 1)
                currentList.toMutableList().let { list ->
                    list.addAll(response.movies)
                    submitList(list)
                }
            } catch (e: Exception) {
                Toast.makeText(fragmentActivity, e.toString(), Toast.LENGTH_SHORT).show()
            }
        }
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
