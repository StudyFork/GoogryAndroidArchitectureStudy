package com.example.myapplication.ui

import com.example.myapplication.data.local.MovieEntity

///**
// * Contract에서 View, Presenter interface선언해서, Contract를 거쳐서 View랑 Presenter를 접근해서 이용
// * 이유 : Presenter에서 View에 대한 작업을 못하게 하려고, 구분해서 사용
// * */
//interface SearchMovieContract {
//    interface View {
//        fun showToastMovieTitleIsEmpty()
//        fun addItems(it: List<MovieEntity>)
//        fun recordLog(it: String)
//    }
//
//    interface Presenter {
//        fun searchMovie(etMovieTitle: String)
//    }
//}