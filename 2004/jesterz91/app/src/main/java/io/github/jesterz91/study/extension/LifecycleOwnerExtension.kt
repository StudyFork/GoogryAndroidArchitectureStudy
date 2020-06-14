package io.github.jesterz91.study.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, onChanged: (T) -> Unit) {
    liveData.observe(this, Observer(onChanged::invoke))
}