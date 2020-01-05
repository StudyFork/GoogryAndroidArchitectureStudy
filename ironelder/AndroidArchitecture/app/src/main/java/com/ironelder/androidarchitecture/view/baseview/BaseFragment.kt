package com.ironelder.androidarchitecture.view.baseview

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.ironelder.androidarchitecture.R


abstract class BaseFragment<BINDING : ViewDataBinding>(
    private val mLayoutResId: Int
) : Fragment() {

    protected lateinit var binding: BINDING

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, mLayoutResId, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.search_menu, menu)
        doCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        doViewCreated(view, savedInstanceState)
        doLoadFromDatabase()
    }

    abstract fun doCreateOptionsMenu(menu: Menu, inflater: MenuInflater)
    abstract fun doViewCreated(view: View, savedInstanceState: Bundle?)
    abstract fun doLoadFromDatabase()

}