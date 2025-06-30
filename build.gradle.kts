// Top-level build file where you can add configuration options common to all sub-projects/modules.
// Plugin versions are managed in settings.gradle.kts
plugins {
    // All plugin versions are managed in settings.gradle.kts
    id("com.android.application") apply false
    id("org.jetbrains.kotlin.android") apply false
    alias(libs.plugins.hilt) apply false
    id("com.google.gms.google-services") apply false
    id("com.google.firebase.crashlytics") apply false
    id("com.google.firebase.firebase-perf") apply false
    id("org.jetbrains.kotlin.plugin.serialization") apply false
    id("org.jetbrains.kotlin.plugin.compose") apply false
    id("com.google.devtools.ksp") version "2.2.0-2.0.2" apply false
    id("org.openapi.generator") apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
    // Avoid capturing project reference in configuration phase
    val buildDir = rootProject.layout.buildDirectory.get()
    doFirst {
        delete(buildDir)
    }
}

// All repositories are configured in settings.gradle.kts