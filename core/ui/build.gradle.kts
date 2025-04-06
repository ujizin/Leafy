plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

apply(from = "$rootDir/config-compose.gradle")
apply(from = "$rootDir/config-android.gradle")

android {
    namespace = "com.ujizin.leafy.core.components"
}

dependencies {
    implementation(projects.core.themes)
    implementation(projects.core.navigation)
    implementation(projects.domain)

    implementation(libs.coil)
    implementation(libs.accompanist.permissions)
    implementation(libs.androidx.app.compat)
}
