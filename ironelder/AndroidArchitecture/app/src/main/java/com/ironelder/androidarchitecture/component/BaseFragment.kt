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
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.search_menu, menu)
        doCreateOptionsMenu(menu, inflater)
    }

    open fun doCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {}
}