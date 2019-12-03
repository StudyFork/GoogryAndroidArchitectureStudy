package wooooooak.com.studyapp.ui.image

import androidx.recyclerview.widget.RecyclerView
import wooooooak.com.studyapp.R
import wooooooak.com.studyapp.ui.base.BaseSearchFragment


class ImageFragment(
    layoutId: Int = R.layout.fragment_image
) : BaseSearchFragment(layoutId) {

    override fun setListViewAdapter(listView: RecyclerView) {
        listView.adapter = ImageListAdapter(requireActivity())
    }

}
