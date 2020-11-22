package com.example.googryandroidarchitecturestudy.ui.presenter

import com.example.googryandroidarchitecturestudy.data.repository.MovieRepository
import com.example.googryandroidarchitecturestudy.ui.contract.RecentContract

class RecentPresenter(
    view: RecentContract.View,
    repository: MovieRepository
) : BasePresenter(view), RecentContract.Presenter {

}