package com.ironelder.androidarchitecture.view.baseview

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ironelder.androidarchitecture.R
import com.ironelder.androidarchitecture.databinding.FragmentMainBinding


abstract class BaseFragment<in VIEW : BaseContract.View, PRESENTER : BaseContract.Presenter<VIEW>>(
    private val mLayoutResId: Int
) : Fragment(), BaseContract.View {

    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        @Suppress("UNCHECKED_CAST")
        presenter.attachView(this as VIEW)
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

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
    abstract val presenter: PRESENTER
    abstract fun doCreateOptionsMenu(menu: Menu, inflater: MenuInflater)
    abstract fun doViewCreated(view: View, savedInstanceState: Bundle?)
    abstract fun doLoadFromDatabase()

}