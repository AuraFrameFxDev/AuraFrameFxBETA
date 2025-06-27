pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        // maven { url = uri("https://plugins.gradle.org/m2/") } // This was for testing, gradlePluginPortal() is preferred
    }
    // @Suppress("UnstableApiUsage") // No longer needed as libs is not used here
    plugins {
        id("com.android.application") version "8.11.0"
        id("org.jetbrains.kotlin.android") version "2.1.21"
        id("com.google.dagger.hilt.android") version "2.56.2"
        id("com.google.devtools.ksp") version "2.1.21-2.0.2"
        id("org.jetbrains.kotlin.plugin.serialization") version "2.1.21"
        id("org.openapitools.generator") version "7.6.0"
        id("com.google.gms.google-services") version "4.4.2"
        id("com.google.firebase.crashlytics") version "2.9.9"
        id("com.google.firebase.firebase-perf") version "1.4.2"
        id("org.jetbrains.kotlin.plugin.compose") version "2.1.21"
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