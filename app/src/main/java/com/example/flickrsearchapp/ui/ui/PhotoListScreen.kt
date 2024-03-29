package com.example.flickrsearchapp.ui.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flickrsearchapp.R
import com.example.flickrsearchapp.model.Photo
import com.example.flickrsearchapp.ui.PhotoUIState
import com.example.flickrsearchapp.ui.PhotosViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalUnitApi::class)
@Composable
fun PhotoListScreen(
    viewModel: PhotosViewModel = viewModel(),
    navigationToDetail: (photo: Photo) -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    val state by viewModel.getState().collectAsState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(text = stringResource(R.string.app_name),
                        fontSize = TextUnit(18f, TextUnitType.Sp))
                })
        },
        content = { _ ->
            Column(modifier = Modifier.fillMaxWidth()) {
                ShowSearchView(onQueryTextListener = { viewModel.searchPhotos(it) })
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp))
                when (val currentState = state) {
                    is PhotoUIState.Loaded -> ShowLoadedList(currentState.uiModel.listOfPhotos,
                        navigationToDetail)
                    PhotoUIState.Loading -> ShowLoading()
                    is PhotoUIState.Error -> ShowError(msg = currentState.msg ?: "")
                }
            }
        }
    )
}

@Composable
fun ShowSearchView(onQueryTextListener: (text: String) -> Unit) {
    Row {
        var text by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(
            value = text,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            onValueChange = {
                text = it
                onQueryTextListener(text)
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black
            ),
            placeholder = { Text("Enter Search Text", color = Color.Gray) }
        )
    }

}

@Composable
fun ShowLoading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .width(50.dp)
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ShowError(msg: String) {
    Snackbar()
    { Text(text = msg) }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ShowLoadedList(
    list: List<Photo>,
    navigationToDetail: (photo: Photo) -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp, 20.dp)
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            content = {
                items(list, itemContent = {
                    PhotoItem(photo = it, navigationToDetail)
                })
            }
        )
    }
}
