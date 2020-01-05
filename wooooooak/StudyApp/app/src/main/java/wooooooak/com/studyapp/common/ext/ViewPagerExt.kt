package wooooooak.com.studyapp.common.ext

import androidx.databinding.BindingAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

@BindingAdapter("pageAdapter")
fun ViewPager2.setPageAdapter(fragmentStateAdapter: FragmentStateAdapter) {
    adapter = fragmentStateAdapter
    isUserInputEnabled = false
}