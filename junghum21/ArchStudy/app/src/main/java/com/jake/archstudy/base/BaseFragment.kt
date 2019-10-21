package com.jake.archstudy.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.jake.archstudy.ext.toast

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes
    private val layoutId: Int
) : Fragment() {

    protected lateinit var binding: B

    abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.toast.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                toast(viewModel.toast.get() ?: return)
            }
        })
    }

}