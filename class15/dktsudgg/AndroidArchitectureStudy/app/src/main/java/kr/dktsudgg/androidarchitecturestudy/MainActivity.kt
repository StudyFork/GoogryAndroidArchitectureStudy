package kr.dktsudgg.androidarchitecturestudy

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kr.dktsudgg.androidarchitecturestudy.data.model.MovieItem
import kr.dktsudgg.androidarchitecturestudy.data.repository.NaverMovieRepositoryImpl
import kr.dktsudgg.androidarchitecturestudy.databinding.ActivityMainBinding
import kr.dktsudgg.androidarchitecturestudy.view.adapter.MovieListAdapter
import kr.dktsudgg.androidarchitecturestudy.view.ui.MovieSearchContract
import kr.dktsudgg.androidarchitecturestudy.view.ui.MovieSearchPresenter

class MainActivity :
    BaseActivity<MovieSearchPresenter, ActivityMainBinding>(R.layout.activity_main),
    MovieSearchContract.View, View.OnClickListener {

    private lateinit var movieSearchPresenter: MovieSearchContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activity = this@MainActivity

        movieSearchPresenter = MovieSearchPresenter(NaverMovieRepositoryImpl(), this);

        /**
         * 영화 검색결과 리스트 보여주는 RecyclerView에 어댑터 연결 및 목록 구분선 추가
         */
        searchedMovieList.adapter = MovieListAdapter()
        searchedMovieList.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

    }

    /**
     * 클릭 이벤트 처리 메소드
     */
    override fun onClick(clickedView: View) {
        when (clickedView.id) {
            R.id.movieSearchBtn -> {    // 검색 버튼 클릭 시, 검색어 입력한 내용을 가지고 검색 수행
                val searchText = movieSearchEditText.text.toString()
                movieSearchPresenter.searchMovies(searchText)
            }
            R.id.showMovieSearchHistoryBtn -> { // 최근검색이력 버튼 클릭 시, 영화검색이력을 보여주는 액티비티로 이동
                startActivityForResult(
                    Intent(this, MovieSearchHistoryActivity::class.java), ACTIVITY_REQ_CODE
                )
            }
            else -> {
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            ACTIVITY_REQ_CODE -> {  // 반환된 내용을 가지고 EditText에 세팅 및 검색버튼 클릭 수행
                if (resultCode == RESULT_OK) {
                    movieSearchEditText.setText(
                        data?.getStringExtra("selectedKeyword") ?: ""
                    )
                    movieSearchBtn.performClick()
                } else {
                    Toast.makeText(
                        this,
                        "MovieSearchHistoryActivity에서 데이터를 반환하지 않았습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            else -> {
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun updateSearchedMovieList(data: List<MovieItem>) {
        (searchedMovieList.adapter as MovieListAdapter).refreshData(data)
    }

    companion object {
        private const val ACTIVITY_REQ_CODE = 12335
    }

}