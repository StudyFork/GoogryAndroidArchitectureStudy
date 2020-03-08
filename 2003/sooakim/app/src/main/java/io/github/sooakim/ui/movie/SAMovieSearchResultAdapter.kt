package io.github.sooakim.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import io.github.sooakim.R
import io.github.sooakim.ui.base.OnRecyclerViewItemClick
import io.github.sooakim.ui.base.SARecyclerViewAdapter
import io.github.sooakim.ui.base.SAViewHolder
import io.github.sooakim.ui.base.SAViewHolderLifecycle
import io.github.sooakim.ui.movie.model.SAMovieViewModel

class SAMovieSearchResultAdapter(
    onItemClick: OnRecyclerViewItemClick<SAMovieViewModel>
) : SARecyclerViewAdapter<SAMovieViewModel, SAMovieSearchResultAdapter.SAItemViewHolder>(onItemClick) {
    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): SAItemViewHolder {
        return SAItemViewHolder(
            inflater.inflate(R.layout.item_movie_search_result, parent, false)
        )
    }

    class SAItemViewHolder(itemView: View) : SAViewHolder(itemView),
        SAViewHolderLifecycle<SAMovieViewModel> {
        private val ivPoster: AppCompatImageView = itemView.findViewById(R.id.iv_poster)
        private val tvTitle: AppCompatTextView = itemView.findViewById(R.id.tv_title)
        private val rtbStar: AppCompatRatingBar = itemView.findViewById(R.id.rtb_star)
        private val tvPublishDate: AppCompatTextView = itemView.findViewById(R.id.tv_publish_date)
        private val tvDirector: AppCompatTextView = itemView.findViewById(R.id.tv_director)
        private val tvActor: AppCompatTextView = itemView.findViewById(R.id.tv_actor)

        override fun onBind(item: SAMovieViewModel) {
            Glide.with(ivPoster)
                .load(item.image)
                .error(R.drawable.bg_placeholder_movie)
                .placeholder(R.drawable.bg_placeholder_movie)
                .into(ivPoster)

            tvTitle.text = item.title
            rtbStar.rating = item.userRating
            tvPublishDate.text = item.pubDate
            tvDirector.text = item.director
            tvActor.text = item.actor
        }

        override fun onRecycle() {
            Glide.with(ivPoster).clear(ivPoster)
        }
    }
}