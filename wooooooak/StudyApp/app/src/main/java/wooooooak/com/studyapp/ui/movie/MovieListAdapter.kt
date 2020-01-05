package wooooooak.com.studyapp.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import wooooooak.com.studyapp.data.model.response.movie.Movie
import wooooooak.com.studyapp.databinding.ItemMoiveBinding
import wooooooak.com.studyapp.ui.base.BaseSearchListAdapter

class MovieListAdapter(
    private val itemListener: ItemListener<Movie>
) :
    BaseSearchListAdapter<Movie, MovieListAdapter.MovieViewHolder>(itemListener, DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
        ItemMoiveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun bindItemViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie, View.OnClickListener {
                itemListener.renderWebView(movie.link)
            })
        }
    }

    inner class MovieViewHolder(private val binding: ItemMoiveBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie, _onClickItemListener: View.OnClickListener) {
            with(binding) {
                item = movie
                onClickItemListener = _onClickItemListener
                executePendingBindings()
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
