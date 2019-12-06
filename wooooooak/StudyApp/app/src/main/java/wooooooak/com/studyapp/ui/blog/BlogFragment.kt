package wooooooak.com.studyapp.ui.blog

import androidx.recyclerview.widget.RecyclerView
import wooooooak.com.studyapp.R
import wooooooak.com.studyapp.ui.base.BaseSearchFragment

class BlogFragment : BaseSearchFragment(R.layout.fragment_blog) {

    override fun setListViewAdapter(listView: RecyclerView) {
        listView.adapter = BlogSearchListAdapter(requireActivity())
    }

}
