plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "dev.aurakai.auraframefx"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.aurakai.auraframefx"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.lifecycle.runtime.ktx)

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

repositories {
    google()
    mavenCentral()
    // Add other repositories only if you have special needs
}

dependencies {
    // Kotlin standard library (should be included by default)
    implementation "org.jetbrains.kotlin:kotlin-stdlib:1.9.22"

    // OkHttp (network client)
    implementation "com.squareup.okhttp3:okhttp:4.11.0"

    // Kotlinx Serialization
    implementation "org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0"

    // Jetpack Compose (UI)
    implementation "androidx.compose.ui:ui:1.6.2"
    implementation "androidx.compose.material:material:1.6.2"
    implementation "androidx.compose.ui:ui-tooling-preview:1.6.2"
    debugImplementation "androidx.compose.ui:ui-tooling:1.6.2"
    implementation "androidx.activity:activity-compose:1.9.0"

    // Dependency Injection (example: Koin, switch to Dagger/Hilt if you use those instead)
    implementation "io.insert-koin:koin-android:3.5.0"
    // OR, for Hilt:
    // implementation "com.google.dagger:hilt-android:2.51"
    // kapt "com.google.dagger:hilt-android-compiler:2.51"

    // AndroidX Security (for MasterKey)
    implementation "androidx.security:security-crypto:1.1.0-alpha06"

    // Xposed (if you are building an Xposed module)
    // implementation "de.robv.android.xposed:api:82" // Check for the correct version and repo

    // For Compose Material Icons (optional)
    implementation "androidx.compose.material:material-icons-extended:1.6.2"

    // Other dependencies as required by your code...
}

    // Compose (if needed)
    implementation(platform(libs.compose.bom))
    // Add other dependencies as needed
}
