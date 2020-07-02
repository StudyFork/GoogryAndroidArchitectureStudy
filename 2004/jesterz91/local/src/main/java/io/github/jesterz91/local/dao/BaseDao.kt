package io.github.jesterz91.local.dao

import androidx.room.*

@Dao
internal interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: List<T>)

    @Delete
    fun delete(data: T)

    @Update
    fun update(data: T)
}