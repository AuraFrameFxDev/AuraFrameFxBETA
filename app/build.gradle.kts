plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.firebase-perf")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.openapi.generator") version "7.14.0"
    id("com.google.dagger.hilt.android")
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
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlin {
        jvmToolchain(21)
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    sourceSets.getByName("main") {
        java.srcDir("${layout.buildDirectory.get().asFile}/generated/kotlin/src/main/java")
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_21.toString()
    }
    ndkVersion = "26.2.11394342"
}

// ----- OpenAPI & Codegen tasks -----
tasks.register("generateTypeScriptClient", org.openapitools.generator.gradle.plugin.tasks.GenerateTask::class) {
    generatorName.set("typescript-fetch")
    inputSpec.set("$projectDir/api-spec/aura-framefx-api.yaml")
    outputDir.set("${layout.buildDirectory.get().asFile}/generated/typescript")
    configOptions.set(
        mapOf(
            "npmName" to "@auraframefx/api-client",
            "npmVersion" to "1.0.0",
            "supportsES6" to "true",
            "withInterfaces" to "true",
            "typescriptThreePlus" to "true"
        )
    )
}

tasks.register("generateJavaClient", org.openapitools.generator.gradle.plugin.tasks.GenerateTask::class) {
    generatorName.set("java")
    inputSpec.set("$projectDir/api-spec/aura-framefx-api.yaml")
    outputDir.set("${layout.buildDirectory.get().asFile}/generated/java")
    configOptions.set(
        mapOf(
            "library" to "retrofit2",
            "serializationLibrary" to "gson",
            "dateLibrary" to "java8",
            "java8" to "true",
            "useRxJava2" to "false"
        )
    )
    apiPackage.set("dev.aurakai.auraframefx.java.api")
    modelPackage.set("dev.aurakai.auraframefx.java.model")
    invokerPackage.set("dev.aurakai.auraframefx.java.client")
}

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
            "serializationLibrary" to "kotlinx_serialization"
        )
    )
    globalProperties.set(
        mapOf(
            "library" to "kotlin",
            "serializationLibrary" to "kotlinx_serialization"
        )
    )
}

tasks.register("generatePythonClient", org.openapitools.generator.gradle.plugin.tasks.GenerateTask::class) {
    generatorName.set("python")
    inputSpec.set("$projectDir/api-spec/aura-framefx-api.yaml")
    outputDir.set("${layout.buildDirectory.get().asFile}/generated/python")
    configOptions.set(
        mapOf("packageName" to "auraframefx_api_client")
    )
}

// Ensure codegen runs before build
tasks.named("preBuild") {
    dependsOn(
        "generateTypeScriptClient",
        "generateJavaClient",
        "openApiGenerate",
        "generatePythonClient"
    )
}

tasks.named("clean") {
    doLast {
        delete("${layout.buildDirectory.get().asFile}/generated")
        delete("$projectDir/src/main/gen")
    }
}

dependencies {
    // Compose BOM and dependencies
    implementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.compose.animation.tooling)

    // Core Android dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.multidex)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Hilt Dependency Injection
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // Room Database
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Network - Retrofit & OkHttp
    implementation(libs.squareup.retrofit2.retrofit)
    implementation(libs.squareup.okhttp3.okhttp)
    implementation(libs.squareup.okhttp3.logging.interceptor)
    implementation(libs.jakewharton.retrofit2.kotlinx.serialization.converter)

    // Serialization
    implementation(libs.jetbrains.kotlinx.serialization.json)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.firebase.crashlytics.ktx)
    implementation(libs.firebase.perf.ktx)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.firestore.ktx)

    // Security Crypto KTX
    implementation(libs.androidx.security.crypto.ktx)

    // WorkManager
    implementation(libs.androidx.work.runtime.ktx)

    // DataStore
    implementation(libs.androidx.datastore.preferences)

    // Image Loading
    implementation(libs.coil.compose)

    // Permissions
    implementation(libs.google.accompanist.permissions)

    // Guava for ListenableFuture
    implementation(libs.guava)

    // AndroidX Window Extensions for Split rules
    implementation(libs.androidx.window.extensions)

    // Testing dependencies
    testImplementation(libs.test.junit)
    testImplementation(libs.test.kotlinx.coroutines)
    androidTestImplementation(libs.androidTest.androidx.test.ext.junit)
    androidTestImplementation(libs.androidTest.espresso.core)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
