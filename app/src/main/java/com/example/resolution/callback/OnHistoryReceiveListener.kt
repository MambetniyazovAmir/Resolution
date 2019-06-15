package com.example.resolution.callback

import com.example.resolution.data.ImageModel

interface OnHistoryReceiveListener {
    fun onResponse(models: List<ImageModel>)
    fun onFailure(message: String)
}