package com.example.kotlinapplication.contract

import com.example.kotlinapplication.model.BlogItem
import com.example.kotlinapplication.model.ImageItem
import com.example.kotlinapplication.model.KinItem
import com.example.kotlinapplication.model.MovieItem

interface Contract {
    interface Presenter{
        fun loadData(type: String?, query: String)
    }
    interface View{
        fun getMovie(movieItems:List<MovieItem>)
        fun getImage(imageItems:List<ImageItem>)
        fun getBlog(blogItems:List<BlogItem>)
        fun getKin(kinItems:List<KinItem>)
        fun getError(message:String)
    }
}