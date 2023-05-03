package com.example.flickrsearchapp.utils

import com.example.flickrsearchapp.model.Photo

fun Photo.getThumbnail(): String {
    val url = "https://farm${this.farm}.static.flickr.com/${this.server}/" +
            "${this.id}_${this.secret}.jpg"
    url.filterNot { it.isWhitespace() }
    return url
}
