package com.camai.archtecherstudy.ui.rencentdialog

import com.camai.archtecherstudy.data.source.local.room.RecentSearchName

interface RecentMovieContract {
    interface View {
        fun setClickName(name: String)
        fun setDataInsertToAdapter(data: List<RecentSearchName>)
        fun showEmptyFieldText()
        fun closeDialog()
    }

    interface Presenter {
        fun setClickData(name: String)
        fun setRecentData()
        fun closeDialog()
    }
}