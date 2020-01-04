package com.jay.architecturestudy.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kin")
data class KinItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val description: String,
    val link: String,
    val title: String
)