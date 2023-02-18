package br.com.devlucasyuji.domain.model

/***
 *  Alarm Model
 *
 *  @param id the alarm id
 *  @param plantId the id's plant
 *  @param ring the ring's alarm
 * */
data class Alarm(
    val id: Long = 0,
    val ring: String,
    val plantId: Long,
)
