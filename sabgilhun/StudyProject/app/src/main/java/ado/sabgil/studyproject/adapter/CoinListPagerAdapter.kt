package ado.sabgil.studyproject.adapter

import ado.sabgil.studyproject.view.coinlist.CoinListFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class CoinListPagerAdapter(
    fragmentManager: FragmentManager,
    views: Map<String, CoinListFragment>
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val pages = mutableListOf<CoinListFragment>()

    private val title = mutableListOf<String>()

    init {
        pages.addAll(views.values.toList())
        title.addAll(views.keys.toList())
        notifyDataSetChanged()
    }

    override fun getItem(position: Int) = pages[position]

    override fun getCount() = pages.size

    override fun getPageTitle(position: Int) = title[position]

}