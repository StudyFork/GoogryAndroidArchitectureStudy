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
import androidx.lifecycle.ViewModel
import com.aiden.aiden.architecturepatternstudy.BR

abstract class BaseFragment<B : ViewDataBinding, VM : ViewModel>(
    @LayoutRes private val layoutResId: Int
) : Fragment() {

    protected lateinit var binding: B
        private set

    abstract val viewModel: Lazy<VM>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding {
            binding.lifecycleOwner = this@BaseFragment
            binding.setVariable(BR.vm, viewModel.value)
        }
    }

    protected fun binding(action: B.() -> Unit) {
        binding.run(action)
    }

    fun toastM(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}