package br.com.devlucasyuji.repository.model

/***
 * Contract Photo Model to PhotoDataSource.
 *
 * @param title the photo title
 * @param date the date of the photo
 * @param filePath the file path of the photo
 * @param description the photo description
 * @param favorite indicates if photo is favorite
 */
data class Photo(
    val title: String,
    val date: String,
    val filePath: String,
    val description: String,
    val favorite: Boolean,
)
