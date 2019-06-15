package com.example.resolution.ui.register

import com.example.resolution.R
import com.example.resolution.callback.GenericFirebaseResponseListener
import com.example.resolution.helper.FirebaseHelper
import com.google.firebase.auth.FirebaseUser

class RegisterPresenter (private val view: RegisterView, private val firebaseHelper: FirebaseHelper): GenericFirebaseResponseListener<FirebaseUser?>{

    fun register(email: String, password: String) {
        firebaseHelper.register(email, password, this)
        view.setLoading(true)
    }
    override fun onSuccess(response: FirebaseUser?) {
        view.setLoading(false)
        view.updateView(response)
    }

    override fun onFailure(message: String) {
        view.showMessage(message)
        view.setLoading(false)
    }

    override fun onFailure(messageResId: Int) {
        view.showMessage(messageResId)
        view.setLoading(false)
    }
}