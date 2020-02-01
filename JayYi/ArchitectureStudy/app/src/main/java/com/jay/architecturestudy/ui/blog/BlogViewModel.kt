package com.jay.architecturestudy.ui.blog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.jay.architecturestudy.data.model.Blog
import com.jay.architecturestudy.data.repository.NaverSearchRepository
import com.jay.architecturestudy.ui.BaseViewModel
import com.jay.architecturestudy.ui.ViewType
import com.jay.architecturestudy.util.addTo
import com.jay.architecturestudy.util.singleIoMainThread

class BlogViewModel(
    override val repository: NaverSearchRepository
): BaseViewModel<Blog>(repository) {
    override val _data: MutableLiveData<List<Blog>> = MutableLiveData()
    val data: LiveData<List<Blog>>
        get() = _data

    val viewType = Transformations.map(data) { list ->
        if (list.isNotEmpty()) {
            ViewType.VIEW_SEARCH_RESULT
        } else {
            ViewType.VIEW_SEARCH_NO_RESULT
        }
    }

    override fun init() {
        repository.getLatestBlogResult()
            .compose(singleIoMainThread())
            .filter { it.keyword.isNotBlank() && it.blogs.isNotEmpty() }
            .subscribe({
                keyword.value = it.keyword
                _data.value = it.blogs
            }, { e ->
                val message = e.message ?: return@subscribe
                errorMsg.value = message
            })
            .addTo(compositeDisposable)
    }

    override fun search(keyword: String) {
        repository.getBlog(
            keyword = keyword
        )
            .compose(singleIoMainThread())
            .subscribe({ blogRepo ->
                _data.value = blogRepo.blogs
            }, { e ->
                val message = e.message ?: return@subscribe
                errorMsg.value = message
            })
            .addTo(compositeDisposable)
    }
}