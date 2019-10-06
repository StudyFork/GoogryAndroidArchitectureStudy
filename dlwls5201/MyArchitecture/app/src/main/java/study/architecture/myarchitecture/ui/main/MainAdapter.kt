package study.architecture.myarchitecture.ui.main

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import study.architecture.myarchitecture.ui.tickerlist.TickerListFragment
import java.lang.ref.WeakReference
import java.util.*

class MainAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var titles: Array<String>? = null

    private var markets: Array<String>? = null

    private var fragmentReferences: Hashtable<Int, WeakReference<TickerListFragment>> = Hashtable()

    // Do NOT try to save references to the Fragments in getItem(),
    // because getItem() is not always called. If the Fragment
    // was already created then it will be retrieved from the FragmentManger
    // and not here (i.e. getItem() won't be called again).
    override fun getItem(position: Int): Fragment {
        val item = markets?.get(position) ?: ""
        return TickerListFragment.newInstance(item)

    }

    // Here we can finally safely save a reference to the created
    // Fragment, no matter where it came from (either getItem() or
    // FragmentManger). Simply save the returned Fragment from
    // super.instantiateItem() into an appropriate reference depending
    // on the ViewPager position.
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val createdFragment = super.instantiateItem(container, position) as Fragment
        fragmentReferences[position] = WeakReference(createdFragment as TickerListFragment)
        return createdFragment
    }

    override fun getCount() = markets?.size ?: 0

    override fun getPageTitle(position: Int): CharSequence? {
        return titles?.get(position)
    }

    fun getFragment(position: Int) = fragmentReferences.get(position)

    fun setItems(items: Array<String>) {
        this.markets = items.copyOf()
    }

    fun setTitles(items: Array<String>) {
        this.titles = items.copyOf()
    }
}