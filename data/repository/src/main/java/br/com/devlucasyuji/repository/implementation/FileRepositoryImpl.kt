package br.com.devlucasyuji.repository.implementation

import br.com.devlucasyuji.domain.repository.FileRepository
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

internal class FileRepositoryImpl : FileRepository {

    private fun createUniqueName(extension: String) =
        "${UUID.randomUUID()}-${System.currentTimeMillis()}.${extension}"

    private fun File.createNewUniqueFile(extension: String) =
        File(this, createUniqueName(extension)).apply { createNewFile() }

    override fun saveByteArray(parentFile: File, byteArray: ByteArray, extension: String): File {
        val file = parentFile.createNewUniqueFile(extension)
        FileOutputStream(file).apply {
            write(byteArray)
            close()
        }
        return file
    }
}