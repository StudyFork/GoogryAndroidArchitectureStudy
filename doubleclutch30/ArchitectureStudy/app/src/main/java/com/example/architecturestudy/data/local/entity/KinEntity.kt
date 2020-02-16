package com.example.architecturestudy.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.architecturestudy.data.model.KinItem

@Entity(tableName = "kin")
data class KinEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val link: String = ""
) {
    fun toItem() = KinItem(
        title = title,
        description = description,
        link = link
    )
}