package com.example.studyapplication.data.model

import io.realm.RealmObject

open class ImageInfo(
    var thumbnail: String = "",
    var link: String = "",
    var title: String = ""
) : RealmObject()