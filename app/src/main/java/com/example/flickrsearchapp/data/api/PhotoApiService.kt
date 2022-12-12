package com.example.flickrsearchapp.data.api

import com.example.flickrsearchapp.model.PhotoSearchResponse
import com.example.flickrsearchapp.utils.FLICKR_API_KEY
import com.example.flickrsearchapp.utils.RESPONSE_FORMAT
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApiService {
    @GET(".")
    suspend fun getPhotoSearchList(
        @Query("text") searchQuery: String,
        @Query("per_page") numberOfPhotosPerPage: Int = 25,
        @Query("method") method: String = "flickr.photos.search",
        @Query("nojsoncallback") noJson: Int = 50,
        @Query("format") format: String = RESPONSE_FORMAT,
        @Query("api_key") apiKey: String = FLICKR_API_KEY,
    ): PhotoSearchResponse
}
