package com.example.flickrsearchapp.repo

import com.example.flickrsearchapp.model.Photo
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
   suspend fun getSearchResultPhotoListStream(searchQuery: String): Flow<List<Photo>>
}
