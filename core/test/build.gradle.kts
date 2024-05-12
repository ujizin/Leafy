plugins {
    alias(libs.plugins.android.library)
}

apply(from = "$rootDir/config-android.gradle")

android {
    namespace = "com.ujizin.leafy.test"
}

dependencies {
    implementation(libs.coroutines.test)
    implementation(libs.bundles.test)
}
