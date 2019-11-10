package com.practice.achitecture.myproject.main

import com.practice.achitecture.myproject.model.SearchedItem

interface SearchedItemClickListener {
    fun onItemClick(item: SearchedItem)
}