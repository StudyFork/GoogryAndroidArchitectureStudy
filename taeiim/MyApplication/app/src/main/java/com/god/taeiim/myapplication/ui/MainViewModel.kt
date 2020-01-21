package com.god.taeiim.myapplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _tabSelectedItem = MutableLiveData<Int>()
    val tabSelectedItem get() = _tabSelectedItem

}