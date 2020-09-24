package com.hjhan.hyejeong.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hjhan.hyejeong.R
import com.hjhan.hyejeong.data.model.Item
import com.hjhan.hyejeong.data.repository.NaverRepositoryImpl
import com.hjhan.hyejeong.data.source.local.NaverLocalDataSourceImpl
import com.hjhan.hyejeong.data.source.remote.NaverRemoteDataSourceImpl
import com.hjhan.hyejeong.ui.QueryHistoryDialog.Companion.HISTORY_DIALOG_TAG
import com.hjhan.hyejeong.ui.base.BaseActivity

class MainActivity : BaseActivity<MainContract.Presenter>(R.layout.activity_main),
    MainContract.View {

    private lateinit var editText: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchButton: Button
    private lateinit var historyButton: Button
    private lateinit var emptyListText: TextView

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
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.search_edit_text)
        searchButton = findViewById(R.id.search_button)
        historyButton = findViewById(R.id.history_button)
        recyclerView = findViewById(R.id.movie_recycler_view)
        emptyListText = findViewById(R.id.empty_list_text)

        movieAdapter = MovieAdapter()
        recyclerView.adapter = movieAdapter

        searchButton.setOnClickListener {
            presenter.getMovieList(editText.text.toString())
        }

        historyButton.setOnClickListener {
            //최근 검색 목록
            QueryHistoryDialog().show(supportFragmentManager, HISTORY_DIALOG_TAG)
        }


    }

    override fun emptyQuery() {
        Toast.makeText(this, "제목을 입력해주세요.", Toast.LENGTH_SHORT).show()
    }

    override fun emptyMovieList() {
        emptyListText.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    override fun setMovieList(list: List<Item>) {
        emptyListText.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        movieAdapter.setMovieList(list)
    }

}
