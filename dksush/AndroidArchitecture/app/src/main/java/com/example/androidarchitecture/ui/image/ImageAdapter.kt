package com.example.androidarchitecture.ui.image

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidarchitecture.R
import com.example.androidarchitecture.data.response.ImageData
import com.example.androidarchitecture.ui.WebviewActivity
import kotlinx.android.synthetic.main.item_image.view.*

class ImageAdapter :
    RecyclerView.Adapter<ImageAdapter.ImageHolder>() {


    private val data = arrayListOf<ImageData>()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bind(data[position])
    }


    fun setData(items: List<ImageData>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    inner class ImageHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        lateinit var model: ImageData

        init {
            view.setOnClickListener {
                Intent(context, WebviewActivity::class.java).apply {
                    // 어플라이 끝에 그러니까 run 자리에서 뱉는건, intent 객체.  안에 애들은 객체를 뱉기전에 값넣기 혹은 초기화 작업시 좋다.
                    putExtra("link", model.link)
                }.run { context.startActivity(this) }
            }
        }


        fun bind(model: ImageData) {
            this.model = model
            with(view) {
                image_title.text = model.title
                try {
                    Glide.with(this)
                        .load(model.thumbnail)
                        .into(image_thum)
                } catch (e: Exception) {
                    Log.v("ImageAdapter", e.message!!)
                }


            }
        }
    }

}