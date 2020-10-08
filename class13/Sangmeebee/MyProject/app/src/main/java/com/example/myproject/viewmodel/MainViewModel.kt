package com.example.myproject.viewmodel

import androidx.databinding.ObservableField
import com.example.myproject.data.model.Items
import com.example.myproject.data.repository.NaverRepository
import com.example.myproject.data.repository.NaverRepositoryImpl
import com.example.myproject.data.source.local.NaverLocalDataSourceImpl
import com.example.myproject.data.source.remote.NaverRemoteDataSourceImpl

class MainViewModel {

    private val repository: NaverRepository =
        NaverRepositoryImpl(NaverLocalDataSourceImpl(), NaverRemoteDataSourceImpl())
    val query = ObservableField<String>()
    val movieList = ObservableField<List<Items>>()
    val msg = ObservableField<String>()
    val isEmpty = ObservableField<Boolean>()
    val showToastMsg = ObservableField<Unit>()
    val isVisible = ObservableField<Unit>()
    val showDialog = ObservableField<Unit>()

    fun callMovieList() {
        val query = query.get() ?: return
        if (query.isEmpty()) {
            msg.set("empty")
            isEmpty.set(true)
            showToastMsg.notifyChange()
        } else {
            repository.getMovieList(query, {
                if (it.isEmpty()) {
                    msg.set("empty_result")
                    isEmpty.set(true)
                    showToastMsg.notifyChange()
                } else {
                    msg.set("success")
                    isEmpty.set(false)
                    showToastMsg.notifyChange()
                    movieList.set(it)
                }
            }, {
                msg.set("error")
                isEmpty.set(true)
                showToastMsg.notifyChange()
            })
        }
    }

    fun dismissDialog() {
        isVisible.notifyChange()
    }

    fun callDialog(){
        showDialog.notifyChange()
    }
}
