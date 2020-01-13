package com.example.archstudy.data.source.local

import com.example.archstudy.Item

interface NaverQueryLocalDataSource {
    fun getMovie(query : String) : List<Item>
}