package br.com.devlucasyuji.domain.usecase.file

import java.io.File

interface SaveFile {

    /**
     * Save byte array in file.
     *
     * @param parentFile the parent file where's to be saved.
     * @param byteArray the byte array to be saved.
     * @param extension extension from file.
     * */
    operator fun invoke(parentFile: File, byteArray: ByteArray, extension: String): File
}
