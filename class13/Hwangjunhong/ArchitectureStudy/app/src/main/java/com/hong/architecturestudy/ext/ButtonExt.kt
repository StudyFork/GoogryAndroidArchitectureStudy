package com.hong.architecturestudy.ext

import android.view.View
import android.widget.Button
import androidx.databinding.BindingAdapter
import androidx.databinding.Observable
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.hong.architecturestudy.ui.main.MainViewModel

@BindingAdapter("fm", "dialogFragment", "bindVm")
fun Button.showRecentSearchList(
    manager: FragmentManager? = null,
    dialogFragment: DialogFragment? = null,
    vm: MainViewModel? = null
) {

    this.setThrottleClick(View.OnClickListener {
        manager?.let {
            dialogFragment?.show(manager, "fragmentDialog")
        }
    })

    vm?.isVisible?.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            dialogFragment?.dismiss()
        }
    })
}