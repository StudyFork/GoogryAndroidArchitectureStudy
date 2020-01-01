package com.jay.architecturestudy.data.source.local

import android.content.Context
import com.jay.architecturestudy.data.database.SearchHistoryDatabase

class NaverSearchLocalDataSourceImpl(
    private val context: Context
) : NaverSearchLocalDataSource {

    override val searchHistoryDatabase: SearchHistoryDatabase by lazy {
        SearchHistoryDatabase.getInstance(context)
    }
}
