package com.example.studyapplication.ui.main

import com.example.studyapplication.ui.base.SearchFragment
import com.example.studyapplication.ui.main.movie.MovieFragment

interface MainContract {
    interface View {
        fun showMovieFragment(fragment : MovieFragment)
        fun showSearchFragment(fragment : SearchFragment)
    }

    interface Presenter {
        fun init()
        fun clickTab(id : Int)
    }
}