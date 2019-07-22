package study.architecture.myarchitecture.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "markets")
data class UpbitMarket(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @SerializedName("market") val market: String,
    @SerializedName("korean_name") val koreanName: String,
    @SerializedName("english_name") val englishName: String
)