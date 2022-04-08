package br.com.devlucasyuji.components.extensions

val String.Companion.Empty get() = ""

fun String.capitalize() = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase() else it.toString()
}