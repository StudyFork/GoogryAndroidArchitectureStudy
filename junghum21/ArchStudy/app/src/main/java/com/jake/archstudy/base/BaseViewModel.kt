package com.jake.archstudy.base

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val toast = ObservableField<String>()

}