pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        // maven { url = uri("https://plugins.gradle.org/m2/") } // This was for testing, gradlePluginPortal() is preferred
    }
    
    // Versions for these plugins will be sourced from libs.versions.toml via aliases if defined there,
    // or directly if needed. The versions here are the source of truth for the buildscript.
    // The versions in libs.versions.toml are for dependencies unless explicitly used for plugins.
    // Let's use versions consistent with your app's needs and common practices.
    // KSP version should align with Kotlin.
    // Compose plugin version should align with composeOptions.kotlinCompilerExtensionVersion in app/build.gradle.kts.
    plugins {
        id("com.android.application") version "8.1.4" // Aligning with libs.versions.toml agp
        id("org.jetbrains.kotlin.android") version "1.9.23" // Version from libs.versions.toml
        id("com.google.dagger.hilt.android") version "2.51" // Version from libs.versions.toml
        id("com.google.devtools.ksp") version "1.9.23-1.0.19" // Version from libs.versions.toml
        id("org.jetbrains.kotlin.plugin.serialization") version "1.9.23" // Version from libs.versions.toml
        id("org.openapitools.generator") version "7.6.0" // Keep existing
        id("com.google.gms.google-services") version "4.4.2" // Keep existing
        id("com.google.firebase.crashlytics") version "2.9.9" // Keep existing
        id("com.google.firebase.firebase-perf") version "1.4.2" // Keep existing
        // This is the Jetpack Compose compiler plugin. Its version should match what's expected by
        // composeOptions.kotlinCompilerExtensionVersion in app/build.gradle.kts (e.g., "1.5.11")
        id("org.jetbrains.kotlin.plugin.compose") version "1.5.11" // This is correct for Kotlin 1.9.23
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
