package com.example.resolution.ui.main

import com.example.resolution.helper.FirebaseHelper

class MainPresenter(private val view: MainView, private val firebaseHelper: FirebaseHelper){
    fun isSignedIn() {
        view.updateView(firebaseHelper.isUserSignedIn())
    }
    fun logout(){
        firebaseHelper.logout()
    }
}