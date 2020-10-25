package com.camai.archtecherstudy.data.source.local

import com.camai.archtecherstudy.data.source.local.room.RecentSearchDatabase
import com.camai.archtecherstudy.data.source.local.room.RecentSearchName
import com.camai.archtecherstudy.ui.ApplicationContext
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Singleton

class MovieLocalDataSourceImpl @Inject constructor() :
    MovieLocalDataSource {

    //  private var recentSearchDatabase: RecentSearchDatabase? = null
    private var recentSearchDatabase: RecentSearchDatabase? =
        RecentSearchDatabase.getInstance(ApplicationContext.movieapplicationContext())


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
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class MovieLocalDataSourceModule {
    @Binds
    @Singleton
    abstract fun bindLocalRepository(movieLocalDataSourceImpl: MovieLocalDataSourceImpl): MovieLocalDataSource
}