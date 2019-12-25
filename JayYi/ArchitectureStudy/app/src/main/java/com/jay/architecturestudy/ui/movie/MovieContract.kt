package com.jay.architecturestudy.ui.movie

import com.jay.architecturestudy.data.model.Movie
import com.jay.architecturestudy.ui.BaseSearchContract

interface MovieContract {
    interface View : BaseSearchContract.View<Presenter, Movie>

    interface Presenter : BaseSearchContract.Presenter
}