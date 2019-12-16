package com.example.androidstudy.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidstudy.api.data.Item


open class BaseFragment(var layoutId: Int) : Fragment(), BaseContract.View {

    protected val typeArray = arrayOf("blog", "news", "movie", "book")

    protected lateinit var basePresenter : BasePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        basePresenter = BasePresenter(this)

        return inflater.inflate(layoutId, container, false)
    }


    override fun onDataChanged(result: ArrayList<Item>) {

    }

    override fun showErrorMessage(msg: String?) {

    }

    override fun showNoSearchData() {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}
