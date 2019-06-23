package com.architecturestudy.base

import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    fun showToast(msg: String?) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }
}