package com.camai.archtecherstudy.data.source.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RMNT")
class RecentSearchName(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "moviename") var movieName: String?
) {
    constructor() : this(null, "")
}