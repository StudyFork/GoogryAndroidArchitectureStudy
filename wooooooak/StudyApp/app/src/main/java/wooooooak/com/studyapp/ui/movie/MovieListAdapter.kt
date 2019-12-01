package wooooooak.com.studyapp.ui.movie

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.lifecycle.LifecycleOwner
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
import wooooooak.com.studyapp.model.response.movie.Movie
import wooooooak.com.studyapp.naverApi
import wooooooak.com.studyapp.ui.base.Searchable

class MovieListAdapter(private val lifecycleOwner: LifecycleOwner) :
    ListAdapter<Movie, MovieListAdapter.MovieViewHolder>(DiffCallback()), Searchable {

    private var textOnEditTextView = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_moive, parent, false)
    )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
        if (position > itemCount - PAGING_OFFSET) loadMore()
    }

    override fun searchByTitle(title: String) {
        lifecycleOwner.lifecycleScope.launch {
            try {
                val response = naverApi.getMovies(title, DISPLAY_LIST_COUNT, null)
                submitList(response.movies)
            } catch (e: Exception) {
                Log.d("MovieFragment", e.toString())
                Log.d("MovieFragment", e.message.toString())
            } finally {
                textOnEditTextView = title
            }
        }
    }

    private fun loadMore() {
        lifecycleOwner.lifecycleScope.launch {
            try {
                val response = naverApi.getMovies(textOnEditTextView, DISPLAY_LIST_COUNT, itemCount + 1)
                currentList.toMutableList().let { list ->
                    list.addAll(response.movies)
                    submitList(list)
                }
            } catch (e: Exception) {

            }
        }
    }

    inner class MovieViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie) {
            view.movie_title.text = HtmlCompat.fromHtml(movie.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
            view.movie_image.load(movie.image)
        }
    }

}

private class DiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
        oldItem.image == newItem.image
}
