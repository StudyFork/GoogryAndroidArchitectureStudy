package io.github.sooakim.ui.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import io.github.sooakim.R
import io.github.sooakim.databinding.ItemMovieSearchResultBinding
import io.github.sooakim.ui.base.OnRecyclerViewItemClick
import io.github.sooakim.ui.base.SARecyclerViewAdapter
import io.github.sooakim.ui.base.SAViewHolder
import io.github.sooakim.ui.movie.model.SAMoviePresentation

class SAMovieSearchResultAdapter(
    onItemClick: OnRecyclerViewItemClick<SAMoviePresentation>
) : SARecyclerViewAdapter<SAMoviePresentation>(
    onItemClick
) {
    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): SAItemViewHolder {
        return SAItemViewHolder(
            inflater.inflate(R.layout.item_movie_search_result, parent, false)
        )
    }

    class SAItemViewHolder(itemView: View) :
        SAViewHolder<ItemMovieSearchResultBinding, SAMoviePresentation>(itemView) {
        override fun onRecycle() {
            Glide.with(viewDataBinding.ivPoster)
                .clear(viewDataBinding.ivPoster)
        }
    }
}