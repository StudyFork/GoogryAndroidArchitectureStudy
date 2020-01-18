package com.cnm.homework.data.source.local

import com.cnm.homework.data.source.local.db.LocalEntity
import io.reactivex.Single

interface NaverQueryLocalDataSource {
    fun saveCacheMovie(localEntity: LocalEntity)

    fun loadLocal(): List<LocalEntity>
}