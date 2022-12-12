package com.example.flickrsearchapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.flickrsearchapp.ui.photo_preview.PhotoPreviewActivity
import com.example.flickrsearchapp.ui.ui.PhotoListScreen
import com.example.flickrsearchapp.ui.ui.theme.FlickrSearchAppTheme
import com.example.flickrsearchapp.utils.getUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlickrSearchAppTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    PhotoListScreen(navigationToDetail = {
                        startActivity(PhotoPreviewActivity.getIntentForPhotoPreviewActivity(this,
                            getUrl(it)))
                    })
                }
            }
        }
    }
}

