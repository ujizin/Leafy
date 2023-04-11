package com.ujizin.leafy.core.ui.extensions

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import com.ujizin.leafy.domain.model.Plant

fun Plant.share(context: Context) {
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )
        setDataAndType(uri, "image/*")
        putExtra(Intent.EXTRA_STREAM, uri)
        putExtra(Intent.EXTRA_TEXT, "$title - $description")
    }
    context.startActivity(Intent.createChooser(intent, "Share plant using"))
}