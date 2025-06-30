// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.agp)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.openapi.generator)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "dev.aurakai.auraframefx"
    compileSdk = 36

    defaultConfig {
        applicationId = "dev.aurakai.auraframefx"
        minSdk = 33
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kotlin.compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
    }
}

// Configure OpenAPI generation
tasks.named<org.openapitools.generator.gradle.plugin.tasks.GenerateTask>("openApiGenerate") {
    generatorName.set("kotlin")
    inputSpec.set("$projectDir/api-spec/aura-framefx-api.yaml")
    outputDir.set("${layout.buildDirectory.get().asFile}/generated/kotlin")
    apiPackage.set("dev.aurakai.auraframefx.api")
    modelPackage.set("dev.aurakai.auraframefx.api.model")
    invokerPackage.set("dev.aurakai.auraframefx.api.invoker")
    configOptions.set(
        mapOf(
            "dateLibrary" to "java8",
            "serializationLibrary" to "kotlinx_serialization",
            "library" to "jvm-retrofit2"
        )
    )
}

// Ensure KSP runs AFTER the OpenAPI generator task
tasks.withType<com.google.devtools.ksp.gradle.KspTask>().configureEach {
    dependsOn(tasks.named("openApiGenerate"))
}

// Add generated sources to the main source set
afterEvaluate {
    android.sourceSets.getByName("main").java.srcDir("${layout.buildDirectory.get()}/generated/kotlin/src/main/kotlin")
}

// Clean up generated files on clean
tasks.named<Delete>("clean") {
    delete(layout.buildDirectory.dir("generated"))
}

dependencies {
    // Core & Lifecycle
    debugImplementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.datetime)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.lifecycle.viewmodel.compose)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Hilt & DI
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.compose)

    // Room (Persistence)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Network (Retrofit + Kotlinx Serialization)
    implementation(libs.squareup.retrofit2.retrofit)
    implementation(libs.squareup.okhttp3.okhttp)
    implementation(libs.squareup.okhttp3.logging.interceptor)
    implementation(libs.jakewharton.retrofit2.kotlinx.serialization.converter)

    // Serialization
    implementation(libs.jetbrains.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.firebase.crashlytics.ktx)
    implementation(libs.firebase.perf.ktx)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.firestore.ktx)

    // Other AndroidX
    implementation(libs.androidx.security.crypto.ktx)
    implementation(libs.androidx.datastore.preferences) // Added this
    implementation(libs.androidx.work.runtime.ktx) // Added this

    // UI Libraries
    implementation(libs.coil.compose)
    implementation(libs.google.accompanist.permissions)

    // Utilities
    implementation(libs.guava)

    // Testing
    testImplementation(libs.test.junit)
    testImplementation(libs.test.kotlinx.coroutines)
    testImplementation(libs.test.mockk)
    androidTestImplementation(libs.androidTest.androidx.test.ext.junit)
    androidTestImplementation(libs.androidTest.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // OpenAPI Generated Client
    implementation(libs.squareup.retrofit2.retrofit)
    implementation(libs.converter.scalars)
    implementation(libs.converter.gson.v300)
    implementation(libs.squareup.okhttp3.logging.interceptor)
    implementation(libs.squareup.okhttp3.okhttp)
}
