package com.hwaniiidev.architecture.data.source.local

import android.content.Context
import com.hwaniiidev.architecture.model.Item

class NaverMovieLocalDataSourceImpl : NaverMovieLocalDataSource {
    private var moviesDB: MoviesDB? = null

    override fun cachingMovies(context: Context, query: String, movies: ArrayList<Item>) {
        moviesDB = MoviesDB.getInstance(context)

        val addMovies = Runnable {
            val cachedMovies = moviesDB?.moviesDao()?.getCachedData(query)

            //이미 저장되어 있는 영화 제거
            var iterator = movies.listIterator()
            while (iterator.hasNext()) {
                val inputMovie = iterator.next()
                if (cachedMovies != null) {
                    for (cashedData in cachedMovies) {
                        if (cashedData.title.equals(inputMovie.title)
                            && cashedData.director.equals(inputMovie.director)
                        ) {
                            iterator.remove()
                            break
                        }
                    }
                }
            }

            if (moviesDB != null) {
                //로컬에 저장
                for (it in movies) {
                    //검색어 맵핑
                    it.query = query
                    moviesDB?.moviesDao()?.insert(movies = it)
                }
            }
            moviesDB?.detroyInstance()
        }
        val addThread = Thread(addMovies)
        addThread.start()

    }

    /*override fun getCachedMovies() {
        TODO("Not yet implemented")
    }*/
}