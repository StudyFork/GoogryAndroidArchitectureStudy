package com.example.seonoh.seonohapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.seonoh.seonohapp.contract.BaseContract

abstract class BaseFragment<P : BaseContract.Presenter, B : ViewDataBinding>(
    @LayoutRes
    private val layoutRes: Int
) : Fragment(), BaseContract.View<P> {

    protected lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,layoutRes,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        presenter.clearDisposable()
        super.onDestroyView()
    }
}