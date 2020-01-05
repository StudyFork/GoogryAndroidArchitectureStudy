package com.siwon.prj.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.siwon.prj.*
import com.siwon.prj.repository.MovieSearchRepository
import com.siwon.prj.repository.MovieSearchRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var repository: MovieSearchRepository
    lateinit var adapter: MovieAdapter
    var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT)
        adapter = MovieAdapter { link: String -> itemClick(link) }
        repository = MovieSearchRepositoryImpl()

        // 검색버튼
        search_btn.setOnClickListener {
            if (edit_text_input.text.isNullOrBlank()) {
                // scope함수중에 null체크해주는거 사용
                // with는 확장함수개념 null 체크안돼
                toast?.let {
                    it.setText("검색어를 입력해주세요.")
                    it.show()
                }
                // 토스트 재활용 가능하도록..!!
                // 토스트 보다는 스낵바 사용
            }else{
                toast?.let {
                    it.setText("입력한 겁색어: ${edit_text_input.text.toString()}")
                    it.show()
                }

                getMovies(edit_text_input.text.toString())
            }
        }
    }

    fun getMovies(input: String) {
        repository.searchMovies(input,
            success = {
                adapter.setItems(it)
                movieListRv.adapter = adapter
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
}
