package com.aiden.aiden.architecturepatternstudy.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<B : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int
) : Fragment() {

    protected lateinit var binding: B
        private set

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    protected fun binding(action: B.() -> Unit) {
        binding.run(action)
    }

    fun toastM(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}