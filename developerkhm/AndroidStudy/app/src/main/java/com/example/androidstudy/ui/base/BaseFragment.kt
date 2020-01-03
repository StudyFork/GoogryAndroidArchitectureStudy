package com.example.androidstudy.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.androidstudy.database.SearchResultDatabase
import com.example.androidstudy.model.data.Item
import com.ironelder.androidarchitecture.view.AdapterSearch
import kotlinx.android.synthetic.main.layout_search_view.*


open class BaseFragment<B : ViewDataBinding>(var layoutId: Int) : Fragment(), BaseContract.View {

    protected val typeArray = arrayOf("blog", "news", "movie", "book")

    protected lateinit var binding: B
    protected lateinit var basePresenter: BasePresenter
    protected val viewModel: BaseViewModel = BaseViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        basePresenter = BasePresenter(this)
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)

        viewModel.database = SearchResultDatabase.getInstance(
            context?.applicationContext ?: (activity as Context).applicationContext
        )!!

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        viewModel.onLoading.addOnPropertyChangedCallback(object :
            androidx.databinding.Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(p0: androidx.databinding.Observable?, p1: Int) {

                viewModel.onLoading.get().let {
                    if (it!!) {
                        //TODO 로딩중~~
                    } else {
                        //TODO 로딩 아님~~~
                    }
                }
            }
        })

        viewModel.noDataError.addOnPropertyChangedCallback(object :
            androidx.databinding.Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(p0: androidx.databinding.Observable?, p1: Int) {

                viewModel.noDataError.get().let {
                    if (!it.isNullOrEmpty()) Toast.makeText(
                        context,
                        "에러!!!!!!!~~~",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

    }

    override fun onDataChanged(result: ArrayList<Item>) {
        (resultRecyclerView?.adapter as AdapterSearch).setItemList(result)
    }

    override fun onDataLoadLocal(query: String, result: ArrayList<Item>) {
        (resultRecyclerView?.adapter as AdapterSearch).setItemList(result)
    }

    override fun loadLocalDatabase(): SearchResultDatabase? {
        return SearchResultDatabase.getInstance(
            context?.applicationContext ?: (activity as Context).applicationContext
        )
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
