package com.camai.archtecherstudy.ui.rencentdialog

import com.camai.archtecherstudy.data.repository.MovieRepositoryImpl

class RecentMoviePresenter(
    private val viewDialog: RecentMovieContract.View,
    private val MovieRepositoryImpl: MovieRepositoryImpl
): RecentMovieContract.Presenter{

    override fun setRecentData() {

    }

    override fun closeDialog() {

    }
}