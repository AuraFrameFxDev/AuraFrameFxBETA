buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.10.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.1.21")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.51.1")
        classpath("com.google.gms:google-services:4.4.2")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.9")
        classpath("com.google.firebase:perf-plugin:1.4.2")
        // Removed KSP and OpenAPI Generator classpaths to avoid plugin block conflicts
    }
}

plugins {
    id("com.google.devtools.ksp") version "2.1.21-2.0.2" apply false
}

// Removed allprojects.repositories block to avoid repository conflict with settings.gradle.kts
