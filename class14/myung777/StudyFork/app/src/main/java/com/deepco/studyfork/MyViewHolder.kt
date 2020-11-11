package com.deepco.studyfork

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.deepco.studyfork.data.model.Item
import kotlinx.android.synthetic.main.movie_item.view.*

class MyViewHolder(
    itemView: View
) :
    RecyclerView.ViewHolder(itemView) {

    private val movieTitleTextView = itemView.movie_title
    private val movieImageView = itemView.movie_image_view
    private val movieDirectorTextView = itemView.movie_director
    private val moviePubDateTextView = itemView.movie_pub_date

    fun bind(myModel: Item) {
        movieTitleTextView.text = getTitleString(myModel.title)
        movieDirectorTextView.text = getDirectorString(myModel.director)
        moviePubDateTextView.text = myModel.pubDate
        Glide.with(itemView)
            .load(myModel.image)
            .error(R.drawable.ic_null_image)
            .into(movieImageView)
    }

    private fun getTitleString(title: String?): String? {
        return title?.replace("<b>", "")?.replace("</b>", "")
    }

    private fun getDirectorString(director: String?): String? {
        return director?.let {
            if (it.isEmpty()) {
                ""
            } else {
                it.substring(0, director.length - 1).replace("|", ", ")
            }
        }
    }
}