plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.firebase-perf")
    id("org.openapi.generator") version "7.14.0"
}

android {
    namespace = "dev.aurakai.auraframefx"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.aurakai.auraframefx"
        minSdk = 33
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
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

    kotlin {
        jvmToolchain(17)
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    ndkVersion = "26.2.11394342"

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
openApiGenerate {
    generatorName.set("kotlin")
    inputSpec.set("$projectDir/api-spec/aura-framefx-api.yaml") // adjust if needed
    outputDir.set("$buildDir/generated")
    apiPackage.set("dev.aurakai.auraframefx.api")
    modelPackage.set("dev.aurakai.auraframefx.api.model")
    invokerPackage.set("dev.aurakai.auraframefx.api.invoker")
    configOptions.set(
        mapOf(
            "dateLibrary" to "java8",
            "serializationLibrary" to "kotlinx_serialization"
        )
    )
}

// Make sure generated code is included as a source set
android.sourceSets["main"].java.srcDir("$buildDir/generated/src/main/kotlin")

// Ensure code is generated before compile
tasks.named("compileKotlin") {
    dependsOn("openApiGenerate")
}
dependencies {
    // Compose & UI
    implementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.compose.animation.tooling)

    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)

    // DI
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // DB
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Network
    implementation(libs.squareup.retrofit2.retrofit)
    implementation(libs.squareup.okhttp3.okhttp)
    implementation(libs.squareup.okhttp3.logging.interceptor)
    implementation(libs.jakewharton.retrofit2.kotlinx.serialization.converter)

    // Serialization, Datastore, Security, WorkManager
    implementation(libs.jetbrains.kotlinx.serialization.json)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.security.crypto.ktx)
    implementation(libs.androidx.work.runtime.ktx)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.firebase.crashlytics.ktx)
    implementation(libs.firebase.perf.ktx)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.firestore.ktx)

    // Image loading, permissions, utils
    implementation(libs.coil.compose)
    implementation(libs.google.accompanist.permissions)
    implementation(libs.guava)
    implementation(libs.androidx.window.extensions)

    // Testing
    testImplementation(libs.test.junit)
    testImplementation(libs.test.kotlinx.coroutines)
    androidTestImplementation(libs.androidTest.androidx.test.ext.junit)
    androidTestImplementation(libs.androidTest.espresso.core)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
