package com.jake.archstudy.base

import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleObserver

open class BaseViewModel : LifecycleObserver {

    val toast = ObservableField<String>()

}