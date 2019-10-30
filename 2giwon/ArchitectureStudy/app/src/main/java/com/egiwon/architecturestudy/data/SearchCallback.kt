package com.egiwon.architecturestudy.data

interface SearchCallback {
    fun onSuccess(searchContents: List<Content.Item>)
    fun onFailure(message: String)
}