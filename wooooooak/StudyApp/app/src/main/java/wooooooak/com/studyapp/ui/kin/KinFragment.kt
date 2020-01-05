package wooooooak.com.studyapp.ui.kin


import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import wooooooak.com.studyapp.R
import wooooooak.com.studyapp.common.ext.startWebView
import wooooooak.com.studyapp.data.model.database.AppDataBase
import wooooooak.com.studyapp.data.model.datasource.local.NaverLocalDataSourceImpl
import wooooooak.com.studyapp.data.model.repository.NaverApiRepositoryImpl
import wooooooak.com.studyapp.data.model.response.kin.Kin
import wooooooak.com.studyapp.data.model.sharedpreference.SharedPreferenceManager
import wooooooak.com.studyapp.databinding.FragmentKinBinding
import wooooooak.com.studyapp.ui.base.BaseSearchListAdapter
import wooooooak.com.studyapp.ui.base.ItemSearchFragment

class KinFragment : ItemSearchFragment<Kin, FragmentKinBinding>(R.layout.fragment_kin) {

    override val adapter = KinListAdapter(object : BaseSearchListAdapter.ItemListener<Kin> {
        override fun loadMoreItems(list: List<Kin>, index: Int) {
            lifecycleScope.launch {
                presenter.fetchMoreItems(list, index)
            }
        }

        override fun renderWebView(url: String) {
            requireContext().startWebView(url)
        }
    })

    private val presenter by lazy {
        KinPresenter(
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
            fragment = this@KinFragment
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
