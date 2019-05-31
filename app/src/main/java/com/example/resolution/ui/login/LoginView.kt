package com.example.resolution.ui.login

import com.google.firebase.auth.FirebaseUser

interface LoginView {
    fun setLoading(isLoading: Boolean)
    fun updateView(user: FirebaseUser?)
    fun showMessage(message: String)
    fun showMessage(messageResId: Int)
}