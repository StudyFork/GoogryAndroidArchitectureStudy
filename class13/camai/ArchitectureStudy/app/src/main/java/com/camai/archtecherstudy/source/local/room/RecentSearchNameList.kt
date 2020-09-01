package com.camai.archtecherstudy.source.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RMNT")
class RecentSearchNameList(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "moviename") var movieName: String?
) {
    constructor() : this(null, "")
}