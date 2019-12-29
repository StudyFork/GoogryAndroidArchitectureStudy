package wooooooak.com.studyapp.data.model.response.kin

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Kin(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("description")
    val description: String
)