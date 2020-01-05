package wooooooak.com.studyapp.ui.blog

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import wooooooak.com.studyapp.R
import wooooooak.com.studyapp.common.ext.startWebView
import wooooooak.com.studyapp.data.model.database.AppDataBase
import wooooooak.com.studyapp.data.model.datasource.local.NaverLocalDataSourceImpl
import wooooooak.com.studyapp.data.model.repository.NaverApiRepositoryImpl
import wooooooak.com.studyapp.data.model.response.blog.Blog
import wooooooak.com.studyapp.data.model.sharedpreference.SharedPreferenceManager
import wooooooak.com.studyapp.databinding.FragmentBlogBinding
import wooooooak.com.studyapp.ui.base.BaseSearchListAdapter
import wooooooak.com.studyapp.ui.base.ItemSearchFragment

class BlogFragment : ItemSearchFragment<Blog, FragmentBlogBinding>(R.layout.fragment_blog) {

    override val adapter = BlogSearchListAdapter(object : BaseSearchListAdapter.ItemListener<Blog> {
        override fun loadMoreItems(list: List<Blog>, index: Int) {
            lifecycleScope.launch {
                presenter.fetchMoreItems(list, index)
            }
        }

        override fun renderWebView(url: String) {
            requireContext().startWebView(url)
        }
    })

    private val presenter by lazy {
        BlogPresenter(
            this, NaverApiRepositoryImpl(
                NaverLocalDataSourceImpl(
                    SharedPreferenceManager(requireContext()),
                    AppDataBase.getDatabase(requireContext())
                )
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            listAdapter = adapter
            title = userInputTitle
            fragment = this@BlogFragment
            executePendingBindings()
        }
    }

    override fun renderListEmptyView(shouldRender: Boolean) {
        binding.isListEmpty = shouldRender
        binding.executePendingBindings()
    }

    override fun setTitle(title: String) {
        binding.title = title
        binding.executePendingBindings()
    }

    override fun initItemsByTitle(title: String, cached: Boolean) {
        lifecycleScope.launch {
            presenter.fetchItemsWithNewTitle(title, cached)
        }
    }

}
