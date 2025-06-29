pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        // ...
    }
    plugins {
        id("com.android.application") version "9.0.0" apply false
        // other plugins...
    }
}

rootProject.name = "AuraFrameFX"
include(":app")