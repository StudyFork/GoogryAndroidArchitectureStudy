package com.egiwon.architecturestudy.data.source.local

import com.egiwon.architecturestudy.data.source.NaverDataSource

class NaverLocalDataSource(
    private val contentDao: ContentDao
) : NaverDataSource {

    companion object {
        private var instance: NaverLocalDataSource? = null

        fun getInstance(contentDao: ContentDao) = instance ?: NaverLocalDataSource(contentDao)
            .apply { instance = this }

    }

}