package study.architecture.myarchitecture.util

import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import study.architecture.myarchitecture.ui.main.MainAdapter

@BindingAdapter("android:adapter")
fun ViewPager.setAdapter(mainAdapter: MainAdapter) {
    adapter = mainAdapter
}