package study.architecture.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import study.architecture.data.entity.ProcessingTicker
import study.architecture.coinjob.CoinDataAdapter

@BindingAdapter("replaceAll")
fun RecyclerView.replaceAll(list: List<ProcessingTicker>?) {
    if (list != null) {
        (this.adapter as? CoinDataAdapter)?.updateLists(list)
    }

}