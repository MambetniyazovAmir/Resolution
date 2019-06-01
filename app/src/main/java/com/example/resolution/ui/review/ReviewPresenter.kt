package com.example.resolution.ui.review

import com.example.resolution.data.ImageModel

class ReviewPresenter(private val view: ReviewView) {
    fun showOriginal(model: ImageModel){
        view.showOriginalImage(model.originalImage)
    }
    fun showSuper(model: ImageModel){
        view.showSuperImage(model.superImage)
    }
    fun saveImage(model: ImageModel){
        view.saveImage()
    }
}