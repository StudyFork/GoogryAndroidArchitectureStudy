package com.example.kotlinapplication.data.model

import androidx.databinding.BaseObservable

data class BlogItem(
    val title: String,
    val link: String,
    val description: String,
    val bloggername: String,
    val bloggerlink: String
):BaseObservable()