package com.hansung.firstproject.adapter

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.hansung.firstproject.data.MovieModel
import com.hansung.firstproject.databinding.MovieItemBinding
import com.hansung.firstproject.ui.movieinformation.MovieInformationActivity

@Suppress("DEPRECATION")
open class MovieHolder(private val binding: MovieItemBinding) :
    BaseViewHolder<MovieModel>(binding.root) {

    private lateinit var webPage: Uri

    init {
        itemView.setOnClickListener {
            MovieInformationActivity.getIntent(it.context).apply {
                putExtra(MovieInformationActivity.TAG, webPage.toString())
            }.also(itemView.context::startActivity)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun bindItems(item: MovieModel) {

        //클릭시 웹사이트 연결을 위한 uri 바인딩
        webPage = Uri.parse(item.link)

        with(binding) {
            this.item = item
            executePendingBindings()
        }
    }
}
