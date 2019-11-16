package com.ironelder.androidarchitecture.view.baseview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.ironelder.androidarchitecture.R


abstract class BaseFragment<in VIEW : BaseContract.View, PRESENTER : BaseContract.Presenter<VIEW>>(
    private val mLayoutResId: Int
) : Fragment(), BaseContract.View {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        @Suppress("UNCHECKED_CAST")
        presenter.attachView(this as VIEW)
        return inflater.inflate(mLayoutResId, container, false)
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

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    abstract val presenter: PRESENTER
    abstract fun doCreateOptionsMenu(menu: Menu, inflater: MenuInflater)
    abstract fun doViewCreated(view: View, savedInstanceState: Bundle?)
}