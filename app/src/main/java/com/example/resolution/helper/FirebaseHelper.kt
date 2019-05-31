package com.example.resolution.helper

import com.example.resolution.R
import com.example.resolution.callback.GenericFirebaseResponseListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseHelper {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun register(email: String, password: String, callback: GenericFirebaseResponseListener<FirebaseUser?>) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback.onSuccess(auth.currentUser)
            } else {
                callback.onFailure(R.string.register_error)
            }
        }
    }
    fun login(email: String, password: String, callback: GenericFirebaseResponseListener<FirebaseUser?>) {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                callback.onSuccess(auth.currentUser)
            }
            else{
                callback.onFailure(R.string.register_error)
            }
        }

    }
    fun logout(){
        auth.signOut()
    }
    fun isUserSignedIn() : Boolean = auth.currentUser != null
}