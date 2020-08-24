package com.camai.archtecherstudy.adapter

import android.content.Context
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
import com.camai.archtecherstudy.model.Items

class MovieSearchAdapter(
    private val context: Context
) : RecyclerView.Adapter<MovieSearchAdapter.ViewHolder>() {

    private val movieInfoArrayList = mutableListOf<Items>()

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int ) : ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieInfoArrayList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieInfoArrayList.get(position), context)

    }
    //  Update Movie List Data
    fun setClearAndAddList( movielist : ArrayList<Items>){
        with(this.movieInfoArrayList){
            clear()
            addAll(movielist)
        }
        //  recyclerview set Data Change
        notifyDataSetChanged()
    }

    class ViewHolder( itemView : View? ) : RecyclerView.ViewHolder(itemView!!) {

        val poster = itemView?.findViewById<ImageView>(R.id.img_poster)
        val title = itemView?.findViewById<TextView>(R.id.txt_title)
        val user_rating = itemView?.findViewById<RatingBar>(R.id.rb_user_rating)
        val pub_data = itemView?.findViewById<TextView>(R.id.txt_pub_data)
        val director = itemView?.findViewById<TextView>(R.id.txt_director)
        val actor = itemView?.findViewById<TextView>(R.id.txt_actor)

        fun bind( itemInfo : Items, context : Context) {

            title?.text = itemInfo.title
            pub_data?.text = itemInfo.pubDate
            director?.text = itemInfo.director
            actor?.text = itemInfo.actor
            user_rating?.rating = itemInfo.userRating

            if (poster != null) {
                Glide.with(context)
                    .load(itemInfo.image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(poster)
            }

            //  Movie Content WebView Call
            itemView.setOnClickListener {
                val webpage = Uri.parse(itemInfo.link)
                val webIntent = Intent(Intent.ACTION_VIEW, webpage)
                itemView.context.startActivity(webIntent)
            }

        }

    }
}
