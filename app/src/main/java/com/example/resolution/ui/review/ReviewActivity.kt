package com.example.resolution.ui.review

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.resolution.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_review.*

class ReviewActivity : AppCompatActivity() {

    companion object {
        const val ORIGINAL_IMAGE_URL = "originalImageUrl"
        const val SUPER_IMAGE_URL = "superImageUrl"
    }

    private var originalImageUrl: String = ""
    private var superImageUrl: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        originalImageUrl = intent.getStringExtra(ORIGINAL_IMAGE_URL)
        superImageUrl = intent.getStringExtra(SUPER_IMAGE_URL)

        afterButton.setOnClickListener {
            afterButton.setBackgroundColor(resources.getColor(R.color.colorBackground))
            afterButton.setTextColor(resources.getColor(R.color.white))
            beforeButton.setTextColor(resources.getColor(R.color.colorBackground))
            beforeButton.setBackgroundColor(resources.getColor(R.color.white))
            showSuperImage()
        }
        beforeButton.setOnClickListener {
            afterButton.setBackgroundColor(resources.getColor(R.color.white))
            afterButton.setTextColor(resources.getColor(R.color.colorBackground))
            beforeButton.setTextColor(resources.getColor(R.color.white))
            beforeButton.setBackgroundColor(resources.getColor(R.color.colorBackground))
            showOriginalImage()
        }
    }

    override fun onStart() {
        super.onStart()
        showSuperImage()
    }

    private fun showSuperImage() {
        Picasso.get()
            .load(superImageUrl)
            .into(image)
    }

    private fun showOriginalImage() {
        Picasso.get()
            .load(originalImageUrl)
            .into(image)
    }
}
