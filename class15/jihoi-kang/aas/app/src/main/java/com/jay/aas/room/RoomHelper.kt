package com.jay.aas.room

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RoomHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {

    val movieDao: MovieDao by lazy {
        appDatabase.movieDao()
    }
    val searchHistoryDao: SearchHistoryDao by lazy {
        appDatabase.searchHistoryDao()
    }

    private val appDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "Movie.db",
    )
        .allowMainThreadQueries()
        .build()

}