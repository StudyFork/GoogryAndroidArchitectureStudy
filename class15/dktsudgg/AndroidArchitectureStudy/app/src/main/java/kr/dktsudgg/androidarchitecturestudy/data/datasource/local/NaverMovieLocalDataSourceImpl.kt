package kr.dktsudgg.androidarchitecturestudy.data.datasource.local

import kr.dktsudgg.androidarchitecturestudy.R

class NaverMovieLocalDataSourceImpl : NaverMovieLocalDataSource {

    override fun getMovieSearchHistory(): List<String> {
        // 문자열로 저장된 데이터를 파싱하여 리스트로 반환
        return SharedPreferencesTool.getData(
            R.string.movie_preference_file_key,
            R.string.movie_search_history
        ).split("|||").map { it }
    }

    override fun putMovieSearchHistory(query: String) {
        // 새로운 데이터를 기존 데이터에 합쳐 저장하기
        var queryList = getMovieSearchHistory().toMutableList()
        queryList.add(0, query)

        SharedPreferencesTool.putData(
            R.string.movie_preference_file_key,
            R.string.movie_search_history,
            queryList.joinToString(prefix = "", separator = "|||", postfix = "")
        )
    }
}