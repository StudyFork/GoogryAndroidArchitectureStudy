package wooooooak.com.studyapp.ui.movie

import androidx.recyclerview.widget.RecyclerView
import wooooooak.com.studyapp.R
import wooooooak.com.studyapp.ui.base.BaseSearchFragment

class MovieFragment : BaseSearchFragment(R.layout.fragment_movie) {

    override fun setListViewAdapter(listView: RecyclerView) {
        listView.adapter = MovieListAdapter(requireActivity())
    }
}
