package wooooooak.com.studyapp.ui.movie

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import wooooooak.com.studyapp.R
import wooooooak.com.studyapp.common.ext.startWebView
import wooooooak.com.studyapp.data.model.database.AppDataBase
import wooooooak.com.studyapp.data.model.datasource.local.NaverLocalDataSourceImpl
import wooooooak.com.studyapp.data.model.repository.NaverApiRepositoryImpl
import wooooooak.com.studyapp.data.model.response.movie.Movie
import wooooooak.com.studyapp.data.model.sharedpreference.SharedPreferenceManager
import wooooooak.com.studyapp.databinding.FragmentMovieBinding
import wooooooak.com.studyapp.ui.base.BaseSearchListAdapter
import wooooooak.com.studyapp.ui.base.ItemSearchFragment

class MovieFragment : ItemSearchFragment<Movie, FragmentMovieBinding>(R.layout.fragment_movie) {

    override val adapter = MovieListAdapter(object : BaseSearchListAdapter.ItemListener<Movie> {
        override fun loadMoreItems(list: List<Movie>, index: Int) {
            lifecycleScope.launch {
                presenter.fetchMoreItems(list, index)
            }
        }

        override fun renderWebView(url: String) {
            requireContext().startWebView(url)
        }
    })

    private val presenter by lazy {
        MoviePresenter(
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
            fragment = this@MovieFragment
            executePendingBindings()
        }
    }

    override fun initItemsByTitle(title: String, cached: Boolean) {
        lifecycleScope.launch {
            presenter.fetchItemsWithNewTitle(title, cached)
        }
    }

    override fun setTitle(title: String) {
        binding.title = title
        binding.executePendingBindings()
    }

    override fun renderListEmptyView(shouldRender: Boolean) {
        binding.isListEmpty = shouldRender
        binding.executePendingBindings()
    }
}
