package io.github.sooakim.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/*
{
            "title": "<b>울</b>언니",
            "link": "https://movie.naver.com/movie/bi/mi/basic.nhn?code=129790",
            "image": "https://ssl.pstatic.net/imgmovie/mdi/mit110/1297/129790_P07_105449.jpg",
            "subtitle": "",
            "pubDate": "2014",
            "director": "이제락|",
            "actor": "오광록|하은|지성원|조상구|",
            "userRating": "3.68"
}
 */
data class SAMovieModel(
    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("link")
    @Expose
    val link: String,

    @SerializedName("image")
    @Expose
    val image: String,

    @SerializedName("subtitle")
    @Expose
    val subtitle: String,

    @SerializedName("pubDate")
    @Expose
    val pubDate: String,

    @SerializedName("director")
    @Expose
    val director: String,

    @SerializedName("actor")
    @Expose
    val actor: String,

    @SerializedName("userRating")
    @Expose
    val userRating: String
)