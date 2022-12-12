package com.example.flickrsearchapp.model

data class Photo(
    val id: String,
    val secret: String?,
    val server: String?,
    val farm: Int?,
    val title: String?,
    val index: Int,
)
