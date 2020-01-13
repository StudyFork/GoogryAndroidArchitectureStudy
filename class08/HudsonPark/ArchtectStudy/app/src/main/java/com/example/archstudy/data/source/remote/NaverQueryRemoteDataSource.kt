package com.example.archstudy.data.source.remote

import com.example.archstudy.Item

interface NaverQueryRemoteDataSource {
    fun getMovie(query : String) : List<Item>
}