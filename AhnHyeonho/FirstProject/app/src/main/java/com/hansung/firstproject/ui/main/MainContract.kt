package com.hansung.firstproject.ui.main

import com.hansung.firstproject.data.MovieResponseModel

interface MainContract {
    interface View {
        fun removeKeyboard(): Boolean

        fun showErrorKeywordEmpty()

        fun showErrorByErrorMessage(errorMessage: String)

        fun showErrorEmptyList()

        fun addItemToAdapter(response: MovieResponseModel)
    }

    interface Presenter {
        fun doSearch(keyword: String)
    }
}