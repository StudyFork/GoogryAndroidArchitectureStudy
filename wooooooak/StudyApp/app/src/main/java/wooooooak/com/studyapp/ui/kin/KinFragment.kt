package wooooooak.com.studyapp.ui.kin


import androidx.recyclerview.widget.RecyclerView
import wooooooak.com.studyapp.R
import wooooooak.com.studyapp.ui.base.BaseSearchFragment

class KinFragment(override val layoutId: Int = R.layout.fragment_kin) : BaseSearchFragment() {

    override fun setListViewAdapter(listView: RecyclerView) {
        listView.adapter = KinListAdapter(requireActivity())
    }
}
