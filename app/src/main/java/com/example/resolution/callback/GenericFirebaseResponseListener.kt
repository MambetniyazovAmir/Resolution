package com.example.resolution.callback

interface GenericFirebaseResponseListener<T> {
    fun onSuccess(response: T)
    fun onFailure(message: String)
    fun onFailure(messageResId: Int)
}