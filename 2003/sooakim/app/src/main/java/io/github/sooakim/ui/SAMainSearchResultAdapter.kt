package io.github.sooakim.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.sooakim.R
import io.github.sooakim.network.model.SAMovieModel

class SAMainSearchResultAdapter :
    ListAdapter<SAMovieModel, SAMainSearchResultAdapter.SAItemViewHolder>(mDiffer) {
    private lateinit var mLayoutInflater: LayoutInflater
    var clickListener: OnMainSearchResultClickListener? = null

    private fun provideLayoutInflater(context: Context): LayoutInflater {
        if (!::mLayoutInflater.isInitialized) mLayoutInflater = LayoutInflater.from(context)
        return mLayoutInflater
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SAItemViewHolder {
        val layoutInflater = provideLayoutInflater(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_main_search_result, parent, false)
        return SAItemViewHolder(itemView).also {
            it.itemView.setOnClickListener { _ ->
                val currentItem =
                    currentList.getOrNull(it.adapterPosition) ?: return@setOnClickListener
                clickListener?.onSearchResultClick(currentItem)
            }
        }
    }

    override fun onBindViewHolder(holder: SAItemViewHolder, position: Int) {
        val currentItem = currentList.getOrNull(position) ?: return

        Glide.with(holder.ivPoster)
            .load(currentItem.image)
            .error(R.drawable.bg_placeholder_movie)
            .placeholder(R.drawable.bg_placeholder_movie)
            .into(holder.ivPoster)

        holder.tvTitle.text =
            HtmlCompat.fromHtml(currentItem.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
        holder.rtbStar.rating = (currentItem.userRating.toFloatOrNull() ?: 0f) / 2
        holder.tvPublishDate.text = currentItem.pubDate
        holder.tvDirector.text = currentItem.director
        holder.tvActor.text = currentItem.actor
    }

    class SAItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPoster: AppCompatImageView = itemView.findViewById(R.id.iv_poster)
        val tvTitle: AppCompatTextView = itemView.findViewById(R.id.tv_title)
        val rtbStar: AppCompatRatingBar = itemView.findViewById(R.id.rtb_star)
        val tvPublishDate: AppCompatTextView = itemView.findViewById(R.id.tv_publish_date)
        val tvDirector: AppCompatTextView = itemView.findViewById(R.id.tv_director)
        val tvActor: AppCompatTextView = itemView.findViewById(R.id.tv_actor)
    }

    interface OnMainSearchResultClickListener {
        fun onSearchResultClick(item: SAMovieModel)
    }

    companion object {
        private val mDiffer: DiffUtil.ItemCallback<SAMovieModel> =
            object : DiffUtil.ItemCallback<SAMovieModel>() {
                override fun areItemsTheSame(
                    oldItem: SAMovieModel,
                    newItem: SAMovieModel
                ): Boolean {
                    return oldItem.subtitle == newItem.subtitle
                }

                override fun areContentsTheSame(
                    oldItem: SAMovieModel,
                    newItem: SAMovieModel
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}