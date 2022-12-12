package com.example.flickrsearchapp.ui.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.flickrsearchapp.model.Photo
import com.example.flickrsearchapp.utils.getUrl
import com.example.flickrsearchapp.R

@OptIn(ExperimentalUnitApi::class)
@Composable
fun PhotoItem(
    photo: Photo,
    navigationToDetail: (photo: Photo) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { navigationToDetail.invoke(photo) },
        elevation = 6.dp,
        border = BorderStroke(0.dp, Color.Gray),
        shape = RoundedCornerShape(4.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            drawProfileImage(getUrl(photo))
            Spacer(Modifier.padding(8.dp, 0.dp))
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(4.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = photo.title ?: "",
                    color = Color.White,
                    fontSize = TextUnit(18f, TextUnitType.Sp))
            }
        }
    }
}

@Composable
fun drawProfileImage(url: String) {
    AsyncImage(
        model = url,
        placeholder = rememberAsyncImagePainter(model = R.drawable.ic_launcher_foreground),
        contentDescription = null,
        modifier = Modifier.fillMaxWidth(),
        contentScale = ContentScale.FillWidth
    )
}

