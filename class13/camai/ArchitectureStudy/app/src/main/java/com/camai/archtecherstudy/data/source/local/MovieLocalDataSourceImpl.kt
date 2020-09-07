package com.camai.archtecherstudy.data.source.local

import android.content.Context
import com.camai.archtecherstudy.data.source.local.room.RecentSearchListDatabase
import com.camai.archtecherstudy.data.source.local.room.RecentSearchName

object MovieLocalDataSourceImpl :
    MovieLocalDataSource {

    private var recentSearchListDatabase: RecentSearchListDatabase? = null

    override fun getInstance(context: Context): RecentSearchListDatabase{
        if( recentSearchListDatabase == null){
            recentSearchListDatabase = RecentSearchListDatabase.getInstance(context)
        }
        return recentSearchListDatabase!!
    }


    override fun getRecentMovieNameList(
        namelist: (List<RecentSearchName>) -> Unit
    ) {

        var run = Runnable {
            namelist(recentSearchListDatabase?.recentSearchListDao()?.getListItems()!!)
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
            recentSearchListDatabase?.recentSearchListDao()?.insert(movieName)

        }

        val addThread = Thread(insert)
        addThread.run()
    }

    override fun deleteDb() {

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