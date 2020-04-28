package com.example.local.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.local.model.Auth
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface AuthDao {
    @Query("SELECT * FROM auth LIMIT 1")
    fun getAuth(): Single<Auth>

    @Insert
    fun insertAuth(auth: Auth): Completable

    @Query("DELETE FROM auth")
    fun deleteAuth(): Completable
}