package com.camai.archtecherstudy.observer

import androidx.databinding.ObservableField
import com.camai.archtecherstudy.data.repository.MovieRepositoryImpl
import com.camai.archtecherstudy.data.source.local.room.RecentSearchName

class RecentViewModel(private val MovieRepositoryImpl: MovieRepositoryImpl) {

    val clickKeyword = ObservableField<String>()
    val recentList = ObservableField<List<RecentSearchName>>()

    val dimissDialog = ObservableField<Unit>()
    val isSuccess = ObservableField<Unit>()
    val isFailed = ObservableField<Unit>()


    fun setRecentData() {
        MovieRepositoryImpl.getRecentSearchList(namelist = {
            if (it.isEmpty()) {
                isFailed.notifyChange()
            } else {
                recentList.set(it)

            }
        })
    }

    fun clickName(){
        isSuccess.notifyChange()

    }

    fun closeDialog() {
        dimissDialog.notifyChange()
    }
}