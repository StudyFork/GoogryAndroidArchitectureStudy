package com.hhi.myapplication.base

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel(){

    val visible = ObservableField<Boolean>()
}