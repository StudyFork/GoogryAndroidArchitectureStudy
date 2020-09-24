package com.hjhan.hyejeong.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.hjhan.hyejeong.R
import com.hjhan.hyejeong.data.model.Item
import com.hjhan.hyejeong.data.repository.NaverRepositoryImpl
import com.hjhan.hyejeong.data.source.local.NaverLocalDataSourceImpl
import com.hjhan.hyejeong.data.source.remote.NaverRemoteDataSourceImpl
import com.hjhan.hyejeong.databinding.ActivityMainBinding
import com.hjhan.hyejeong.ui.QueryHistoryDialog.Companion.HISTORY_DIALOG_TAG
import com.hjhan.hyejeong.ui.base.BaseActivity

class MainActivity : BaseActivity<MainContract.Presenter>(), MainContract.View {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter

    override val presenter: MainContract.Presenter by lazy {
        val remoteDataSourceImpl = NaverRemoteDataSourceImpl()
        val localDataSourceImpl = NaverLocalDataSourceImpl()
        MainPresenter(
            this@MainActivity,
            NaverRepositoryImpl(remoteDataSourceImpl, localDataSourceImpl)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this

        movieAdapter = MovieAdapter()
        binding.movieRecyclerView.adapter = movieAdapter

    }

    override fun emptyQuery() {
        Toast.makeText(this, "제목을 입력해주세요.", Toast.LENGTH_SHORT).show()
    }

    override fun emptyMovieList() {
        binding.emptyListText.visibility = View.VISIBLE
        binding.movieRecyclerView.visibility = View.GONE
    }

    override fun setMovieList(list: List<Item>) {
        binding.emptyListText.visibility = View.GONE
        binding.movieRecyclerView.visibility = View.VISIBLE
        movieAdapter.setMovieList(list)
    }

    fun onSearchButtonClicked() {
        presenter.getMovieList(binding.searchEditText.text.toString())
    }

    fun onHistoryButtonClicked() {
        //최근 검색 목록
        QueryHistoryDialog().show(supportFragmentManager, HISTORY_DIALOG_TAG)
    }

}
