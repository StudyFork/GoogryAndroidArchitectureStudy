package com.example.studyapplication.data.model

import io.realm.RealmObject

open class BlogInfo(
    var link: String = "",
    var title: String = "",
    var description: String = "",
    var bloggername: String = "",
    var bloggerlink: String = ""
) : RealmObject()
