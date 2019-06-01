package com.example.resolution.helper


import com.example.resolution.callback.GenericFirebaseResponseListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.FirebaseStorage
class FirebaseHelper {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private var storage: FirebaseStorage = FirebaseStorage.getInstance()
    fun register(email: String, password: String, callback: GenericFirebaseResponseListener<FirebaseUser?>) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback.onSuccess(auth.currentUser)
            } else {
                task.exception?.localizedMessage?.let { callback.onFailure(it) }
            }
        }
    }
    fun login(email: String, password: String, callback: GenericFirebaseResponseListener<FirebaseUser?>) {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                callback.onSuccess(auth.currentUser)
            }
            else{
                task.exception?.localizedMessage?.let { callback.onFailure(it) }
            }
        }
    }
    fun logout(){
        auth.signOut()
    }
    fun isUserSignedIn() : Boolean = auth.currentUser != null
}