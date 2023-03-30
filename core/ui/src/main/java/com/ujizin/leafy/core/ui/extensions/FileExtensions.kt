package com.ujizin.leafy.core.ui.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.os.Build
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

fun ByteArrayOutputStream.decodeToBitmapWithRotation(): Bitmap? {
    val byteArray = toByteArray()
    val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size) ?: return null

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) return bitmap

    val orientation = ExifInterface(ByteArrayInputStream(byteArray)).getAttributeInt(
        ExifInterface.TAG_ORIENTATION,
        ExifInterface.ORIENTATION_UNDEFINED
    )

    val degrees = when (orientation) {
        ExifInterface.ORIENTATION_ROTATE_90 -> 90F
        ExifInterface.ORIENTATION_ROTATE_180 -> 180F
        ExifInterface.ORIENTATION_ROTATE_270 -> 270F
        else -> 0F
    }

    val matrix = Matrix().apply { postRotate(degrees) }

    return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
}
