package com.example.androidstudy.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidstudy.model.data.Item
import com.ironelder.androidarchitecture.view.AdapterSearch
import kotlinx.android.synthetic.main.layout_search_view.*


open class BaseFragment(var layoutId: Int) : Fragment(), BaseContract.View {

    protected val typeArray = arrayOf("blog", "news", "movie", "book")

    protected lateinit var basePresenter: BasePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        basePresenter = BasePresenter(this)

        return inflater.inflate(layoutId, container, false)
    }


    override fun onDataChanged(result: ArrayList<Item>) {
        (resultRecyclerView?.adapter as AdapterSearch).setItemList(result)
    }

    override fun showErrorMessage(msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT)
            .show()
    }

    override fun showNoSearchData() {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun hideKeyboard(view: View) {
        val inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.applicationWindowToken, 0)
    }
}
