package com.example.studyapplication.ui.main.base

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.annotation.LayoutRes
import com.example.studyapplication.R
import com.example.studyapplication.ui.base.BaseFragment

open class BaseSearchFragment(
    @LayoutRes override val fragmentId: Int
) : BaseFragment(fragmentId),
    BaseSearchContract.View {

    @SuppressLint("ShowToast")
    override fun toastErrorMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun toastEmptyQueryMessage() {
        Toast.makeText(context, getString(R.string.error_message_empty_query), Toast.LENGTH_SHORT).show()
    }
}