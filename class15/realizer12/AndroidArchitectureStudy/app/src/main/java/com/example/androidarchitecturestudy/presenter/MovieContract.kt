package com.example.androidarchitecturestudy.presenter

import com.example.androidarchitecturestudy.data.GetMovieInfo

interface MovieContract {
    //뷰에서 할일
    interface View {
        fun showMovieResultEmpty()
        fun showSearchQueryEmpty()
        fun hideKeyBoard()
        fun updateRecyclerView(movieList: List<GetMovieInfo.MovieData>)
        fun showError(throwable: Throwable)
    }

    //프레젠터에서 할일
    interface Presenter {
        fun getMovieData(searchQuery: String)
    }
}