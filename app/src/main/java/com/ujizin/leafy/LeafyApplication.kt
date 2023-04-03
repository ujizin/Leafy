package com.ujizin.leafy

import android.app.Application
import com.ujizin.leafy.alarm.AlarmNotificator
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LeafyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AlarmNotificator.createChannel(this)
    }
}
