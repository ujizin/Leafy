package br.com.devlucasyuji.alarm.model

import android.content.Context
import androidx.annotation.StringRes
import br.com.devlucasyuji.alarm.R
import java.util.concurrent.TimeUnit

enum class RepeatMode(@StringRes val display: Int) {
    Once(R.string.once),
    Daily(R.string.daily),
    MonToFriday(R.string.mon_to_friday);

    val intervalTimeMillis: Long
        get() = when (this) {
            Once -> NO_REPEAT
            Daily -> TimeUnit.DAYS.toMillis(1)
            MonToFriday -> REPEAT_MON_TO_FRIDAY
        }

    companion object {
        internal const val NO_REPEAT = -1L
        internal const val REPEAT_MON_TO_FRIDAY = -2L


        fun getByDisplayValue(context: Context, value: String) = values().first {
            context.getString(it.display) == value
        }
    }
}