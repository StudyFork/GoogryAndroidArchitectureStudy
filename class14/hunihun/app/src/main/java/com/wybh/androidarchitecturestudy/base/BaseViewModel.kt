package com.wybh.androidarchitecturestudy.base
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {
    val progressVisible = MutableLiveData<Int>(View.GONE)
}