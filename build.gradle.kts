plugins {
    id("com.android.application") apply false
    id("org.jetbrains.kotlin.android") apply false
    id("com.google.devtools.ksp") apply false
    id("com.google.dagger.hilt.android") apply false
    // Other plugins like kotlin-serialization, compose, google-services, firebase-*, openapi-generator
    // are applied directly in the app/build.gradle.kts with their versions.
}
