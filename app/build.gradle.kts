plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.example.jubookhub"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.jubookhub"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // AndroidX dependencies
    implementation(libs.androidx.core.ktx)                           // Core KTX for AndroidX
    implementation(libs.androidx.lifecycle.runtime.ktx)              // Lifecycle KTX
    implementation(libs.androidx.activity.compose)                   // Compose for Activity

    // Jetpack Compose dependencies
    implementation(platform(libs.androidx.compose.bom))              // Compose BOM for version management
    implementation(libs.androidx.compose.ui)                         // Compose UI
    implementation(libs.androidx.compose.material3)                   // Material3 for Compose UI
    implementation(libs.androidx.compose.material.icons.extended)    // Extended material icons for Compose

    // Firebase dependencies (using BOM for version management)
    implementation(platform(libs.firebase.bom))                      // Firebase BOM
    implementation(libs.firebase.auth)                               // Firebase Authentication
    implementation(libs.firebase.firestore)                          // Firebase Firestore
    implementation(libs.firebase.storage)                            // Firebase Storage
    implementation(libs.firebase.messaging)                          // Firebase Cloud Messaging
    implementation(libs.firebase.analytics)                          // Firebase Analytics

    // Image loading library - Coil for Compose
    implementation("io.coil-kt:coil-compose:2.7.0")
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.compose.animation.core)                   // Coil for Compose (image loading)

    // Testing dependencies
    testImplementation(libs.junit)                                   // JUnit for unit testing
    androidTestImplementation(libs.androidx.junit)                   // JUnit for Android UI testing
    androidTestImplementation(libs.androidx.espresso.core)           // Espresso for UI testing
    androidTestImplementation(platform(libs.androidx.compose.bom))  // Compose BOM for UI testing
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)  // Compose UI testing (JUnit4)
    debugImplementation(libs.androidx.compose.ui.tooling)            // Compose UI tooling (for previews, etc.)
    debugImplementation(libs.androidx.compose.ui.test.manifest)      // Compose testing manifest

    // Material Components for legacy UI styling (required for Theme.Material3.DayNight.NoActionBar)
    implementation("com.google.android.material:material:1.12.0")    // Material components for legacy UI styles

    // Navigation Compose (for Compose-based navigation)
    implementation("androidx.navigation:navigation-compose:2.5.0")   // Navigation for Compose

    // Compose Foundation components (basic UI components like Column, Row, etc.)
    implementation("androidx.compose.foundation:foundation:1.1.0")   // Foundation components for Compose
}
