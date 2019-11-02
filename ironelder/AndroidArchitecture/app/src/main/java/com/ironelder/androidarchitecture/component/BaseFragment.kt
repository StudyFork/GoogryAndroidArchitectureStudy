package com.ironelder.androidarchitecture.component

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.ironelder.androidarchitecture.R

abstract class BaseFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return doCreateView(inflater, container, savedInstanceState)
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
    }

    abstract fun doCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    abstract fun doCreateOptionsMenu(menu: Menu, inflater: MenuInflater)
    abstract fun doViewCreated(view: View, savedInstanceState: Bundle?)
}