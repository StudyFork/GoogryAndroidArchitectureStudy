package kr.dktsudgg.androidarchitecturestudy.data.datasource.local

import kr.dktsudgg.androidarchitecturestudy.R

class NaverMovieLocalDataSourceImpl : NaverMovieLocalDataSource {

    override fun getMovieSearchHistory(): List<String> {
        // 문자열로 저장된 데이터를 파싱하여 리스트로 반환
        return SharedPreferencesTool.getData(
            MOVIE_PREFERENCE_FILE_KEY,
            MOVIE_SEARCH_HISTORY
        ).split("|||").map { it }
    }

    override fun putMovieSearchHistory(query: String) {
        // 새로운 데이터를 기존 데이터에 합쳐 저장하기
        var queryList = getMovieSearchHistory().toMutableList()
        queryList.add(0, query)

        SharedPreferencesTool.putData(
            MOVIE_PREFERENCE_FILE_KEY,
            MOVIE_SEARCH_HISTORY,
            queryList.joinToString(prefix = "", separator = "|||", postfix = "")
        )
    }

    companion object {
        /**
         * 영화 관련 데이터를 보관하는 Shared Preferences File명
         */
        private const val MOVIE_PREFERENCE_FILE_KEY = "MOVIE_DATA"

        /**
         * 영화 검색 이력 데이터를 나타내는 키
         */
        private const val MOVIE_SEARCH_HISTORY = "MOVIE_SEARCH_HISTORY"
    }

}