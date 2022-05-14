package br.com.devlucasyuji.repository.model

/***
 *  Contract Alarm Model to PhotoDataSource
 *
 *  @param id the alarm id
 *  @param photoId the id's photo
 *  @param ring the ring's alarm
 * */
data class Alarm(
    val id: Long = 0,
    val ring: String,
    val photoId: Long,
)
