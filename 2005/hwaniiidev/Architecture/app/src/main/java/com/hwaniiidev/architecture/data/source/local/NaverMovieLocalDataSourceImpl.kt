package com.hwaniiidev.architecture.data.source.local

import android.content.Context
import android.util.Log
import com.hwaniiidev.architecture.model.Item

class NaverMovieLocalDataSourceImpl : NaverMovieLocalDataSource {
    private var moviesDB: MoviesDB? = null

    override fun cachingMovies(context: Context, query: String, movies: ArrayList<Item>) {
        moviesDB = MoviesDB.getInstance(context)

        //removeOverlap(query,movies)

        val addCatQuery = Runnable {
            if (moviesDB != null) {
                for (it in movies) {
                    it.query = query
                    moviesDB?.moviesDao()?.insert(movies = it)
                }
            }
            moviesDB?.detroyInstance()
        }
        val addThread = Thread(addCatQuery)
        addThread.start()
    }

    /*override fun getCachedMovies() {
        TODO("Not yet implemented")
    }

    override fun removeOverlap(query: String,cachingMovies: ArrayList<Item>): ArrayList<Item> {
        var cachedMovies : List<Item>? = null
        val addCatQuery = Runnable {
            cachedMovies  = moviesDB?.moviesDao()?.getCachedData(query)
            for(it in cachedMovies!!){
                Log.d("ttt","cached = id : ${it.id}, title : ${it.title}, director : ${it.director}")
            }

            var iterator = cachingMovies.listIterator()
            while (iterator.hasNext()){
                val cashingData = iterator.next()
                if (cachedMovies != null) {
                    for(cashedData in cachedMovies!!){
                        if(cashedData.title.equals(cashingData.title)
                            && cashedData.director.equals(cashingData.director)){
                            iterator.remove()
                        }
                    }

                }
            }


            for(it in cachingMovies){
                Log.d("ttt","before = id : ${it.id}, title : ${it.title}, director : ${it.director}")
            }
        }
        val addThread = Thread(addCatQuery)
        addThread.start()

        return cachingMovies
    }*/
}