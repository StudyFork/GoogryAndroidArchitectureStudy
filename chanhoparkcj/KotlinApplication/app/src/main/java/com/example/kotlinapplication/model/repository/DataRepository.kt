package com.example.kotlinapplication.model.repository

import com.example.kotlinapplication.model.BlogItems
import com.example.kotlinapplication.model.ImageItems
import com.example.kotlinapplication.model.KinItems
import com.example.kotlinapplication.model.MovieItems

interface DataRepository {
    interface call{
        fun callMovieResources(query: String)
        fun callImageResources(query: String)
        fun callBlogResources(query: String)
        fun callKinResources(query: String)
    }
    interface response{
        fun responseMovieResources(movieList: List<MovieItems>)
        fun responseImageResources(imageList: List<ImageItems>)
        fun responseBlogResources(blogList: List<BlogItems>)
        fun responseKinResources(kinList: List<KinItems>)
    }

}