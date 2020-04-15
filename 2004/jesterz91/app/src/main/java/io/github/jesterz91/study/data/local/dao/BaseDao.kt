package io.github.jesterz91.study.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface BaseDao<T> {

    @Insert
    fun insert(data: T)

    @Delete
    fun delete(data: T)

    @Update
    fun update(data: T)
}