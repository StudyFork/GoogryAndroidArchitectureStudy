package kr.dktsudgg.androidarchitecturestudy.api.naver.data

/**
 * 네이버 영화 API 응답 데이터 - 개별 검색 결과
 * https://developers.naver.com/docs/search/movie/
 */
data class MovieItem(
    val title: String,
    val subtitle: String,
    val director: String,
    val actor: String,
    val userRating: String,
    val image: String,
    val link: String,
    val pubDate: String
)