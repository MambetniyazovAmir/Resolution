package com.example.resolution.ui.main

import android.content.Context
import android.net.Uri
import com.example.resolution.helper.FirebaseHelper
import android.graphics.Bitmap
import android.provider.MediaStore.Images
import android.widget.Toast
import com.example.resolution.callback.GenericFirebaseResponseListener
import com.example.resolution.data.ImageModel

class MainPresenter(private val view: MainView, private val firebaseHelper: FirebaseHelper): GenericFirebaseResponseListener<ImageModel> {
    fun isSignedIn() {
        view.updateView(firebaseHelper.isUserSignedIn())
    }
    fun logout(){
        firebaseHelper.logout()
        isSignedIn()
    }
    fun pickImage(context: Context,uri: Uri?) {
        val bitmap = Images.Media.getBitmap(context.contentResolver, uri)
        val resizedBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.width*2, bitmap.height*2, false)
        val path = Images.Media.insertImage(context.contentResolver, resizedBitmap, "Title", null)
        val superImageUri = Uri.parse(path)
        if (uri != null && superImageUri != null) {
            firebaseHelper.upload(uri, superImageUri, this)
            view.setLoading(true)
        }
    }

    override fun onSuccess(response: ImageModel) {
        view.setLoading(false)
        view.showResizedImage(response)
    }

    override fun onFailure(message: String) {
        view.showMessage(message)
    }

    override fun onFailure(messageResId: Int) {
        view.showMessage(messageResId)
    }
}