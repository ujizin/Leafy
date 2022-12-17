package br.com.devlucasyuji.navigation

import br.com.devlucasyuji.navigation.Args.ImageFilePath

enum class Destination(
    private val destinationName: String,
    val navigationItem: NavigationItem = NavigationItem.None,
) {
    Home("home", NavigationItem.Home),
    Search("search", NavigationItem.Search),
    Camera("camera", NavigationItem.None),
    Alarm("alarm", NavigationItem.Alarm),
    Others("others", NavigationItem.Others),
    Publish("publish/{$ImageFilePath}", NavigationItem.None);

    val route: String get() = "$HOST/$destinationName"

    fun withArguments(vararg arguments: Pair<String, String>): String {
        var destinationName = this.destinationName
        arguments.forEach { (key, value) ->
            destinationName = destinationName.replace("{$key}", value)
        }
        return "$HOST/$destinationName"
    }

    companion object {
        private const val HOST = "app://camera-reminder"

        fun findByName(destinationName: String?) = values().find {
            it.route == destinationName
        }
    }
}
