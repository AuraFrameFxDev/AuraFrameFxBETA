pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        // maven { url = uri("https://androidx.dev/storage/compose-compiler/repository/") } // For K2 Compose Compiler -- REMOVED
        // maven { url = uri("https://plugins.gradle.org/m2/") } // This was for testing, gradlePluginPortal() is preferred
    }
    
    plugins {
        id("com.android.application") version "8.4.1"
        id("org.jetbrains.kotlin.android") version "2.0.0"
        id("com.google.dagger.hilt.android") version "2.56.2"
        id("com.google.devtools.ksp") version "2.0.0-1.0.21"
        id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0"
        id("org.openapitools.generator") version "7.6.0"
        id("com.google.gms.google-services") version "4.4.2"
        id("com.google.firebase.crashlytics") version "2.9.9"
        id("com.google.firebase.firebase-perf") version "1.4.2"
        // org.jetbrains.kotlin.plugin.compose is intentionally omitted, relying on Kotlin 2.0 + buildFeatures { compose = true }

    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "AuraFrameFX"

include:("app")
