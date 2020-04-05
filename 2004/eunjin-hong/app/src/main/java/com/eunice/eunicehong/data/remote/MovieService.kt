package com.eunice.eunicehong.data.remote

import com.eunice.eunicehong.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieService {

    /**
     * @param query : 검색을 원하는 질의. UTF-8 인코딩이다.
     * @param display : 기본값 10, 최대 100	검색 결과 출력 건수를 지정한다. 최대 100까지 가능하다.
     * @param start : 기본값 1, 최대 1000	검색의 시작 위치를 지정할 수 있다. 최대 1000까지 가능하다.
     * @param genre : 검색을 원하는 장르 코드를 의미한다.
     *  1: 드라마 2: 판타지 3: 서부 4: 공포 5: 로맨스 6: 모험 7: 스릴러 8: 느와르 9: 컬트 10: 다큐멘터리 11: 코미디 12: 가족 13: 미스터리 14: 전쟁 15: 애니메이션 16: 범죄 17: 뮤지컬 18: SF 19: 액션20: 무협 21: 에로 22: 서스펜스 23: 서사 24: 블랙코미디 25: 실험 26: 영화카툰 27: 영화음악 28: 영화패러디포스터
     * @param country : 검색을 원하는 국가 코드를 의미한다. 국가코드는 대문자만 사용 가능하다.
     *  한국 (KR), 일본 (JP), 미국 (US), 홍콩 (HK), 영국 (GB), 프랑스 (FR), 기타 (ETC)
     * @param yearFrom : 검색을 원하는 영화의 제작년도(최소)를 의미한다. yearfrom은 yearto와 함께 사용되어야 한다.
     * @param yearTo : 검색을 원하는 영화의 제작년도(최대)를 의미한다. yearto는 yearfrom과 함께 사용되어야 한다.
     */
    @GET("/v1/search/movie.json")
    fun getMovieList(
        @Query("query") query: String,

        @Header("X-Naver-Client-Id") clientId: String = BuildConfig.ClientId,
        @Header("X-Naver-Client-Secret") clientSecret: String = BuildConfig.ClientSecret,
        @Query("display") display: Int? = null,
        @Query("start") start: Int? = null,
        @Query("genre") genre: Int? = null,
        @Query("country") country: String? = null,
        @Query("yearfrom") yearFrom: Int? = null,
        @Query("yearto") yearTo: Int? = null
    ): Call<MovieList>
}
