package com.hansung.firstproject.ui.main

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hansung.firstproject.R
import com.hansung.firstproject.adapter.MovieItemAdapter
import com.hansung.firstproject.data.ErrorStringResource
import com.hansung.firstproject.data.MovieResponseModel
import com.hansung.firstproject.data.repository.NaverRepository
import com.hansung.firstproject.data.source.remote.NaverRemoteDataSourceImpl
import com.hansung.firstproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainContract.View {

    private val presenter by lazy {
        MainPresenter(
            this,
            NaverRepository.getInstance(
                NaverRemoteDataSourceImpl.getInstance(
                    Pair<String, String>(
                        getString(R.string.client_id),
                        getString(R.string.client_secret)
                    )
                )
            )
        )
    }

    private val movieItemAdapter: MovieItemAdapter = MovieItemAdapter()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // recyclerView initialize
        initRecyclerView()
        // keyboard function
        setKeyboardSearchFunc()

        with(binding) {
            btnSearch.setOnClickListener {
                presenter.doSearch(etSearch.text.toString())
            }
        }
    }

    // recyclerView 초기화 메소드
    private fun initRecyclerView() {
        with(binding.recyclerViewMovies) {
            adapter = movieItemAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL
                )
            )
        }
    }

    //키보드 제거 메소드
    override fun removeKeyboard() =
        (this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            binding.etSearch.windowToken,
            0
        )

    override fun showErrorKeywordEmpty() {
        Toast.makeText(this, getString(R.string.empty_keyword_message), Toast.LENGTH_SHORT).show()
    }

    override fun showErrorByErrorMessage(errorMessage: String) {
        Toast.makeText(this, ErrorStringResource.valueOf(errorMessage).resId, Toast.LENGTH_SHORT)
            .show()
    }

    override fun showErrorEmptyList() {
        Toast.makeText(this, getString(R.string.empty_list_message), Toast.LENGTH_SHORT).show()
    }

    override fun addItemToAdapter(response: MovieResponseModel) {
        movieItemAdapter.run {
            addItems(response.items)
            notifyDataSetChanged()
        }
    }

    private fun setKeyboardSearchFunc() {
        binding.etSearch.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        presenter.doSearch(binding.etSearch.text.toString())
                    }
                    else ->
                        return false
                }
                return true
            }
        })
    }
}