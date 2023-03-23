package br.com.devlucasyuji.navigation


enum class Destination(
    private val destinationName: String,
) {
    Home("home"),
    Search("search?auto_focus={${Args.SearchAutoFocus}}"),
    Camera("camera"),
    Alarms("alarms"),
    Others("others"),
    Publish("publish"),
    Alarm("alarm"),
    About("about"),
    Review("review"),
    Preferences("preferences");

    val route: String get() = "$HOST/$destinationName"

    fun withArguments(vararg arguments: Pair<String, Any>): String {
        var destinationName = this.destinationName
        arguments.forEach { (key, value) ->
            destinationName = destinationName.replace("{$key}", "$value")
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
