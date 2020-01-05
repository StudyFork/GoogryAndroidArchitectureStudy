package wooooooak.com.studyapp.common.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("listAdapter")
fun RecyclerView.setListAdapter(listAdapter: ListAdapter<*, *>?) {
    adapter ?: kotlin.run {
        listAdapter?.let {
            this.adapter = it
        }
    }
}