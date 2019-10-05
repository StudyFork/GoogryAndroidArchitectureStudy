package com.jake.archstudy.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.jake.archstudy.ext.toast

abstract class BaseFragment<B : ViewDataBinding, P : BaseContract.Presenter>(
    @LayoutRes
    private val layoutId: Int
) : Fragment(),
    BaseContract.View<P> {

    internal lateinit var binding: B

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
        presenter.start()
    }

    override fun showToast(
        @StringRes
        stringResId: Int,
        duration: Int
    ) {
        toast(stringResId, duration)
    }

}