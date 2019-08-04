package study.architecture.model.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Market(
    @PrimaryKey @SerializedName("english_name") val englishName: String,
    @SerializedName("korean_name") val koreanName: String,
    @SerializedName("market") val market: String
)