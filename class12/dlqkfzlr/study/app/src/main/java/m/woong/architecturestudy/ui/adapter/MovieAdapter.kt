package m.woong.architecturestudy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import m.woong.architecturestudy.R
import m.woong.architecturestudy.data.source.remote.model.MovieResponse
import m.woong.architecturestudy.databinding.RvitemMovieBinding

class MovieAdapter() :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movieList: ArrayList<MovieResponse.Item> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {

        val binding = DataBindingUtil.inflate<RvitemMovieBinding>(
            LayoutInflater.from(parent.context),
            R.layout.rvitem_movie,
            parent,
            false
        )

        return MovieViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movieList[position])
    }

    fun resetData(data: List<MovieResponse.Item>) {
        movieList.clear()
        movieList.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount() = movieList.size

    class MovieViewHolder(private val itemMovieBinding: RvitemMovieBinding) :
        RecyclerView.ViewHolder(itemMovieBinding.root) {
        fun onBind(movieItem: MovieResponse.Item) {
            itemMovieBinding.movie = movieItem
            itemMovieBinding.executePendingBindings()
        }
    }
}