package com.example.flickrsearchapp.utils

import com.example.flickrsearchapp.model.Photo

fun getUrl(photo: Photo): String {
    val url = "https://farm${photo.farm}.static.flickr.com/${photo.server}/" +
            "${photo.id}_${photo.secret}.jpg"
    url.filterNot { it.isWhitespace() }
    return url
}
