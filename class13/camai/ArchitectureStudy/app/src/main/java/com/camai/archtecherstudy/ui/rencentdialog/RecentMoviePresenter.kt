package com.camai.archtecherstudy.ui.rencentdialog

import com.camai.archtecherstudy.data.repository.MovieRepositoryImpl

class RecentMoviePresenter(
    private val viewDialog: RecentMovieContract.View,
    private val MovieRepositoryImpl: MovieRepositoryImpl
) : RecentMovieContract.Presenter {

    override fun setClickData(name: String) {
        viewDialog.setClickName(name)
    }

    override fun setRecentData() {
        MovieRepositoryImpl.getRecentSearchList(namelist = {
            if (it.isEmpty()) {
                viewDialog.showEmptyFieldText()
            } else {
                viewDialog.setDataInsertToAdapter(it)
            }
        })
    }

    override fun closeDialog() {
        viewDialog.closeDialog()
    }
}