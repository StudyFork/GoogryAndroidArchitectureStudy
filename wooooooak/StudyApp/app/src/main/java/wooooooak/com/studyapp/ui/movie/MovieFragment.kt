package wooooooak.com.studyapp.ui.movie

import androidx.recyclerview.widget.RecyclerView
import wooooooak.com.studyapp.R
import wooooooak.com.studyapp.ui.base.BaseSearchFragment

class MovieFragment(override val layoutId: Int = R.layout.fragment_movie) : BaseSearchFragment() {

    override fun setListViewAdapter(listView: RecyclerView) {
        listView.adapter = MovieListAdapter(requireActivity())
    }
}
