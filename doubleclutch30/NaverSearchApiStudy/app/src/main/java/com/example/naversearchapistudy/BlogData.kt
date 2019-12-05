package com.example.naversearchapistudy

data class BlogData (val items : List<BlogItems>)

data class BlogItems(

    val title : String,
    val contents : String,
    val blogger : String,
    val link : String )
