package com.architecturestudy.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.architecturestudy.util.CompositeDisposable

abstract class BaseFragment<B : ViewDataBinding>(
    @LayoutRes
    private val resource: Int
) : Fragment() {

    protected lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            resource,
            container,
            false
        )
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onDestroyView() {
        CompositeDisposable.dispose()
        super.onDestroyView()
    }

    fun showToast(msg: String?) = Toast.makeText(
        activity,
        msg,
        Toast.LENGTH_SHORT
    ).show()
}