package com.example.myapplication.data.repository

//class MovieRepositoryDataSet : Application() {
//    lateinit var movieRepository: MovieRepository
//    private lateinit var movieRemoteDataSource: MovieRemoteDataSource
//    private lateinit var movieLocalDataSource: MovieLocalDataSource
//    private lateinit var movieDao: MovieDao
//
//
//    override fun onCreate() {
//        super.onCreate()
//        inject()
//    }
//
//    private fun inject() {
//        movieDao = MovieDatabase.getDatabase(this).movieDao()
//        movieRemoteDataSource =
//            MovieRemoteDataSourceImpl()
//        movieLocalDataSource = MovieLocalDataSourceImpl(movieDao)
//        movieRepository = MovieRepositoryImpl(movieRemoteDataSource, movieLocalDataSource)
//    }
//}