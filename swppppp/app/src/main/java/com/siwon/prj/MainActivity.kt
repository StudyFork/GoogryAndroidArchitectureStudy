package com.siwon.prj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    
    // 영화리스트 어댑터
    inner class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.movieHolder> {
        val items : ArrayList<Movie>

        constructor(items: ArrayList<Movie>) {
            this.items = items
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): movieHolder
            = movieHolder(LayoutInflater.from(baseContext).inflate(R.layout.list_item, parent, false))

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: movieHolder, position: Int) {
            holder.bind(items[position])
            holder.itemView.setOnClickListener {
                val toast = Toast.makeText(baseContext, "웹뷰로 이동", Toast.LENGTH_SHORT)
                toast.show()
            }
        }

        inner class movieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bind(item: Movie){
                Glide.with(itemView).load(item.image).into(itemView.findViewById(R.id.img_view))
                itemView.findViewById<TextView>(R.id.movie_name).text = item.title
                itemView.findViewById<RatingBar>(R.id.score).rating = item.userRating.toFloat()/2f
                itemView.findViewById<TextView>(R.id.pub_date).text = item.pubDate
                itemView.findViewById<TextView>(R.id.director).text = item.director
                itemView.findViewById<TextView>(R.id.actor).text = item.actor
            }
        }

    }
}
