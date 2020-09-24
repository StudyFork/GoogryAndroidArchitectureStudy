package com.camai.archtecherstudy.ui.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.camai.archtecherstudy.R
import com.camai.archtecherstudy.data.model.Items
import com.camai.archtecherstudy.data.repository.MovieRepositoryImpl
import com.camai.archtecherstudy.databinding.ActivityMainBinding
import com.camai.archtecherstudy.extension.visibleProgress
import com.camai.archtecherstudy.ui.rencentdialog.RecentMovieDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private val TAG = "MovieSearch"

    var progressStatus: Boolean = false

    private val mainPresenter: MainContract.Presenter by lazy {
        MainPresenter(this, MovieRepositoryImpl)
    }

    private val movieSearchAdapter: MovieSearchAdapter by lazy {
        MovieSearchAdapter(mainPresenter)
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //  Recycler View And Adapter Init
        setAdapterAndRecyclerViewInit()

        //  Search Button Click Event
        binding.btnSearch.setOnClickListener(View.OnClickListener {
            hideKeyboard()
            val moviename: String = binding.editName.text.toString()
            mainPresenter.setSearchKeywordCheck(moviename)

        })

        //  Recent Search Movie Name list Dialog Show Click Event
        binding.btnRecent.setOnClickListener(View.OnClickListener {
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
        binding.recyclerView.run {
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

    override fun progressViewStatus(status: Boolean) {
        progressStatus = status
        visibleProgress(binding.progressbar, status)
    }


    override fun textClear() {
        binding.editName.text.clear()
    }

    override fun showEmptyFieldText() {
        Toast.makeText(applicationContext, "검색어를 입력해주세요.", Toast.LENGTH_LONG).show()
    }

    override fun showNotFoundMessage(keyword: String) {
        Toast.makeText(applicationContext, keyword + " 를 찾을 수 없습니다.", Toast.LENGTH_LONG).show()
    }

    override fun openWebView(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun setRecyclerViewScollorPositionInit(keyword: String) {
        binding.recyclerView.layoutManager?.scrollToPosition(0)
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