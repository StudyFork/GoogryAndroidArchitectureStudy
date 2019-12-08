package com.example.kotlinapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.adapter.viewholder.MovieViewHolder
import com.example.kotlinapplication.model.BlogItems
import com.example.kotlinapplication.model.ImageItems
import com.example.kotlinapplication.model.KinItems
import com.example.kotlinapplication.model.MovieItems
import java.util.*

class ListNaverAdapter(
    listener: ItemListener,
    val items: List<Objects>,
    val context: Context?,
    val type: String
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mListener: ItemListener = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (type) {
            "영화" -> {
                return MovieViewHolder(
                    LayoutInflater.from(context).inflate(
                        R.layout.movie_list_item,
                        parent,
                        false
                    )
                )

            }
            else->{
                return return MovieViewHolder(
                    LayoutInflater.from(context).inflate(
                        R.layout.movie_list_item,
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieViewHolder) {
            holder.bind(items.get(position), mListener, type)
        }else if(holder is BlogViewHolder){
            holder.bind(items.get(position),mListener,type)
        }else if(holder is ImageViewHolder){
            holder.bind(items.get(position),mListener,type)
        }else if(holder is KinViewHolder){
            holder.bind(items.get(position),mListener,type)
        }
    }

    interface ItemListener {
        fun onMovieItemClick(movieItems: MovieItems)
        fun onBlogItemClick(blogItems:BlogItems)
        fun onImageItemClick(imageItems: ImageItems)
        fun onKinItemClick(kinItems: KinItems)
    }


}