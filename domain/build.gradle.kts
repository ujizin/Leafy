plugins {
    alias(libs.plugins.android.library)
}

apply(from = "../config-android.gradle")

android {
    namespace = "com.ujizin.leafy.domain"
}

dependencies {
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.bundles.test)
    testImplementation(projects.core.test)
}
