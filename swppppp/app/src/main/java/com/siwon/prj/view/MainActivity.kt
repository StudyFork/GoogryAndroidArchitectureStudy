package com.siwon.prj.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.siwon.prj.view.presenter.MainContract
import com.siwon.prj.view.presenter.MainPresenter
import com.siwon.prj.movie_adapter.MovieAdapter
import com.siwon.prj.R
import com.siwon.prj.model.Movie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private val present: MainContract.Presenter = MainPresenter(this)
//    private val present: MainContract.Presenter by inject{ parametersOf(this)}

    lateinit var adapter: MovieAdapter
    var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT)
        adapter = MovieAdapter { link: String ->
            itemClick(link)
        }
        movieListRv.adapter = adapter

        // 검색버튼
        search_btn.setOnClickListener {
            hideKeyboard()
            getMovies(edit_text_input.text.toString())
        }
    }

    override fun showResult(result: ArrayList<Movie>) {
        movieListRv.visibility = View.VISIBLE
        emptyResultImg.visibility = View.GONE
        adapter.clearItemList()
        adapter.setItem(result)
    }

    override fun emptyInput() {
        adapter.clearItemList()
        toastMsg("입력값이 없습니다.")
    }

    override fun emptyResult() {
        movieListRv.visibility = View.GONE
        emptyResultImg.visibility = View.VISIBLE
        toastMsg("검색 결과가 없습니다.")
    }

    override fun serviceErr(erMsg: String) = toastMsg("${erMsg}\n\n 서비스 에러입니다. 네트워크를 확인해 주세요.")

    override fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if(currentFocus!=null) imm.hideSoftInputFromWindow(currentFocus.windowToken, 0)
    }

    override fun toastMsg(msg: String) {
        toast?.let {
            it.setText(msg)
            it.duration = Toast.LENGTH_SHORT
            it.show()
        }
    }

    fun getMovies(input: String) = present.getSearchResult(input)

    fun itemClick(link: String) {
        val detailWebview = Intent(this@MainActivity, DetailWebview::class.java)
        detailWebview.putExtra("link", link)
        startActivity(detailWebview)
    }
}
