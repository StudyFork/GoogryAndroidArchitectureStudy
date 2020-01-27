package com.jay.architecturestudy.ui.blog

import androidx.databinding.ObservableField
import com.jay.architecturestudy.data.model.Blog
import com.jay.architecturestudy.data.repository.NaverSearchRepository
import com.jay.architecturestudy.ui.BaseViewModel
import com.jay.architecturestudy.ui.ViewType
import com.jay.architecturestudy.util.addTo
import com.jay.architecturestudy.util.singleIoMainThread

class BlogViewModel(
    override val repository: NaverSearchRepository
): BaseViewModel<Blog>(repository) {
    override val data: ObservableField<List<Blog>> = ObservableField()

    override fun init() {
        repository.getLatestBlogResult()
            .compose(singleIoMainThread())
            .subscribe({
                keyword.set(it.keyword)
                data.set(it.blogs)
            }, { e ->
                val message = e.message ?: return@subscribe
                errorMsg.set(message)
            })
            .addTo(compositeDisposable)
    }

    override fun search(keyword: String) {
        repository.getBlog(
            keyword = keyword
        )
            .compose(singleIoMainThread())
            .subscribe({ blogRepo ->
                viewType.set(
                    if (blogRepo.blogs.isEmpty()) {
                        ViewType.VIEW_SEARCH_NO_RESULT
                    } else {
                        ViewType.VIEW_SEARCH_SUCCESS
                    }
                )
                data.set(blogRepo.blogs)
            }, { e ->
                val message = e.message ?: return@subscribe
                errorMsg.set(message)
            })
            .addTo(compositeDisposable)
    }
}