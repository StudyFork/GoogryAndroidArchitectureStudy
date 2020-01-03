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

    private lateinit var viewModel : BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        //TODO ViewModel이 여러개 뷰를 가질 수 있지만 화면당 하나의 뷰모델이 맞는건가 싶어서?
        //검색에 사용할, type를 주 생성자로 해줘야 하는지? 이렇게 하는게 좋은지... 모르...???

        viewModel = BaseViewModel()
        viewModel.type = typeArray[0]
        binding.vm = viewModel



        viewModel.onLoading.addOnPropertyChangedCallback(object : androidx.databinding.Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(p0: androidx.databinding.Observable?, p1: Int) {

                viewModel.onLoading.get().let {
                    if(it!!){
                        //TODO 로딩중~~
                    }else{
                        //TODO 로딩 아님~~~
                    }
                }
           }
        })

        viewModel.noDataError.addOnPropertyChangedCallback()

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
