package br.com.devlucasyuji.navigation

enum class Destination(
    private val destinationName: String,
    val navigationItem: NavigationItem = NavigationItem.None
) {
    Home("home", NavigationItem.Home),
    Search("search", NavigationItem.Search),
    Camera("camera", NavigationItem.None),
    Alarm("alarm", NavigationItem.Alarm),
    Others("others", NavigationItem.Others),
    Preview("preview/{image}", NavigationItem.None);

    val route: String get() = "$HOST/$destinationName"

    fun withArguments(vararg arguments: Pair<String, String>): String {
        var route = this.destinationName
        arguments.forEach { (key, value) ->
            route = route.replace("{$key}", value)
        }
        return route
    }

    companion object {
        private const val HOST = "app://camera-reminder"

        fun findByName(destinationName: String?) = values().find {
            it.route == destinationName
        }
    }
}
