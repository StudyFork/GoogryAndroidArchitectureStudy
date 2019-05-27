package com.namjackson.archstudy.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<B : ViewDataBinding, P : BaseContract.Presenter>(private val layoutId: Int) : Fragment(),
    BaseContract.View {
    protected lateinit var binding: B
    protected lateinit var presenter: P

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun showError(errorMsg: String) {
        Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
    }
}