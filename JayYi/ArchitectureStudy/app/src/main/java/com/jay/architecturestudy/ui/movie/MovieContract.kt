package com.jay.architecturestudy.ui.movie

import com.jay.architecturestudy.data.model.Movie
import com.jay.architecturestudy.ui.BaseContract

interface MovieContract {
    interface View : BaseContract.View<Presenter, Movie>

    interface Presenter : BaseContract.Presenter
}