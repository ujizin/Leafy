plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kover)
    id(libs.plugins.google.services.get().pluginId)
    id(libs.plugins.google.crashlytics.get().pluginId)
}

apply(from = "$rootDir/config-android.gradle")
apply(from = "$rootDir/config-compose.gradle")

android {
    namespace = "com.ujizin.leafy"
    defaultConfig {
        applicationId = "com.ujizin.leafy"
        versionCode = 1
        versionName = "0.1.0"
    }

    signingConfigs {
        create("release") {
            storeFile = file("${rootProject.extra["RELEASE_STORE_FILE"]}")
            storePassword = "${rootProject.extra["RELEASE_STORE_PASSWORD"]}"
            keyAlias = "${rootProject.extra["RELEASE_KEY_ALIAS"]}"
            keyPassword = "${rootProject.extra["RELEASE_KEY_PASSWORD"]}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            resValue("string", "app_name", "Leafy")
            signingConfig = signingConfigs.getByName("release")
        }

        getByName("debug") {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            resValue("string", "app_name", "Leafy Dev")
        }
    }
}

dependencies {
    implementation(libs.androidx.core.splashscreen)

    implementation(projects.features.home)
    implementation(projects.features.search)
    implementation(projects.features.alarm)
    implementation(projects.features.camera)
    implementation(projects.features.publish)
    implementation(projects.features.about)
    implementation(projects.features.preferences)
    implementation(projects.features.tasks)
    implementation(projects.features.plant)

    // TODO add plugins in each module
    kover(projects.features.home)
    kover(projects.features.search)
    kover(projects.features.alarm)
    kover(projects.features.camera)
    kover(projects.features.publish)
    kover(projects.features.about)
    kover(projects.features.preferences)
    kover(projects.features.tasks)
    kover(projects.features.plant)

    implementation(projects.domain)
    implementation(projects.core.local)

    implementation(projects.core.ui)
    implementation(projects.core.themes)
    implementation(projects.core.navigation)

    androidTestImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.androidx.test)
}
