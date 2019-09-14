package study.architecture.myarchitecture.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import study.architecture.myarchitecture.ui.main.MainAdapter

@BindingAdapter("android:adapter")
fun ViewPager.setAdapter(mainAdapter: MainAdapter) {
    adapter = mainAdapter
}


@BindingAdapter(value = ["android:category", "android:adapter"], requireAll = true)
fun ImageView.setCategory(field: Filter.SelectArrow?, mainAdapter: MainAdapter?) {

    if (field == null || mainAdapter == null) return

    val order: Int = if (isSelected) {
        Filter.DESC
    } else {
        Filter.ASC
    }

    isSelected = !isSelected

    for (i: Int in 0 until (mainAdapter.count)) {
        mainAdapter.getFragment(i)?.run {
            this.get()?.showTickerListOrderByField(field, order)
        }
    }
}