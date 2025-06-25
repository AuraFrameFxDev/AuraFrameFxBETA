pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        // maven { url = uri("https://plugins.gradle.org/m2/") } // This was for testing, gradlePluginPortal() is preferred
    }
    @Suppress("UnstableApiUsage")
    plugins {
        id("com.android.application") version libs.versions.agp.get()
        id("org.jetbrains.kotlin.android") version libs.versions.kotlin.get()
        id("com.google.dagger.hilt.android") version libs.versions.hilt.get()
        id("com.google.devtools.ksp") version libs.versions.ksp.get()
        id("org.jetbrains.kotlin.plugin.serialization") version libs.versions.kotlin.get()
        id("org.openapitools.generator") version "7.6.0" // Version from plan, not in libs.versions for plugins block
        id("com.google.gms.google-services") version libs.versions.googleServicesPlugin.get()
        id("com.google.firebase.crashlytics") version "2.9.9" // Version from plan/original, not in libs.versions for plugins block
        id("com.google.firebase.firebase-perf") version "1.4.2" // Version from plan/original, not in libs.versions for plugins block
        id("org.jetbrains.kotlin.plugin.compose") version libs.versions.kotlin.get()
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