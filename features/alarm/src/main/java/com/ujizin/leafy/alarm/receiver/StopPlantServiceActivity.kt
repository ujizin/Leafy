package com.ujizin.leafy.alarm.receiver

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.ujizin.leafy.alarm.AlarmService
import com.ujizin.leafy.alarm.extensions.getAlarmIntent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StopPlantServiceActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stopAlarmService(this)
        finish()
    }

    private fun stopAlarmService(context: Context?) {
        val stopAlarmIntent = context?.getAlarmIntent(AlarmService.STOP_ACTION)
        context?.startService(stopAlarmIntent)
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, StopPlantServiceActivity::class.java)
    }
}
