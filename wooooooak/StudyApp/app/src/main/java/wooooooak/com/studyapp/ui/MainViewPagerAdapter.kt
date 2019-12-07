package wooooooak.com.studyapp.ui

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import wooooooak.com.studyapp.ui.blog.BlogFragment
import wooooooak.com.studyapp.ui.image.ImageFragment
import wooooooak.com.studyapp.ui.kin.KinFragment
import wooooooak.com.studyapp.ui.movie.MovieFragment

class MainViewPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    private val fragmentList = listOf(MovieFragment(), BlogFragment(), ImageFragment(), KinFragment())

    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int) = fragmentList[position]
}