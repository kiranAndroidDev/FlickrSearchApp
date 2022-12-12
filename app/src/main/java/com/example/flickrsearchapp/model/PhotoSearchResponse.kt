package com.example.flickrsearchapp.model


data class PhotoSearchResponse(val photos: Photos) {
    data class Photos(
        var photo: ArrayList<Photo>,
    )
}
