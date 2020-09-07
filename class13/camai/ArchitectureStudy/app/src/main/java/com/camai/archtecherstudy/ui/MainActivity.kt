package com.camai.archtecherstudy.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.camai.archtecherstudy.ui.adapter.MovieSearchAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MovieSearch"
    private lateinit var movieSearchAdapter: MovieSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //  Recycler View And Adapter Init
        setAdapterAndRecyclerViewInit()


        //  Search Button Click Event
        btn_search.setOnClickListener(View.OnClickListener {
            hideKeyboard(this)
            progressbar.isVisible = true
            searchStart()
        })

        //  Recent Search Movie Name list Dialog Show Click Event
        btn_recent.setOnClickListener(View.OnClickListener {
            RecentMovieListDialog(keywork = {
                //  Click movie name
                getMoiveSearchCall(it)
            }).show(supportFragmentManager, RecentMovieListDialog.TAG)
        })

    }


    //  RecyclerView Adapter Set
    private fun setAdapterAndRecyclerViewInit() {
        movieSearchAdapter =
            MovieSearchAdapter()

        //  recycler view init and adapter connect
        recycler_view.run {
            adapter = movieSearchAdapter
            setHasFixedSize(false)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        }
    }

    //  Hardware Keyboard hide
    private fun hideKeyboard(context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(edit_name.windowToken, 0)
    }

    //  Keyword Empty Toast
    private fun showEmptyFieldText() {
        Toast.makeText(applicationContext, "검색어를 입력해주세요.", Toast.LENGTH_LONG).show()
    }

    //  Keyword Error Toast
    private fun showNotFoundMessage(keyword: String) {
        Toast.makeText(applicationContext, keyword + " 를 찾을 수 없습니다.", Toast.LENGTH_LONG).show()
    }

    //  Movie Name Search
    private fun searchStart() {
        val moviename: String = edit_name.text.toString()

        //  Movie Name Value Check
        if (moviename.isNullOrBlank()) {
            showEmptyFieldText()
        } else {
            recycler_view.layoutManager?.scrollToPosition(0)
            getMoiveSearchCall(moviename)
        }
    }

    //  Naver Moive Search Api Call
    private fun getMoiveSearchCall(movietitle: String) {
        MovieRepositoryImpl.getMovieNameSearch(movietitle, 100, 1,
            success = {
                //  movie list data to recycler View
                setListData(it)
            },
            failed = {
                showNotFoundMessage(movietitle)
                Log.e(TAG, it)
            })
    }


    //  Update Movie Search Result Data List
    private fun setListData(infoList: ArrayList<Items>) {
        movieSearchAdapter.setClearAndAddList(infoList)

        progressbar.isVisible = false
        edit_name.text.clear()
    }

    override fun onDestroy() {
        super.onDestroy()

        MovieRepositoryImpl.dbclose()
    }
}