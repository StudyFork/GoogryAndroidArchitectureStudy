package kr.dktsudgg.androidarchitecturestudy.data.datasource.local

interface NaverMovieLocalDataSource {

    /**
     * 최근 영화검색 이력 불러오기
     */
    fun getMovieSearchHistory(): List<String>

    /**
     * 영화검색 이력 저장
     */
    fun putMovieSearchHistory(query: String)
}