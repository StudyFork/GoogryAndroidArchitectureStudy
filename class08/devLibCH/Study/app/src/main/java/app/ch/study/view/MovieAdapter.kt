package app.ch.study.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.ch.study.BR
import app.ch.study.R
import app.ch.study.data.db.entitiy.MovieModel
import app.ch.study.databinding.ListItemMovieBinding
import com.bumptech.glide.Glide

class MovieAdapter(
    private val context: Context,
    private val list: ArrayList<MovieModel>,
    private val listener: EventListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun replaceAll(items: ArrayList<MovieModel>) {
        items.let {
            this.list.run {
                clear()
                addAll(it)
            }
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MovieViewHolder(parent.context, R.layout.list_item_movie, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as MovieViewHolder).onBindViewHolder(list[position])

    fun itemClick(url: String) {
        listener.itemClick(url)
    }

    inner class MovieViewHolder(
        context: Context, view: Int, parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(context).inflate(view, parent, false)
    ) {
        private val binding: ListItemMovieBinding? = DataBindingUtil.bind(itemView)

        fun onBindViewHolder(item: MovieModel) {
            binding?.setVariable(BR.item, item)
            binding?.setVariable(BR.adapter, this@MovieAdapter)

            binding?.ivPoster?.let {
                Glide
                    .with(context)
                    .load(item.image)
                    .centerCrop()
                    .into(it)
            }
        }
    }

    interface EventListener {
        fun itemClick(url: String)
    }

}