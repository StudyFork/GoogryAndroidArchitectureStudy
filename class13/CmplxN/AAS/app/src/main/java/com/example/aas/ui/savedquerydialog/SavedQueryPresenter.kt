package com.example.aas.ui.savedquerydialog

import android.app.Dialog
import com.example.aas.base.BasePresenter

class SavedQueryPresenter(override val view: SavedQueryContract.View) : BasePresenter(view),
    SavedQueryContract.Presenter {
    override fun getSavedQuery(savedQuery: Array<String>?): Dialog {
        val reversedList = savedQuery?.reversed() ?: listOf()
        return view.buildDialog(reversedList.toTypedArray())
    }
}