plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

apply(from = "$rootDir/config-compose.gradle")
apply(from = "$rootDir/config-android.gradle")

android {
    namespace = "com.ujizin.leafy.features.alarm"

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(projects.core.navigation)
    implementation(projects.core.ui)
    implementation(projects.core.themes)
    implementation(projects.domain)

    testImplementation(projects.core.test)
    testImplementation(libs.bundles.test)
    testImplementation(libs.coroutines.test)
}
