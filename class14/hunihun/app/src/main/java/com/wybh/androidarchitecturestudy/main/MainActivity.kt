package com.wybh.androidarchitecturestudy.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.wybh.androidarchitecturestudy.CinemaAdapter
import com.wybh.androidarchitecturestudy.CinemaItem
import com.wybh.androidarchitecturestudy.R
import com.wybh.androidarchitecturestudy.databinding.ActivityMainBinding
import com.wybh.androidarchitecturestudy.history.RecentSearchWord

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var imm: InputMethodManager
    private lateinit var binding: ActivityMainBinding

    private val presenter = MainPresenter(this)
    private val cinemaAdapter: CinemaAdapter =
        CinemaAdapter {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
        }

    override fun showToastFailMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showCinemaList(dataList: List<CinemaItem>) {
        // adapter list clear
        cinemaAdapter.dataClear()
        cinemaAdapter.addList(dataList)
        // recyclerview 갱신
        cinemaAdapter.notifyDataSetChanged()

        // 키보드 내리기
        imm.hideSoftInputFromWindow(binding.etSearchWord.windowToken, 0)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.view = this

        initView()
        initAdapter()
    }

    override fun onDestroy() {
        presenter.removeCompositeDisposable()
        super.onDestroy()
    }

    private fun initView() {
        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    private fun initAdapter() {
        binding.rvCinema.adapter = cinemaAdapter
    }

    fun search() {
        presenter.searchCinema(binding.etSearchWord.text.toString())
        presenter.saveSearchWord(binding.etSearchWord.text.toString())
    }

    fun recentSearch() {
        startActivityForResult(
            Intent(this, RecentSearchWord::class.java),
            REQUEST_HISTORY_WORD
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        when (requestCode) {
            REQUEST_HISTORY_WORD -> {
                presenter.searchCinema(data?.getStringExtra("SEARCH_WORD")!!)
                presenter.saveSearchWord(data.getStringExtra("SEARCH_WORD")!!)
            }
        }

    }

    companion object {
        const val REQUEST_HISTORY_WORD = 200
    }
}
