package com.hhi.myapplication.base

import androidx.databinding.ObservableField

abstract class BaseViewModel {

    val visible = ObservableField<Boolean>()
}