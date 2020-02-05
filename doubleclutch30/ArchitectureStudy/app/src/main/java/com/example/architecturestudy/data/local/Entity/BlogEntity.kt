package com.example.architecturestudy.data.local.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.architecturestudy.data.model.BlogItem

@Entity(tableName = "Blog")
data class BlogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val bloggername: String = "",
    val link: String = "",
    val postdate: String = ""
) {
    fun toItem() = BlogItem(
        title = title,
        description = description,
        bloggername = bloggername,
        link = link,
        postdate = postdate
    )
}