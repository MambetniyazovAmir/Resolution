package com.example.resolution.ui.register

import com.google.firebase.auth.FirebaseUser

interface RegisterView {
    fun setLoading(boolean: Boolean)
    fun updateView(firebaseUser: FirebaseUser?)
    fun showMessage(message: String)
    fun showMessage(messageResId: Int)
}