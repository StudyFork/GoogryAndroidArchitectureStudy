package com.aiden.aiden.architecturepatternstudy.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainSearchViewModel : ViewModel() {

    val searchKeyword = MutableLiveData<String>()

    fun clearKeyword() {

        searchKeyword.value = ""

    }

}
