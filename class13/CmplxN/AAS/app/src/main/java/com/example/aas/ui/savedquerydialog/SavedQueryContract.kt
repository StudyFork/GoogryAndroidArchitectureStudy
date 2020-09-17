package com.example.aas.ui.savedquerydialog

import android.app.Dialog
import com.example.aas.base.BaseContract

interface SavedQueryContract {
    interface View : BaseContract.View {
        fun buildDialog(savedQuery: Array<String>): Dialog
    }

    interface Presenter : BaseContract.Presenter {
        fun getSavedQuery(savedQuery: Array<String>?): Dialog
    }
}