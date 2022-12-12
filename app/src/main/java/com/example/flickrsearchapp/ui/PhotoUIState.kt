package com.example.flickrsearchapp.ui

sealed class PhotoUIState {
    data class Loaded(val uiModel: PhotoUiModel) : PhotoUIState()
    data class Error(val msg: String?) : PhotoUIState()
    object Loading : PhotoUIState()
}
