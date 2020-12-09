package kr.dktsudgg.androidarchitecturestudy

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import kr.dktsudgg.androidarchitecturestudy.databinding.ActivityMainBinding
import kr.dktsudgg.androidarchitecturestudy.view.ui.mvvm.MovieSearchViewModel

class MainActivity :
    BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val movieSearchViewModel: MovieSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vm = movieSearchViewModel

        /**
         * 영화 검색 시, 검색어를 입력하지 않았을 경우에 대한 처리 모음
         */
        movieSearchViewModel.eventWhenEmptyInputInjected.observe(this) {
            showToast("검색어를 입력하세요")
        }

        /**
         * 영화 검색 성공 시에 대한 처리 모음
         */
        movieSearchViewModel.eventWhenDataRefreshRequestSuccess.observe(this) {
            showToast("검색에 성공하였습니다.")
        }

        /**
         * 영화 검색 실패 시에 대한 처리 모음
         */
        movieSearchViewModel.eventWhenDataRefreshRequestFailure.observe(this) {
            showToast("검색에 실패하였습니다.")
        }

        /**
         * 영화 검색이력 조회 요청 이벤트
         */
        movieSearchViewModel.eventToShowMovieSearchHistory.observe(this) {
            startActivityForResult(
                Intent(this@MainActivity, MovieSearchHistoryActivity::class.java), ACTIVITY_REQ_CODE
            )
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            ACTIVITY_REQ_CODE -> {  // 반환된 내용을 가지고 EditText에 세팅 및 검색버튼 클릭 수행
                if (resultCode == RESULT_OK) {
                    binding.movieSearchEditText.setText(
                        data?.getStringExtra("selectedKeyword") ?: ""
                    )
                    binding.movieSearchBtn.performClick()
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

    companion object {
        private const val ACTIVITY_REQ_CODE = 12335
    }

}