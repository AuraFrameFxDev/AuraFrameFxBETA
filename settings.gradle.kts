pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id("com.android.application") version "8.4.0" apply false
        id("org.jetbrains.kotlin.android") version "2.2.0" apply false
        id("com.google.devtools.ksp") version "2.2.0-2.0.2" apply false
        id("com.google.dagger.hilt.android") version "2.56.2" apply false
        id("com.google.gms.google-services") version "4.4.3" apply false
        id("com.google.firebase.crashlytics") version "3.0.4" apply false
        id("com.google.firebase.firebase-perf") version "1.4.2" apply false
        id("org.jetbrains.kotlin.plugin.serialization") version "2.2.0" apply false
        id("org.jetbrains.kotlin.plugin.compose") version "2.2.0" apply false
        id("org.openapi.generator") version "7.6.0" apply false
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from(files("gradle/libs.versions.toml"))
        }
    }
}
rootProject.name = "AuraFrameFxBETA"
include(":app")

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}    


