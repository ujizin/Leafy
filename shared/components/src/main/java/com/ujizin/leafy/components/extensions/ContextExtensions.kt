package com.ujizin.leafy.components.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

fun Context.startSettingsPermission() {
    val intent = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.parse("package:${packageName}"
        )
    )
    startActivity(intent)
}