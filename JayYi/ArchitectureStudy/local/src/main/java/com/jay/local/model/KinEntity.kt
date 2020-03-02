package com.jay.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kin")
data class KinEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val description: String,
    val link: String,
    val title: String
)

data class KinLocalData(
    val kins: List<KinEntity>
)