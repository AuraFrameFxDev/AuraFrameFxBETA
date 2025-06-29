plugins {
    id("com.android.application")version("8.11.0")
    id("org.jetbrains.kotlin.android")version("2.1.21")
    id("com.google.devtools.ksp")version("2.1.21-2.0.2")
    id("org.jetbrains.kotlin.plugin.serialization")version("2.1.21"
    id("com.google.gms.google-services")version("4.3.15")
    id("com.google.firebase.crashlytics")version("2.9.8")
    id("com.google.firebase.firebase-perf")version("1.4.1")
    id("org.jetbrains.kotlin.plugin.compose")version("2.1.0")
    id("org.openapi.generator")version("7.14.0")
    id("com.google.dagger.hilt.android")version("2.56.2")

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
        signingConfig = signingConfigs.getByName("debug")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlin {
        jvmToolchain(21)
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
    ndkVersion = "26.2.11394342"
}

tasks(register("generateTypeScriptClient", org.openapitools.generator.gradle.plugin.tasks.GenerateTask::class) {
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

tasks(register("generateJavaClient", org.openapitools.generator.gradle.plugin.tasks.GenerateTask::class) {
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

tasks(named<org.openapitools.generator.gradle.plugin.tasks.GenerateTask>("openApiGenerate") {
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

    globalProperties.set(mapOf(
        "library" to "kotlin",
        "serializationLibrary" to "kotlinx_serialization"
    ))
}){
    dependsOn("generateTypeScriptClient", "generateJavaClient")
val generatePythonClient by tasks.registering(org.openapitools.generator.gradle.plugin.tasks.GenerateTask::class) {
    generatorName.set("python")
    inputSpec.set("$projectDir/api-spec/aura-framefx-api.yaml")
    outputDir.set("${layout.buildDirectory.get().asFile}/generated/python")
    configOptions.set(mapOf(
        "packageName" to "auraframefx_api_client"
    ))
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.graphics)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    implementation(libs.squareup.retrofit2.retrofit)
    implementation(libs.squareup.okhttp3.okhttp)
    implementation(libs.squareup.okhttp3.logging.interceptor)
    implementation(libs.jakewharton.retrofit2.kotlinx.serialization.converter)
    implementation(libs.jetbrains.kotlinx.serialization.json)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.firebase.crashlytics.ktx)
    implementation(libs.firebase.perf.ktx)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.androidx.security.crypto.ktx)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.coil.compose)
    implementation(libs.google.accompanist.permissions)
    testImplementation(libs.test.junit)
    testImplementation(libs.test.kotlinx.coroutines)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidTest.androidx.test.ext.junit)
    androidTestImplementation(libs.androidTest.espresso.core)
{
    exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-coroutines-debug")}
    androidTestImplementation(libs.androidTest.androidx.test.rules)
    androidTestImplementation(libs.androidTest.androidx.test.runner)
    androidTestImplementation(libs.androidTest.androidx.test.core))
