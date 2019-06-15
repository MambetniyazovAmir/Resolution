package com.example.resolution.ui.login

import com.example.resolution.callback.GenericFirebaseResponseListener
import com.example.resolution.helper.FirebaseHelper
import com.google.firebase.auth.FirebaseUser

class LoginPresenter(private val view: LoginView, private val firebaseHelper: FirebaseHelper): GenericFirebaseResponseListener<FirebaseUser?> {

    fun login(email: String, password : String){
        view.setLoading(true)
        firebaseHelper.login(email, password, this)
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