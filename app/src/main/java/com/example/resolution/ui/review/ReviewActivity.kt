package com.example.resolution.ui.review

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.resolution.R
import com.example.resolution.data.ImageModel
import kotlinx.android.synthetic.main.activity_review.*

class ReviewActivity : AppCompatActivity(), ReviewView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        lateinit var model: ImageModel
        model.userId = intent.getStringExtra("userId").toString()
        model.originalImage = intent.getStringExtra("originalName")
        model.superImage= intent.getStringExtra("superImage")
    }

    override fun showOriginalImage(uri: Uri) {
        originalImage.setImageURI(uri)
    }

    override fun showSuperImage(uri: Uri) {

    }

    override fun saveImage() {

    }
}
