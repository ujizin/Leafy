package br.com.devlucasyuji.domain.repository

import java.io.File

interface FileRepository {
    /**
     * Save byte array in file.
     *
     * @param parentFile the parent file where's to be saved.
     * @param byteArray the byte array to be saved.
     * @param extension extension from file.
     * */
    fun saveByteArray(parentFile: File, byteArray: ByteArray, extension: String): File
}