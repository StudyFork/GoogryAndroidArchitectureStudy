package com.camai.archtecherstudy.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface RecentSearchListDao {

    @Query("SELECT DISTINCT moviename FROM RMNT ORDER BY id DESC LIMIT 5")
    fun getListItems(): List<RecentSearchName>

    @Insert(onConflict = REPLACE)
    fun insert(RMNT: RecentSearchName)

    @Query("DELETE FROM RMNT")
    fun deleteAll()
}