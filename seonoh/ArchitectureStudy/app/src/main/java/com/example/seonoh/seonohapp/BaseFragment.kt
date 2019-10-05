package com.example.seonoh.seonohapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.seonoh.seonohapp.contract.BaseContract

abstract class BaseFragment(
    @LayoutRes
    private val layoutRes: Int
) : Fragment(), BaseContract.View {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(layoutRes, container, false)
    }

    override fun onDestroyView() {
        presenter.clearDisposable()
        super.onDestroyView()
    }
}