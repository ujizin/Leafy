package com.ujizin.leafy.core.ui.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import java.io.File

fun Context.startSettingsPermission() {
    val intent = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.parse(
            "package:$packageName",
        ),
    )
    startActivity(intent)
}

val Context.plantsDir get() = File(filesDir, "plants").apply { mkdirs() }
