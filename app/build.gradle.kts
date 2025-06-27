plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.firebase-perf")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.devtools.ksp")
    kotlin("kapt")
    id("org.openapi.generator") version "7.6.0"
}

@Suppress("UnstableApiUsage")
android {
    namespace = "dev.aurakai.auraframefx"
    compileSdk = 36 // Addressing AAR metadata feedback

    defaultConfig {
        applicationId = "dev.aurakai.auraframefx"
        minSdk = 31 // As per compatibility plan (LSPosed)
        targetSdk = 36 // Addressing AAR metadata feedback
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        
        ndk {
            abiFilters += listOf("arm64-v8a", "armeabi-v7a")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11" // For Kotlin 2.1.21
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/*.kotlin_module"
        }
    }
    
    // Configure native builds
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = libs.versions.cmake.get()
        }
    }
    
    ndkVersion = libs.versions.ndk.get()
    
    sourceSets {
        getByName("main") {
            java.srcDirs("build/generated/source/openapi/src/main/java")
        }
    }
}

// Generate TypeScript client
tasks.register("generateTypeScriptClient", org.openapitools.generator.gradle.plugin.tasks.GenerateTask::class) {
    generatorName.set("typescript-fetch")
    inputSpec.set("$projectDir/api-spec/aura-framefx-api.yaml")
    outputDir.set("${layout.buildDirectory.get().asFile}/generated/typescript")

    configOptions.set(mapOf(
        "npmName" to "@auraframefx/api-client",
        "npmVersion" to "1.0.0",
        "supportsES6" to "true",
        "withInterfaces" to "true",
        "typescriptThreePlus" to "true"
    ))
}

// Generate Java client  
tasks.register("generateJavaClient", org.openapitools.generator.gradle.plugin.tasks.GenerateTask::class) {
    generatorName.set("java")
    inputSpec.set("$projectDir/api-spec/aura-framefx-api.yaml")
    outputDir.set("${layout.buildDirectory.get().asFile}/generated/java")

    configOptions.set(mapOf(
        "library" to "retrofit2",
        "serializationLibrary" to "gson",
        "dateLibrary" to "java8",
        "java8" to "true",
        "useRxJava2" to "false"
    ))
    
    apiPackage.set("dev.aurakai.auraframefx.java.api")
    modelPackage.set("dev.aurakai.auraframefx.java.model")
    invokerPackage.set("dev.aurakai.auraframefx.java.client")
}

// OpenAPI Generator: Generate Kotlin client from OpenAPI spec
tasks.named<org.openapitools.generator.gradle.plugin.tasks.GenerateTask>("openApiGenerate") {
    generatorName.set("kotlin")
    inputSpec.set("$projectDir/api-spec/aura-framefx-api.yaml")
    outputDir.set("${layout.buildDirectory.get().asFile}/generated/kotlin")
    apiPackage.set("dev.aurakai.auraframefx.api")
    modelPackage.set("dev.aurakai.auraframefx.api.model")
    invokerPackage.set("dev.aurakai.auraframefx.api.invoker")
    configOptions.set(mapOf(
        "dateLibrary" to "java8",
        "serializationLibrary" to "kotlinx_serialization"
    ))
}

// Generate Python client (for AI backend)
val generatePythonClient by tasks.registering(org.openapitools.generator.gradle.plugin.tasks.GenerateTask::class) {
    generatorName.set("python")
    inputSpec.set("$projectDir/api-spec/aura-framefx-api.yaml")
    outputDir.set("${layout.buildDirectory.get().asFile}/generated/python")
    configOptions.set(mapOf(
        "packageName" to "auraframefx_api_client"
    ))
}

// Configure tasks to run generation before compilation
tasks.named("preBuild") {
    dependsOn("generateTypeScriptClient", "generateJavaClient", "openApiGenerate", "generatePythonClient")
}

tasks.named("clean") {
    doLast {
        delete("${layout.buildDirectory.get().asFile}/generated")
        delete("$projectDir/src/main/gen")
    }
}

@Suppress("UnstableApiUsage")
dependencies {
    // Compose BOM and dependencies
    implementation(platform(libs.androidx.compose.bom)) // Version "2025.06.00" from toml
    androidTestImplementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.runtime.livedata)
    // Note:androidTestImplementation("androidx.compose.ui:ui-test-junit4") was already changed to libs.androidx.compose.ui.test.junit4

    // Core Android dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.multidex)
    
    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.compose)
    
    // Hilt Dependency Injection
    implementation(libs.hilt.android)
    // kapt(libs.hilt.compiler) // Switching to KSP for Hilt
    add("ksp", libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)
    
    // Room Database
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    add("ksp", libs.androidx.room.compiler)

    // Network - Retrofit & OkHttp
    implementation(libs.squareup.retrofit2.retrofit)
    implementation(libs.squareup.okhttp3.okhttp)
    implementation(libs.squareup.okhttp3.logging.interceptor)
    implementation(libs.jakewharton.retrofit2.kotlinx.serialization.converter)
    
    // Serialization
    implementation(libs.jetbrains.kotlinx.serialization.json)
    implementation(libs.jetbrains.kotlinx.datetime)
    
    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.firebase.crashlytics.ktx)
    implementation(libs.firebase.perf.ktx) // Already using libs alias from previous step for these
    implementation(libs.firebase.auth.ktx)   // Already using libs alias
    implementation(libs.firebase.firestore.ktx) // Already using libs alias
    
    // Security
    implementation(libs.androidx.security.crypto.ktx)
    
    // WorkManager
    implementation(libs.androidx.work.runtime.ktx)
    
    // DataStore
    implementation(libs.androidx.datastore.preferences)
    
    // Image Loading
    implementation(libs.coil.compose)
    
    // Permissions
    implementation(libs.google.accompanist.permissions)
    
    // System UI Controller
    implementation(libs.google.accompanist.systemuicontroller)
    
    // Generated OpenAPI clients / Other JSON libs
    implementation(libs.squareup.moshi.kotlin)
    implementation(libs.squareup.moshi.adapters)
    implementation(libs.google.code.gson)
    
    // LSPosed/Xposed (for root features)
    compileOnly(files("${rootProject.projectDir}/Libs/api-82.jar")) // This remains as is

    // Testing dependencies
    testImplementation(libs.test.junit)
    testImplementation(libs.test.kotlinx.coroutines)
    testImplementation(libs.test.androidx.arch.core)
    testImplementation(libs.test.mockk)
    
    androidTestImplementation(libs.androidTest.androidx.test.ext.junit)
    androidTestImplementation(libs.androidTest.espresso.core)
    // androidTestImplementation(libs.androidx.compose.ui.test.junit4) // This is already handled above with compose.bom
    
    debugImplementation(libs.androidx.compose.ui.tooling) // Already handled by compose.bom
    debugImplementation(libs.androidx.compose.ui.test.manifest) // Already handled by compose.bom
}
