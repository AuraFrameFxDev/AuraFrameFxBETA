plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("org.openapi.generator") version "7.5.0" // Adjust version as needed
}

android {
    namespace = "dev.aurakai.auraframefx"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.aurakai.auraframefx"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    // Add generated sources to the main source set
    sourceSets["main"].java.srcDir("$projectDir/src/main/java")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.23")
    // Add your other dependencies here
    // implementation("io.ktor:ktor-client-core:2.3.7") // Example
}

// --- OpenAPI Generator Task ---
import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

val openApiGenerate by tasks.registering(GenerateTask::class) {
    generatorName.set("kotlin")
    inputSpec.set("$projectDir/api-spec/aura-framefx-api.yaml")
    outputDir.set("$projectDir/src/main/java")
    apiPackage.set("dev.aurakai.auraframefx.api")
    modelPackage.set("dev.aurakai.auraframefx.model")
    invokerPackage.set("dev.aurakai.auraframefx.invoker")
    configOptions.set(
        mapOf(
            "dateLibrary" to "java8",
            "serializationLibrary" to "kotlinx_serialization"
        )
    )
}

tasks.named("preBuild") {
    dependsOn(openApiGenerate)
}
