package com.example.androidstudy.ui.blog

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import com.example.androidstudy.R
import com.example.androidstudy.databinding.FragmentBlogBinding
import com.example.androidstudy.ui.base.BaseFragment
import com.example.androidstudy.ui.base.BaseViewModel
import com.ironelder.androidarchitecture.view.AdapterSearch
import java.util.*

class BlogFragment : BaseFragment<FragmentBlogBinding>(R.layout.fragment_blog) {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        viewModel.type = typeArray[0]
        binding.vm = viewModel

        setLayout()
    }

    private fun setLayout() {
        binding.searchLayout.searchEditText.onEditorAction(EditorInfo.IME_ACTION_SEARCH)

        binding.searchLayout.searchEditText.setOnEditorActionListener { searchEditText, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    basePresenter.search(searchEditText.text.toString(), typeArray[0])
                }
                else -> false
            }
            true
        }

        binding.searchLayout.resultRecyclerView.adapter =
            AdapterSearch(context, arrayListOf(), "blog")
    }
}

private fun Observable.addOnPropertyChangedCallback(function: () -> Unit) {

}
