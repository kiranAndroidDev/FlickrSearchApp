package com.example.flickrsearchapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickrsearchapp.repo.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val repository: PhotoRepository,
) : ViewModel() {
    private val _state = MutableStateFlow<PhotoUIState>(PhotoUIState.Loading)

    fun getState() = _state

    @OptIn(FlowPreview::class)
    fun getPhotos(searchQuery: String) {
        viewModelScope.launch {
            flowOf(searchQuery)
                .debounce(500)
                .filter {
                    it.trim().isNotEmpty()
                }
                .distinctUntilChanged()
                .flatMapLatest {
                    repository.getSearchResultPhotoListStream(searchQuery)
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
