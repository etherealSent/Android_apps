package com.example.exercise202

import android.content.res.AssetManager

class AssetFileManager(private val assetManager: AssetManager) {
    fun getMyAppFileInputStream() = assetManager.open("my-app-file.txt")
}