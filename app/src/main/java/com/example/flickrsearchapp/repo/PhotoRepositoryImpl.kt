package com.example.flickrsearchapp.repo

import com.example.flickrsearchapp.data.api.PhotoApiService
import com.example.flickrsearchapp.model.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val photoApiService: PhotoApiService,
) :
    PhotoRepository {
    override suspend fun getSearchResultPhotoListStream(searchQuery: String): Flow<List<Photo>> {
        return flowOf(photoApiService.getPhotoSearchList(searchQuery).photos.photo)
    }
}


