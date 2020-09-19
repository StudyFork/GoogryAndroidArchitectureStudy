package com.example.aas.ui.savedquerydialog

import com.example.aas.base.BasePresenter

class SavedQueryPresenter(override val view: SavedQueryContract.View) : BasePresenter(view),
    SavedQueryContract.Presenter {
    override fun getSavedQuery(savedQuery: Array<String>?) {
        val reversedList = savedQuery?.reversed() ?: listOf()
        view.storeSavedQuery(reversedList.toTypedArray())
    }
}