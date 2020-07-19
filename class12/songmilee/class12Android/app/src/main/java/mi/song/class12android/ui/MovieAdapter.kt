package mi.song.class12android.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import mi.song.class12android.R
import mi.song.class12android.data.model.MovieInfo
import mi.song.class12android.databinding.ItemMovieBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieVh>() {
    private val movieList = ArrayList<MovieInfo>()

    fun addMovieInfo(movieList: List<MovieInfo>) {
        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }

    fun clearMovieList() {
        movieList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVh {
        val binding: ItemMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )
        return MovieVh(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieVh, position: Int) {
        holder.bind(movieList[position])
    }

    inner class MovieVh(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movieInfo: MovieInfo) {
            binding.tvTitle.text = movieInfo.title
            binding.tvDirector.text = movieInfo.director
            binding.tvActor.text = movieInfo.actor

            movieInfo.image.let {
                Glide.with(itemView.context)
                    .load(movieInfo.image)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(binding.ivThumbnail)
            }
        }
    }
}