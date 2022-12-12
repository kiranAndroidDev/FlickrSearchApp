package com.example.flickrsearchapp.ui.photo_preview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.flickrsearchapp.ui.ui.theme.FlickrSearchAppTheme

const val URL = "photo_url"

class PhotoPreviewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlickrSearchAppTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = Color.Transparent) {
                    PhotoPreviewScreen(intent?.getStringExtra(URL) ?: "")
                }
            }
        }
    }

    companion object {
        fun getIntentForPhotoPreviewActivity(context: Context, url: String) =
            Intent(context, PhotoPreviewActivity::class.java).apply {
                putExtra(URL, url)
            }
    }

}

