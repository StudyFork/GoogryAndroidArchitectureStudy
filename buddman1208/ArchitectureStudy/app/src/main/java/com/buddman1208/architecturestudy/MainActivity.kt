package com.buddman1208.architecturestudy

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.recyclical.datasource.DataSource
import com.afollestad.recyclical.datasource.dataSourceTypedOf
import com.afollestad.recyclical.setup
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val datas: DataSource<Any> = dataSourceTypedOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()
        initBottomNavigation()
        initRecyclerView()
    }

    private fun initToolbar() {
        toolbar.apply {
            setSupportActionBar(this)
            setTitleTextColor(ContextCompat.getColor(applicationContext, android.R.color.white))
            setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
            contentInsetStartWithNavigation = 0
        }
        updateToolbarTitle(bottomTabView.selectedItemId)
    }

    private fun initBottomNavigation() {
        bottomTabView.setOnNavigationItemSelectedListener {
            updateToolbarTitle(it.itemId)
            true
        }
    }

    private fun initRecyclerView() {
        rvMain.setup {
            withDataSource(datas)
            withLayoutManager(LinearLayoutManager(this@MainActivity))
        }
    }

    private fun updateToolbarTitle(@IdRes itemId: Int) {
        supportActionBar?.title = when (itemId) {
            R.id.menuBlog -> "블로그"
            R.id.menuBook -> "도서"
            R.id.menuMovie -> "영화"
            R.id.menuNews -> "뉴스"
            else -> ""
        }
    }

}

class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val ivThumbnail: ImageView = view.findViewById(R.id.ivThumbnail)
    val title: TextView = view.findViewById(R.id.tvTitle)
    val description: TextView = view.findViewById(R.id.tvDescription)
}