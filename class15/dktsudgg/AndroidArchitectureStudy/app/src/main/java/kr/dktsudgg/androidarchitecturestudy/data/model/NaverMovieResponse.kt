package kr.dktsudgg.androidarchitecturestudy.data.model

/**
 * 네이버 영화 API 응답 데이터
 * https://developers.naver.com/docs/search/movie/
 */
data class NaverMovieResponse(
    val lastBuildDate: String,  // 검색 결과를 생성한 시간이다.
    val total: Int,             // 검색된 총 document size.
    val start: Int,             // 검색의 시작 위치를 지정할 수 있다. 최대 1000까지 가능하다. 기본값 1, 최대 1000
    val display: Int,           // 검색 결과 출력 건수를 지정한다. 최대 100까지 가능하다. 기본값 10, 최대 100
    val items: List<MovieItem>? // 개별 검색 결과이며 title, link, image, subtitle, pubDate, director, actor, userRating을 포함한다.
)