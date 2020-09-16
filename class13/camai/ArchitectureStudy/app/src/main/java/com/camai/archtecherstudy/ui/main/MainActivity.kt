package com.camai.archtecherstudy.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.camai.archtecherstudy.R
import com.camai.archtecherstudy.data.model.Items
import com.camai.archtecherstudy.data.repository.MovieRepositoryImpl
import com.camai.archtecherstudy.ui.rencentdialog.RecentMovieDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private val TAG = "MovieSearch"

    private val movieSearchAdapter: MovieSearchAdapter by lazy {
        MovieSearchAdapter()
    }

    private val mainPresenter: MainContract.Presenter by lazy {
        MainPresenter(this, MovieRepositoryImpl)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //  Recycler View And Adapter Init
        setAdapterAndRecyclerViewInit()

        //  Search Button Click Event
        btn_search.setOnClickListener(View.OnClickListener {
            hideKeyboard()
            val moviename: String = edit_name.text.toString()
            mainPresenter.setSearchKeywordCheck(moviename)

        })

        //  Recent Search Movie Name list Dialog Show Click Event
        btn_recent.setOnClickListener(View.OnClickListener {
            RecentMovieDialog(keywork = {
                //  Click movie name
                mainPresenter.setSearchKeywordCheck(it)
            })
                .show(supportFragmentManager, RecentMovieDialog.TAG)
        })

    }

    //  RecyclerView Adapter Set
    private fun setAdapterAndRecyclerViewInit() {

        //  recyclerView init
        recycler_view.run {
            adapter = movieSearchAdapter
            setHasFixedSize(false)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }

    }

    //  Hardware Keyboard hide
    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(edit_name.windowToken, 0)
    }

    override fun progressView() {
        progressbar.isVisible = true
    }

    override fun progressGone() {
        progressbar.isVisible = false
    }

    override fun textClear() {
        edit_name.text.clear()
    }

    override fun showEmptyFieldText() {
        Toast.makeText(applicationContext, "검색어를 입력해주세요.", Toast.LENGTH_LONG).show()
    }

    override fun showNotFoundMessage(keyword: String) {
        Toast.makeText(applicationContext, keyword + " 를 찾을 수 없습니다.", Toast.LENGTH_LONG).show()
    }

    override fun setRecyclerViewScollorPositionInit(keyword: String) {
        recycler_view.layoutManager?.scrollToPosition(0)
        //  Movie Name Search
        mainPresenter.setSearchMovie(keyword)
    }

    //  Data input to Adapter
    override fun setDataInsertToAdapter(data: ArrayList<Items>) {
        setListData(data)
    }

    //  Update Movie Search Result Data List
    private fun setListData(infoList: ArrayList<Items>) {
        movieSearchAdapter.setClearAndAddList(infoList)
    }
}