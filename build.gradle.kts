import io.gitlab.arturbosch.detekt.Detekt

buildscript {
    dependencies {
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${libs.versions.detekt}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${libs.versions.kotlin}")
        classpath(libs.google.services)
        classpath(libs.firebase.crashlytics)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.detekt)
    alias(libs.plugins.spotless)
}

subprojects {
    apply(plugin = rootProject.libs.plugins.spotless.get().pluginId)
    apply(from = "${project.rootDir}/spotless.gradle")
}

detekt {
    toolVersion = rootProject.libs.versions.detekt.toString()
    config = files("config/detekt.yml")
    buildUponDefaultConfig = true
}

tasks.withType<Detekt>().configureEach {
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
    }
}

apply(from = "config-properties.gradle.kts")