package com.example.exercise202.storage.filesystem

import android.content.Context
import org.apache.commons.io.IOUtils
import java.io.File
import java.io.InputStream

class ProviderFileManager(
    private val context: Context,
    private val fileToUriMapper: FileToUriMapper,
) {
    fun writeStream(name: String, inputStream: InputStream) {
        val fileToSave = File(context.externalCacheDir, name)
        val outputStream = context.contentResolver.openOutputStream(
            fileToUriMapper.getUriFromFile(
                context,
                fileToSave
            ), "rw"
        )
        IOUtils.copy(inputStream, outputStream)
    }
}