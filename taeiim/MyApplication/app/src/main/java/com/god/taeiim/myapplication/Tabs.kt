package com.god.taeiim.myapplication

enum class Tabs { BLOG, NEWS, MOVIE, BOOK }

fun getSearchType(tab: Tabs) =
    when (tab) {
        Tabs.BLOG -> "blog"
        Tabs.NEWS -> "news"
        Tabs.MOVIE -> "movie"
        Tabs.BOOK -> "book"
    }