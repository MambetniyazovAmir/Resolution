package com.example.resolution.helper


import android.net.Uri
import android.util.Log
import com.example.resolution.callback.GenericFirebaseResponseListener
import com.example.resolution.data.ImageModel
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.util.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.collections.HashMap


class FirebaseHelper {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private var storage: FirebaseStorage = FirebaseStorage.getInstance()
    private var mStorageRef: StorageReference? = null
    var db = FirebaseFirestore.getInstance()
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
    fun upload(originalUri: Uri, superUri: Uri, callback: GenericFirebaseResponseListener<ImageModel>) {
        mStorageRef = storage.reference.child("images/${originalUri.lastPathSegment}")
        mStorageRef?.putFile(originalUri)
            ?.continueWithTask {
                if (!it.isSuccessful) {
                    it.exception?.let {
                        throw it
                    }
                }
                return@continueWithTask mStorageRef!!.downloadUrl
            }
            ?.addOnCompleteListener { task ->
                when (task.isSuccessful) {
                    true -> {
                        val originalLink = task.result.toString()
                        mStorageRef?.putFile(superUri)
                            ?.continueWithTask {
                                if (!it.isSuccessful) {
                                    it.exception?.let {
                                        throw it
                                    }
                                }
                                return@continueWithTask mStorageRef!!.downloadUrl
                            }
                            ?.addOnCompleteListener { task ->
                                when(task.isSuccessful) {
                                    true -> {
                                        val superLink = task.result.toString()
                                        val user = HashMap<String, Any?>()
                                        user["original_image"] = originalLink
                                        user["super_image"] = superLink
                                        user["user_id"] = auth.currentUser?.uid
                                        db.collection("images")
                                            .add(user)
                                            .addOnSuccessListener {
                                                callback.onSuccess(ImageModel(auth.currentUser!!.uid, originalLink, superLink))
                                            }
                                            .addOnFailureListener {e ->
                                                //Log.d("Soski", e.localizedMessage)
                                                callback.onFailure(e.localizedMessage)
                                            }
                                    }
                                    false -> {
                                        task.exception?.localizedMessage
                                    }
                                }
                            }
                    }
                    false -> task.exception?.localizedMessage
                }
            }
    }
}