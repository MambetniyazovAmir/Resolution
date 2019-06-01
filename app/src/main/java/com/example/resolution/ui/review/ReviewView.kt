package com.example.resolution.ui.review

import android.net.Uri

interface ReviewView {
    fun showOriginalImage(uri: Uri)
    fun showSuperImage(uri: Uri)
    fun saveImage()
}