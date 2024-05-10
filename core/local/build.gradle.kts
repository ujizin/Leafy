plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.ksp)
}

apply(from = "../../config-android.gradle")

android {
    namespace = "com.ujizin.leafy.data.local"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(libs.bundles.datastore)

    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    implementation(libs.kotlinx.serialization.json)

    androidTestImplementation(projects.core.test)
    androidTestImplementation(libs.bundles.test)
    androidTestImplementation(libs.coroutines.test)

    implementation(projects.core.repository)
}
