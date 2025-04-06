plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}
android {
    namespace = "com.ujizin.leafy.core.themes"
}

apply(from = "$rootDir/config-compose.gradle")
apply(from = "$rootDir/config-android.gradle")
