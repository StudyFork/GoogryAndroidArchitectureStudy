package app.ch.study.ui.main.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.ch.study.BR
import app.ch.study.R
import app.ch.study.data.remote.response.MovieModel
import app.ch.study.databinding.ListItemMovieBinding

class MovieAdapter(private val itemClick: (String) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = mutableListOf<MovieModel>()

    fun replaceAll(items: MutableList<MovieModel>) {
        items.let {
            this.list.run {
                clear()
                addAll(it)
            }
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MovieViewHolder(parent.context, R.layout.list_item_movie, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as MovieViewHolder).onBindViewHolder(list[position])

    fun clear() {
        list.clear()
    }

    inner class MovieViewHolder(
        context: Context,
        view: Int,
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(context).inflate(view, parent, false)
    ) {
        private val binding: ListItemMovieBinding = DataBindingUtil.bind(itemView)!!

        fun holderItemClick(item: MovieModel) = itemClick(item.link)

        fun onBindViewHolder(item: MovieModel) {
            binding.setVariable(BR.holder, this)
            binding.setVariable(BR.item, item)
        }
    }

}