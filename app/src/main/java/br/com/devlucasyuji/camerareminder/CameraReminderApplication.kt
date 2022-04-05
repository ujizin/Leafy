package br.com.devlucasyuji.camerareminder

import android.app.Application
import br.com.devlucasyuji.home.HomeNavigation
import br.com.devlucasyuji.navigation.addNavigators
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CameraReminderApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        addNavigators(
            HomeNavigation
        )
    }
}