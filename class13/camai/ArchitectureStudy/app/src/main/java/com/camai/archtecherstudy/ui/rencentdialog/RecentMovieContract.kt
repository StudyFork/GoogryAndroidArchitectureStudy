package com.camai.archtecherstudy.ui.rencentdialog

import com.camai.archtecherstudy.data.source.local.room.RecentSearchName

interface RecentMovieContract {
    interface View {
        fun setDataInsertToAdapter(data: List<RecentSearchName>)
        fun showEmptyFieldText()
        fun closeDialog()
    }

    interface Presenter {
        fun setRecentData()
        fun closeDialog()
    }
}