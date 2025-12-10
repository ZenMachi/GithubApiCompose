import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
    // Google Services
    id("com.google.gms.google-services")
    // Add the Crashlytics Gradle plugin
    id("com.google.firebase.crashlytics")
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
        val certHashBase64 = properties.getProperty("CERT_HASH_BASE64") ?: ""
        val pkgName = properties.getProperty("PKG_NAME") ?: ""
        val email = properties.getProperty("EMAIL") ?: ""
        buildConfigField(
            type = "String",
            name =  "API_TOKEN",
            value = apiKey
        )
        buildConfigField(
            type = "String",
            name =  "CERT_HASH_BASE64",
            value = certHashBase64
        )
        buildConfigField(
            type = "String",
            name =  "PKG_NAME",
            value = pkgName
        )
        buildConfigField(
            type = "String",
            name =  "EMAIL",
            value = email
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
    // Kotlin Serializable
    implementation(libs.kotlinx.serialization.json)
    // Google Fonts
    implementation(libs.androidx.ui.text.google.fonts)
    // Hilt
    implementation (libs.hilt.android)
    ksp (libs.hilt.android.compiler)
    implementation (libs.androidx.hilt.navigation.compose)
    // DataStore
    implementation(libs.androidx.datastore.preferences)
    // Coil Bundles
    implementation(libs.bundles.coil)
    // Lottie
    implementation(libs.lottie.compose)
    // Room
    implementation (libs.room.runtime)
    ksp (libs.room.compiler)
    implementation (libs.room.ktx)
    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:34.1.0"))
    // Add the dependencies for the Crashlytics NDK and Analytics libraries
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-crashlytics-ndk")
    implementation("com.google.firebase:firebase-analytics")
    // freeRASP SDK
    implementation("com.aheaditec.talsec.security:TalsecSecurity-Community:16.0.1")
    // Testing Libs
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}