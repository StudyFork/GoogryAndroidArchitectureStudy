package com.aiden.aiden.architecturepatternstudy.base

import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    fun toastM(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}