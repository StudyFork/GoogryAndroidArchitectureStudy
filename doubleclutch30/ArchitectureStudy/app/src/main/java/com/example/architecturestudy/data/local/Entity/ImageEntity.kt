package com.example.architecturestudy.data.local.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.architecturestudy.data.model.ImageItem

@Entity(tableName = "image")
data class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val thumbnail:String =  "",
    val sizeheight: String = "",
    val sizewidth: String = "",
    val link: String
) {
    fun toItem() = ImageItem(
        thumbnail = thumbnail,
        sizeheight = sizeheight,
        sizewidth = sizewidth,
        link = link
    )
}