pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        // maven { url = uri("https://plugins.gradle.org/m2/") } // This was for testing, gradlePluginPortal() is preferred
    }
    
    plugins {
        id("com.android.application") version "8.4.1" // Updated AGP
        id("org.jetbrains.kotlin.android") version "2.0.0" // Updated Kotlin
        id("com.google.dagger.hilt.android") version "2.51.1" // Updated Hilt to stable
        id("com.google.devtools.ksp") version "2.0.0-1.0.21" // Updated KSP for Kotlin 2.0.0
        id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0" // Updated Kotlin Serialization
        id("org.openapitools.generator") version "7.6.0" // Unchanged
        id("com.google.gms.google-services") version "4.4.2" // Unchanged
        id("com.google.firebase.crashlytics") version "2.9.9" // Unchanged
        id("com.google.firebase.firebase-perf") version "1.4.2" // Unchanged
        id("org.jetbrains.kotlin.plugin.compose") version "1.5.12" // Updated for Kotlin 2.0.0
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // Add JitPack here
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "AuraFrameFX"

include(":app")
