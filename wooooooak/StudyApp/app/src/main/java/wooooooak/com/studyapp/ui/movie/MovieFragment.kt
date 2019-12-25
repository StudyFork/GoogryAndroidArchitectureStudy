package wooooooak.com.studyapp.ui.movie

import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import wooooooak.com.studyapp.R
import wooooooak.com.studyapp.common.ext.startWebView
import wooooooak.com.studyapp.data.model.repository.NaverApiRepositoryImpl
import wooooooak.com.studyapp.data.model.response.movie.Movie
import wooooooak.com.studyapp.ui.base.BaseSearchListAdapter
import wooooooak.com.studyapp.ui.base.ItemSearchFragment

class MovieFragment : ItemSearchFragment<Movie>(R.layout.fragment_movie) {

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

    private val presenter by lazy { MoviePresenter(this, NaverApiRepositoryImpl) }

    override fun initItemsByTitle(title: String) {
        lifecycleScope.launch {
            presenter.fetchItemsWithNewTitle(title)
        }
    }
}
