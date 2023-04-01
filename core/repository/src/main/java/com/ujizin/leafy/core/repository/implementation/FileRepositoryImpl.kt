package com.ujizin.leafy.core.repository.implementation

import android.graphics.Bitmap
import com.ujizin.leafy.domain.repository.FileRepository
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

internal class FileRepositoryImpl : FileRepository {

    private fun createUniqueName(extension: String) =
        "${UUID.randomUUID()}-${System.currentTimeMillis()}.$extension"

    private fun File.createNewUniqueFile(extension: String) =
        File(this, createUniqueName(extension)).apply { createNewFile() }

    override fun saveBitmap(parentFile: File, bitmap: Bitmap, extension: String): File {
        val file = parentFile.createNewUniqueFile(extension)
        val stream = ByteArrayOutputStream()

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)

        FileOutputStream(file).apply {
            write(stream.toByteArray())
            close()
        }
        return file
    }
}
