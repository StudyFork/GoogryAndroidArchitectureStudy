package com.camai.archtecherstudy.data.source.local

import android.content.Context
import com.camai.archtecherstudy.data.source.local.room.RecentSearchDatabase
import com.camai.archtecherstudy.data.source.local.room.RecentSearchName

object MovieLocalDataSourceImpl :
    MovieLocalDataSource {

    private var recentSearchDatabase: RecentSearchDatabase? = null

    override fun getInstance(context: Context): RecentSearchDatabase {
        if (recentSearchDatabase == null) {
            recentSearchDatabase = RecentSearchDatabase.getInstance(context)
        }
        return recentSearchDatabase!!
    }


    override fun getRecentMovieNameList(
        namelist: (List<RecentSearchName>) -> Unit
    ) {

        var run = Runnable {
            namelist(recentSearchDatabase?.recentSearchListDao()?.getListItems()!!)
        }
        val addThread = Thread(run)
        addThread.run()
    }

    override fun saveMovieName(keyword: String) {

        val insert = Runnable {
            var movieName =
                RecentSearchName()

            movieName.movieName = keyword
            //  movie name insert db
            recentSearchDatabase?.recentSearchListDao()?.insert(movieName)

        }

        val addThread = Thread(insert)
        addThread.run()
    }

    override fun deleteDb() {

        val insert = Runnable {
            //  data all delete
            recentSearchDatabase?.recentSearchListDao()?.deleteAll()
        }

        val addThread = Thread(insert)
        addThread.run()

    }

    override fun dbclose() {
        //  db close
        RecentSearchDatabase.destroyInstance()
    }


}