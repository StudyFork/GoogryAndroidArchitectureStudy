package com.jay.architecturestudy.data.source.local

import com.jay.architecturestudy.data.database.SearchHistoryDatabase

interface NaverSearchLocalDataSource {
    val searchHistoryDatabase: SearchHistoryDatabase
}