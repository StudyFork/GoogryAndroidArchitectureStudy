package com.example.kotlinapplication.view.home.presenter

import com.example.kotlinapplication.data.BlogItem
import com.example.kotlinapplication.data.ImageItem
import com.example.kotlinapplication.data.KinItem
import com.example.kotlinapplication.data.MovieItem

interface Contract {
    interface Presenter {
        fun loadData(type: Int?, query: String)
    }

    interface View {
        fun getMovie(movieItems: List<MovieItem>)
        fun getImage(imageItems: List<ImageItem>)
        fun getBlog(blogItems: List<BlogItem>)
        fun getKin(kinItems: List<KinItem>)
        fun getError(message: String)
    }
}