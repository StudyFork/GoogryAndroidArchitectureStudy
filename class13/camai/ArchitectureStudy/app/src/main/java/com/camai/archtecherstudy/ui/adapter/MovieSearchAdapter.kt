package com.camai.archtecherstudy.ui.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.camai.archtecherstudy.R
import com.camai.archtecherstudy.R.string.*
import com.camai.archtecherstudy.data.model.Items

class MovieSearchAdapter : RecyclerView.Adapter<MovieSearchAdapter.ViewHolder>() {

    private val movieInfoArrayList = mutableListOf<Items>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)

        return ViewHolder(
            view
        ).click{ position ->
            //  Movie Content WebView Call
            val webpage = Uri.parse(movieInfoArrayList.get(position).link)
            val webIntent = Intent(Intent.ACTION_VIEW, webpage)
            parent.context.startActivity(webIntent)
        }
    }

    //  itemView setOnClickListener Extension
    fun <T : RecyclerView.ViewHolder> T.click(event: (position: Int) -> Unit) : T {
        itemView.setOnClickListener{
            event.invoke(adapterPosition)
        }
        return this
    }

    override fun getItemCount() = movieInfoArrayList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieInfoArrayList.get(position))
    }

    //  Update Movie List Data
    fun setClearAndAddList(movielist: ArrayList<Items>) {
        //  adaper clear and movielist data add 
        with(movieInfoArrayList) {
            clear()
            addAll(movielist)
        }
        //  recyclerview set Data Change
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        private val poster = itemView?.findViewById<ImageView>(R.id.img_poster)
        private val title = itemView?.findViewById<TextView>(R.id.txt_title)
        private val user_rating = itemView?.findViewById<RatingBar>(R.id.rb_user_rating)
        private val pub_data = itemView?.findViewById<TextView>(R.id.txt_pub_data)
        private val director = itemView?.findViewById<TextView>(R.id.txt_director)
        private val actor = itemView?.findViewById<TextView>(R.id.txt_actor)

        @SuppressLint("SetTextI18n")
        fun bind(itemInfo: Items) {

            title?.text = itemInfo.title
            pub_data?.text = itemView.context.getString(movie_date) + itemInfo.pubDate
            director?.text = itemView.context.getString(movie_director) + itemInfo.director
            actor?.text = itemView.context.getString(movie_actor) +itemInfo.actor
            user_rating?.rating = itemInfo.userRating

            if (poster != null) {
                Glide.with(itemView.context)
                    .load(itemInfo.image)
                    .error(R.drawable.ic_input_trail_error)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(poster)
            }
        }
    }
}
