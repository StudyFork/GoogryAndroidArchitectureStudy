package wooooooak.com.studyapp.ui.image

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import wooooooak.com.studyapp.R
import wooooooak.com.studyapp.common.ext.startWebView
import wooooooak.com.studyapp.data.model.database.AppDataBase
import wooooooak.com.studyapp.data.model.datasource.local.NaverLocalDataSourceImpl
import wooooooak.com.studyapp.data.model.repository.NaverApiRepositoryImpl
import wooooooak.com.studyapp.data.model.response.image.Image
import wooooooak.com.studyapp.data.model.sharedpreference.SharedPreferenceManager
import wooooooak.com.studyapp.databinding.FragmentImageBinding
import wooooooak.com.studyapp.ui.base.BaseSearchListAdapter
import wooooooak.com.studyapp.ui.base.ItemSearchFragment

class ImageFragment : ItemSearchFragment<Image, FragmentImageBinding>(R.layout.fragment_image) {

    override val adapter = ImageListAdapter(object : BaseSearchListAdapter.ItemListener<Image> {
        override fun loadMoreItems(list: List<Image>, index: Int) {
            lifecycleScope.launch {
                presenter.fetchMoreItems(list, index)
            }
        }

        override fun renderWebView(url: String) {
            requireContext().startWebView(url)
        }
    })

    private val presenter by lazy {
        ImagePresenter(
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
            fragment = this@ImageFragment
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