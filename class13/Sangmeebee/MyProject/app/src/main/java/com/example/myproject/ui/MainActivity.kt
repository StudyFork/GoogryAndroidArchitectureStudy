package com.example.myproject.ui

import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.R
import com.example.myproject.data.model.Items
import com.example.myproject.data.repository.NaverRepository
import com.example.myproject.data.repository.NaverRepositoryImpl
import com.example.myproject.data.source.local.NaverLocalDataSourceImpl
import com.example.myproject.data.source.remote.NaverRemoteDataSourceImpl
import com.example.myproject.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<MainContract.Presenter>(R.layout.activity_main),
    MainContract.View, OnListItemSelectedInterface {

    private var movies: ArrayList<Items> = ArrayList()
    private val movieAdapter = MovieAdapter(this, movies)

    private val repositoryDataSourceImpl: NaverRepository by lazy {
        val naverRemoteDataSourceImpl = NaverRemoteDataSourceImpl()
        val naverLocalDataSourceImpl = NaverLocalDataSourceImpl()
        NaverRepositoryImpl(naverLocalDataSourceImpl, naverRemoteDataSourceImpl)
    }

    override val presenter by lazy {
        MainPresenter(this, repositoryDataSourceImpl)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerView()

        btn_search.setOnClickListener {

            val title = edit_title.text.toString()
            presenter.searchMovieList(title)

        }

        btn_history.setOnClickListener {
            val dialog =
                TitleFragmentDialog.newInstance(repositoryDataSourceImpl.readRecentSearchTitle())
            dialog.show(supportFragmentManager, "title_history_dialog")
        }
    }

    private fun setRecyclerView() {
        recyclerview.apply {
            setHasFixedSize(true)
            adapter = movieAdapter
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }
    }

    override fun onItemSelected(title: String) {
        edit_title.text = Editable.Factory.getInstance().newEditable(title)
    }

    override fun showQueryEmpty() {
        Toast.makeText(this, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
    }

    override fun initScroll() {
        recyclerview.layoutManager?.scrollToPosition(0)
    }

    override fun showResultEmpty() {
        movieAdapter.clearItems()
        Toast.makeText(
            applicationContext,
            "$title 를 찾을 수 없습니다",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun showResult(items: ArrayList<Items>) {
        movieAdapter.clearAndAddItems(items)
    }
}
