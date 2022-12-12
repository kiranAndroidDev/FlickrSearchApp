package com.example.flickrsearchapp.ui

import MainCoroutineRule
import com.example.flickrsearchapp.repo.PhotoRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class PhotosViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val repository: PhotoRepository = mock()

    private val viewModel = PhotosViewModel(repository)

    @Test
    fun `emit Loaded state when repo returns success`() {
        runTest {
            Mockito.`when`(repository.getSearchResultPhotoListStream(any()))
                .thenReturn(flowOf(emptyList()))
            viewModel.getPhotos("test")
            assertEquals(PhotoUIState.Loading,viewModel.getState().value)
            advanceUntilIdle()
            assertEquals(PhotoUIState.Loaded(PhotoUiModel(emptyList())),viewModel.getState().value)
        }
    }

    @Test
    fun `emit Error state when repo throws Error`() {
        runTest {
            Mockito.`when`(repository.getSearchResultPhotoListStream(any()))
                .thenThrow(IllegalArgumentException(""))
            viewModel.getPhotos("test")
            assertEquals(PhotoUIState.Loading,viewModel.getState().value)
            advanceUntilIdle()
            assertEquals(PhotoUIState.Error(""),viewModel.getState().value)
        }
    }
}
