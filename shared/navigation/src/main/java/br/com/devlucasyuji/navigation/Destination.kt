package br.com.devlucasyuji.navigation

enum class Destination(
    private val destinationName: String,
    val navigationItem: NavigationItem = NavigationItem.None
) {
    Home("home", NavigationItem.Home),
    Search("search", NavigationItem.Search),
    Camera("camera", NavigationItem.Camera),
    Alarm("alarm", NavigationItem.Alarm),
    Others("others", NavigationItem.Others);

    val route: String get() = "$HOST/$destinationName"

    companion object {
        private const val HOST = "app://camera-reminder"

        fun findByName(destinationName: String?) = values().find {
            it.route == destinationName
        }
    }
}
