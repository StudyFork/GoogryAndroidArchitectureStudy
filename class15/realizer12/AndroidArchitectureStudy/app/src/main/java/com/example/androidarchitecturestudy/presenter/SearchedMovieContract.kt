package com.example.androidarchitecturestudy.presenter

import com.example.androidarchitecturestudy.room.SearchedDataBase

interface SearchedMovieContract {

    //뷰에서 할일
    interface View{
        fun updateRecyclerView(recentSearchMovieList:List<String>)
    }

    //프레젠터에서 할일
    interface Presenter {
        fun getSearchedMovieList(database: SearchedDataBase)
    }

}