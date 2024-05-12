plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

apply(from = "$rootDir/config-compose.gradle")
apply(from = "$rootDir/config-android.gradle")

android {
    namespace = "com.ujizin.leafy.features.preferences"
}

dependencies {
    implementation(projects.core.navigation)
    implementation(projects.core.ui)
    implementation(projects.domain)

    implementation(libs.google.review)
}
