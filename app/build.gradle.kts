import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.dokari4.githubapicompose"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dokari4.githubapicompose"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        //Add API_TOKEN to local.properties
        val keyStoreFile = project.rootProject.file("local.properties")
        val properties = Properties()
        properties.load(keyStoreFile.inputStream())

        val apiKey = properties.getProperty("API_TOKEN") ?: ""

        buildConfigField(
            type = "String",
            name =  "API_TOKEN",
            value = apiKey
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    // Lifecycle Bundles
    implementation (libs.bundles.lifecycle)
    // Compose Bundle
    implementation(libs.bundles.compose)
    implementation(platform(libs.androidx.compose.bom))
    // Navigation Bundles
    implementation(libs.bundles.navigation)
    // Network Bundles
    implementation(libs.bundles.network)
    //Kotlin Serializable
    implementation(libs.kotlinx.serialization.json)
    // Google Fonts
    implementation(libs.androidx.ui.text.google.fonts)
    //Hilt
    implementation (libs.hilt.android)
    ksp (libs.hilt.android.compiler)
    implementation (libs.androidx.hilt.navigation.compose)
    // DataStore
    implementation(libs.androidx.datastore.preferences)
    //Coil Bundles
    implementation(libs.bundles.coil)
    //Lottie
    implementation(libs.lottie.compose)
    // Testing Libs
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}