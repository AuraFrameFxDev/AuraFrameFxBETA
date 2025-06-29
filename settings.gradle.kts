
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://androidx.dev/storage/compose-compiler/repository/") }
    }
}

plugins {
    id("com.android.application") version ("8.11.0") apply false
    id("org.jetbrains.kotlin.android") version ("2.1.21") apply false
    id("com.google.devtools.ksp") version ("2.1.21-2.0.2") apply false
    id("org.gradle.toolchains.foojay-resolver-convention") version ("1.0.0") apply false
    id("com.google.dagger.hilt.android") version ("2.56.2") apply false

}

rootProject.name = "AuraFrameFX"

include(":app")


dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://androidx.dev/storage/compose-compiler/repository/") }
    }
}
