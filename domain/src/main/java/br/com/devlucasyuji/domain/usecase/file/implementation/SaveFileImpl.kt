package br.com.devlucasyuji.domain.usecase.file.implementation

import br.com.devlucasyuji.domain.repository.FileRepository
import br.com.devlucasyuji.domain.usecase.file.SaveFile
import java.io.File

internal class SaveFileImpl(
    private val repository: FileRepository
) : SaveFile {

    override operator fun invoke(
        parentFile: File,
        byteArray: ByteArray,
        extension: String
    ): File = repository.saveByteArray(parentFile, byteArray, extension)
}