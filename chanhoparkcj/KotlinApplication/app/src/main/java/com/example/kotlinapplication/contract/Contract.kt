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
        fun resultMovie(movieItems:List<MovieItem>)
        fun resultImage(imageItems:List<ImageItem>)
        fun resultBlog(blogItems:List<BlogItem>)
        fun resultKin(kinItems:List<KinItem>)
        fun resultError(message:String)
    }
}