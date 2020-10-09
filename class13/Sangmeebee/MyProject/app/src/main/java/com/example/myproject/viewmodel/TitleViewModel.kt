package com.example.myproject.viewmodel

import androidx.databinding.ObservableField
import com.example.myproject.data.repository.NaverRepositoryImpl
import com.example.myproject.data.source.local.NaverLocalDataSourceImpl
import com.example.myproject.data.source.remote.NaverRemoteDataSourceImpl

class TitleViewModel {

    private val repository =
        NaverRepositoryImpl(NaverLocalDataSourceImpl(), NaverRemoteDataSourceImpl())

    val titleList = ObservableField<List<String>>()

    fun callTitleList() {
        titleList.set(repository.readRecentSearchTitle())
    }
}
