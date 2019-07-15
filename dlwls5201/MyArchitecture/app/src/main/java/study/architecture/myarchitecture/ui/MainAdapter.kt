package study.architecture.myarchitecture.ui

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var titles: Array<String>? = null

    private var markets: Array<String>? = null

    override fun getItem(position: Int): Fragment {

        val item = markets?.get(position) ?: ""

        return TickerListFragment.newInstance(item)

    }

    override fun getCount() = markets?.size ?: 0

    //생성된 fragment를 파괴하지 않는다.
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        //super.destroyItem(container, position, `object`)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles?.get(position)
    }

    fun setItems(items: Array<String>) {
        this.markets = items
        notifyDataSetChanged()
    }

    fun setTitles(items: Array<String>) {
        this.titles = items
    }
}