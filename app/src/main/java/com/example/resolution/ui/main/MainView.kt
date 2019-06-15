package com.example.resolution.ui.main

import com.example.resolution.data.ImageModel

interface MainView {
    fun showResizedImage(model: ImageModel)
    fun updateView(isSignedIn: Boolean)
    fun setLoading(isLoading: Boolean)
    fun showMessage(message: String)
    fun showMessage(messageResId: Int)
    fun setData(models: List<ImageModel>)
}