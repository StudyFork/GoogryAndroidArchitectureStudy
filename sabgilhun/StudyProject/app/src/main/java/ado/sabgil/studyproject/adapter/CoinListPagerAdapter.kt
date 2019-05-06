package ado.sabgil.studyproject.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class CoinListPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val pages: MutableList<Fragment> by lazy { mutableListOf<Fragment>() }

    private val title: MutableList<String> by lazy { mutableListOf<String>() }

    override fun getItem(position: Int) = pages[position]

    override fun getCount() = pages.size

    fun setPages(pages: Map<String, Fragment>) {
        this.pages.apply {
            clear()
            addAll(pages.values.toList())
            notifyDataSetChanged()
        }
        this.title.addAll(pages.keys.toList())
    }

    override fun getPageTitle(position: Int) = title[position]
}