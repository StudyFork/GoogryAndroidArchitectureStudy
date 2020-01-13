package com.example.androidarchitecture.ui.blog

import android.text.TextUtils
import androidx.databinding.ObservableField
import com.example.androidarchitecture.data.repository.NaverRepoInterface
import com.example.androidarchitecture.data.response.BlogData

class BlogViewModel(
    private val naverRepositroy: NaverRepoInterface
) {

    var inputKeyword = naverRepositroy.getBlogKeyword()
    val blankInputText = ObservableField<Any>() //get,set 자동생성? //ObservableField 를 이용해 xml 에 바인딩 할 수 있는 클래스를 만듬
    val renderItems = ObservableField<List<BlogData>>()
    val errorToast = ObservableField<Throwable>()
    val isListEmpty = ObservableField<Boolean>()


    fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        inputKeyword = s.toString()
    }


    suspend fun requestSearchHist() {
        naverRepositroy.getBlogHist().let {
            if (it.isNotEmpty()) {
                isListEmpty.set(it.isEmpty())
                renderItems.set(it)

            }
        }

    }


    fun onBtnSearch() {
        if (TextUtils.isEmpty(inputKeyword)) {
            blankInputText.set(Any())
        } else {
            naverRepositroy.saveBlogKeyword(inputKeyword) // 검색어 쉐어드에 저장.
            naverRepositroy.getBlog(inputKeyword, 1, 10,
                success = {
                    isListEmpty.set(false)
                    renderItems.set(it)
                }, fail = {
                    errorToast.set(Throwable())
                })
        }
    }


}