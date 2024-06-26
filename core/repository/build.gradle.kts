plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

apply(from = "../../config-android.gradle")

android {
    namespace = "com.ujizin.leafy.repository"
}

dependencies {
    implementation(projects.domain)
    implementation(libs.coroutines)
}
