package com.siwon.prj.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.siwon.prj.*
import com.siwon.prj.repository.MovieSearchRepository
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val repository: MovieSearchRepository by inject()
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
//        repository = MovieSearchRepositoryImpl()

        // 검색버튼
        search_btn.setOnClickListener {
            if (edit_text_input.text.isNullOrBlank()) {
                toastMsg("검색어를 입력해 주세요.")
            }else{
                toastMsg("입력한 겁색어: ${edit_text_input.text.toString()}")
                getMovies(edit_text_input.text.toString())
            }
        }
    }

    fun getMovies(input: String) {
        repository.searchMovies(input,
            success = {
                adapter.setItem(it)
//                adapter.setItems(it)
            },
            fail = {
                Log.e("에러", "에러메시지: ${it.message}")
            })
    }

    fun itemClick(link: String){
        val detailWebview = Intent(this@MainActivity, DetailWebview::class.java)
        detailWebview.putExtra("link", link)
        startActivity(detailWebview)
    }

    fun toastMsg(msg: String) {
        toast?.let {
            it.setText(msg)
            it.duration = Toast.LENGTH_SHORT
            it.show()
        }
    }
}
