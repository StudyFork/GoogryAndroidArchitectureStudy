package com.sangjin.newproject.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.sangjin.newproject.R
import com.sangjin.newproject.activity.adapter.MovieListAdapter
import com.sangjin.newproject.databinding.ActivityMovieListBinding
import kotlinx.android.synthetic.main.activity_movie_list.*
import org.koin.android.viewmodel.ext.android.viewModel


class MovieListActivity : AppCompatActivity() {

    private lateinit var movieListAdapter: MovieListAdapter

    private val viewModel: MovieListViewModel by viewModel()

    private lateinit var binding: ActivityMovieListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_movie_list
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setRecyclerView()
        showKeyPad()

        toastMsgObserver()
        hideKeypadObserver()
    }


    //**리사이클러뷰 셋팅
    private fun setRecyclerView() {

        //각 항목 클릭시 이벤트 처리
        val onItemClickListener: ((Int) -> Unit) = { position ->
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(movieListAdapter.getMovieList().get(position).link)
            )
            startActivity(intent)
        }

        movieListAdapter = MovieListAdapter(onItemClickListener)
        binding.movieListView.adapter = movieListAdapter
    }


    //**키패드 보여주기
    private fun showKeyPad() {
        binding.movieNameET.requestFocus()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }

    //**키패드 숨기기
    private fun hideKeypad(){
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(movieNameET.windowToken, 0)
    }


    //** toastMsgObserver 모음
    private fun toastMsgObserver() {
        viewModel.toastMsg.observe(this, Observer {
            Toast.makeText(this@MovieListActivity, it, Toast.LENGTH_SHORT).show()
        })

    }


    //** 키패드 숨기기 옵저버
    private fun hideKeypadObserver() {
        viewModel.hideKeypad.observe(this, Observer {
            hideKeypad()
        })
    }



    //**키패드 셋팅
    companion object {
        @BindingAdapter("setKeypad")
        @JvmStatic
        fun EditText.setKeypad(action : (() -> Unit) ?= null) {
            this.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    action?.invoke()
                    true
                } else {
                    false
                }
            }
        }
    }
}
