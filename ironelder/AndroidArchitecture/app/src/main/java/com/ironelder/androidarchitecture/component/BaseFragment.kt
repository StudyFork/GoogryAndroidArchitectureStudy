package com.ironelder.androidarchitecture.component

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.ironelder.androidarchitecture.R

abstract class BaseFragment : Fragment() {
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

    open fun doCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {}
    open fun doViewCreated(view: View, savedInstanceState: Bundle?) {}
}