package com.cnm.homework.data.source.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocal(localEntity: LocalEntity)

    @Query("SELECT * FROM local ")
    fun loadLocal(): LiveData<List<LocalEntity>>
}