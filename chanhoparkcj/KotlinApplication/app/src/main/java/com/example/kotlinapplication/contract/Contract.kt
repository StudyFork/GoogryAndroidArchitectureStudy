package com.example.kotlinapplication.contract

import com.example.kotlinapplication.model.BlogItems
import com.example.kotlinapplication.model.ImageItems
import com.example.kotlinapplication.model.KinItems
import com.example.kotlinapplication.model.MovieItems

interface Contract {
    interface Presenter{
        fun loadData(type: String?, query: String)
    }
    interface View{
        fun resultMovie(movieItems:List<MovieItems>)
        fun resultImage(imageItems:List<ImageItems>)
        fun resultBlog(blogItems:List<BlogItems>)
        fun resultKin(kinItems:List<KinItems>)
        fun resultError(message:String)
    }
}