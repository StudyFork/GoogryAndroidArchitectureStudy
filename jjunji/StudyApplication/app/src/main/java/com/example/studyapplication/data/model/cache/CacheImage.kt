package com.example.studyapplication.data.model.cache

import com.example.studyapplication.data.model.ImageInfo
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CacheImage : RealmObject() {
    @PrimaryKey
    open var id : Int = 1
    open var imageList : RealmList<ImageInfo> = RealmList()
}