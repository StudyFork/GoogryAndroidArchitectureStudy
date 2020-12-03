package com.hhi.myapplication.base

import android.view.View
import androidx.databinding.ObservableField

abstract class BaseViewModel {

    val loading = ObservableField(View.GONE)
}