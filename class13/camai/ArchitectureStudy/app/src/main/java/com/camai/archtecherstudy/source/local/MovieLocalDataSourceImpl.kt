package com.camai.archtecherstudy.source.local

import android.content.Context
import com.camai.archtecherstudy.source.local.room.RecentSearchListDatabase
import com.camai.archtecherstudy.source.local.room.RecentSearchNameList

object MovieLocalDataSourceImpl : MovieLocalDataSource {

    var recentSearchListDatabase: RecentSearchListDatabase? = null

    override fun getRecentMovieNameList(
        namelist: (List<RecentSearchNameList>) -> Unit,
        context: Context
    ) {
        recentSearchListDatabase = RecentSearchListDatabase.getInstance(context)

        var run = Runnable {
            namelist(recentSearchListDatabase?.recentSearchListDao()?.getListItems()!!)
        }
        val addThread = Thread(run)
        addThread.run()
    }

    override fun saveMovieName(keyword: String, context: Context) {
        recentSearchListDatabase = RecentSearchListDatabase.getInstance(context)

        val insert = Runnable {
            var movieName = RecentSearchNameList()

            movieName.movieName = keyword
            //  movie name insert db
            recentSearchListDatabase?.recentSearchListDao()?.insert(movieName)

        }

        val addThread = Thread(insert)
        addThread.run()
    }

    override fun deleteDb(context: Context) {
        recentSearchListDatabase = RecentSearchListDatabase.getInstance(context)

        val insert = Runnable {
            //  data all delete
            recentSearchListDatabase?.recentSearchListDao()?.deleteAll()
        }

        val addThread = Thread(insert)
        addThread.run()

    }

    override fun dbclose() {
        //  db close
        RecentSearchListDatabase.destroyInstance()
    }


}