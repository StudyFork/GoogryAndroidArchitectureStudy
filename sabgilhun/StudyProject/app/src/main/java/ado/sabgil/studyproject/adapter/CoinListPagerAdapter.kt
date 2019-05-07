package ado.sabgil.studyproject.adapter

import ado.sabgil.studyproject.view.coinlist.CoinListFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class CoinListPagerAdapter(
    fragmentManager: FragmentManager,
    views: Map<String, CoinListFragment>
) : FragmentPagerAdapter(fragmentManager) {

    private val pages = mutableListOf<CoinListFragment>()

    private val title = mutableListOf<String>()

    init {
        this.pages.apply {
            clear()
            addAll(views.values.toList())
            notifyDataSetChanged()
        }
        this.title.addAll(views.keys.toList())

    }

    override fun getItem(position: Int) = pages[position]

    override fun getCount() = pages.size

    override fun getPageTitle(position: Int) = title[position]
}