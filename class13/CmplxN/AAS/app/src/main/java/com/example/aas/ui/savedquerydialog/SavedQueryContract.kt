package com.example.aas.ui.savedquerydialog

import com.example.aas.base.BaseContract

interface SavedQueryContract {
    interface View : BaseContract.View {
        fun getSavedQuery(savedQuery: Array<String>)
    }

    interface Presenter : BaseContract.Presenter {
        fun getSavedQuery(savedQuery: Array<String>?)
    }
}