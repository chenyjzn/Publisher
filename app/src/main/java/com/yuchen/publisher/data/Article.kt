package com.yuchen.publisher.data

data class Article(
    val author: Author = Author(),
    val content: String = "",
    val createdTime: Long = 0L,
    val id: String = "",
    val tag: String = "",
    val title: String = ""
)
