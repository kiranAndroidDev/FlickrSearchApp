package com.example.flickrsearchapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickrsearchapp.repo.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val repository: PhotoRepository,
) : ViewModel() {
    private val _state = MutableStateFlow<PhotoUIState>(PhotoUIState.Loading)

    fun getState() = _state

    private val searchQueryFlow: MutableSharedFlow<String> = MutableSharedFlow()

    init {
        getPhotosResult()
    }

    fun searchPhotos(searchQuery: String) {
        viewModelScope.launch {
            searchQueryFlow.emit(searchQuery)
        }
    }

    @OptIn(FlowPreview::class)
    private fun getPhotosResult() {
        viewModelScope.launch {
            searchQueryFlow
                .debounce(500)
                .filter {
                    Log.e("data", it)
                    it.trim().isNotEmpty()
                }
                .distinctUntilChanged()
                .flatMapLatest {
                    repository.getSearchResultPhotoListStream(it)
                }
                .catch { error ->
                    _state.emit(PhotoUIState.Error(error.localizedMessage))
                }
                .collect { result ->
                    _state.emit(PhotoUIState.Loaded(PhotoUiModel(result)))
                }
        }
    }
}
