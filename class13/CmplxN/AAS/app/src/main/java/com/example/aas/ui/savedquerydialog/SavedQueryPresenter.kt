package com.example.aas.ui.savedquerydialog

import com.example.aas.base.BasePresenter

class SavedQueryPresenter(override val view: SavedQueryContract.View) : BasePresenter(view),
    SavedQueryContract.Presenter {
    override fun getSavedQuery(savedQuery: Array<String>?): List<String> =
        savedQuery?.reversed() ?: listOf()
}