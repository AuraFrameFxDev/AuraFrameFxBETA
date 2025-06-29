pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id("com.android.application") version "8.4.0" apply false
        id("org.jetbrains.kotlin.android") version "2.1.21" apply false
        id("com.google.devtools.ksp") version "2.1.21-2.0.2" apply false
        id("org.jetbrains.kotlin.plugin.serialization") version "2.1.21" apply false
        id("org.jetbrains.kotlin.plugin.compose") version "2.2.0" apply false
        id("com.google.dagger.hilt.android") version "2.56.2" apply false
        id("com.google.gms.google-services") version "4.4.3" apply false
        id("com.google.firebase.crashlytics") version "3.0.4" apply false
        id("com.google.firebase.firebase-perf") version "1.4.2" apply false
        id("org.openapi.generator") version "7.14.0" apply false
        id("org.jetbrains.kotlin.jvm") version "2.1.21" apply false
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AuraFrameFX"
include(":app", ":openapi-codegen")
