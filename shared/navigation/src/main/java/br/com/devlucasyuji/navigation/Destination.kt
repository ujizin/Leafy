package br.com.devlucasyuji.navigation

enum class Destination(private val destinationName: String) {
    Home("home");

    val route: String get() = "$HOST/$destinationName"

    companion object {
        private const val HOST = "app://camera-reminder"
    }
}
