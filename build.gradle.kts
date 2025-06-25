// Top-level build file.
// Plugin versions are managed in settings.gradle.kts within the pluginManagement block,
// which refers to gradle/libs.versions.toml.

@Suppress("UnstableApiUsage") // For using libs from version catalog in plugins block
plugins {
    id("com.android.application") apply false
    id("org.jetbrains.kotlin.android") apply false
    id("com.google.devtools.ksp") apply false
    id("com.google.dagger.hilt.android") apply false
    // Other plugins like kotlin-serialization, compose, google-services, firebase-*, openapi-generator
    // are applied directly in the app/build.gradle.kts with their versions.
}

allprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions.jvmTarget = "21"
    }
}

