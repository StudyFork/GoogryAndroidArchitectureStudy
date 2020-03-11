package com.byiryu.study.model.source.local

import com.byiryu.study.model.entity.MovieItem

class MovieDataSource constructor( private val movieDao: MovieDao){

    fun getAll() : List<MovieItem>{
        return movieDao.getAll()
    }

    fun insertAll(items : List<MovieItem>) {
        movieDao.insertAll(items)
    }

    fun deleteAll(){
        movieDao.deleteAll()
    }
}