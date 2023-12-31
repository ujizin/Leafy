package com.ujizin.leafy.core.navigation

enum class Destination(
    private val destinationName: String,
) {
    Home("home"),
    Search("search?auto_focus={${Args.SearchAutoFocus}}"),
    Camera("camera"),
    Tasks("tasks"),
    Preferences("preferences"),
    Publish("publish"),
    Alarm("alarm"),
    About("about"),
    PlantDetails("plant/{${Args.PlantId}}"),
    PlantEdit("plant/${Args.PlantId}"),
    ;

    val route: String get() = "$HOST/$destinationName"

    fun withArguments(vararg arguments: Pair<String, Any>): String {
        var destinationName = this.destinationName
        arguments.forEach { (key, value) ->
            destinationName = destinationName.replace("{$key}", "$value")
        }
        return "$HOST/$destinationName"
    }

    companion object {
        private const val HOST = "app://leafy"

        fun findByName(destinationName: String?) = entries.find {
            it.route == destinationName
        }
    }
}
