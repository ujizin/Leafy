plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

apply(from = "$rootDir/config-compose.gradle")
apply(from = "$rootDir/config-android.gradle")

android {
    namespace = "com.ujizin.leafy.features.tasks"
}

dependencies {
    implementation(projects.domain)

    implementation(projects.core.navigation)
    implementation(projects.core.ui)
}
