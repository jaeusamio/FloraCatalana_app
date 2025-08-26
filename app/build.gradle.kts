import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp.compiler)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.floracatalana.floracatalana"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.floracatalana.floracatalana"
        minSdk = 24
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
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_1_8
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    applicationVariants.forEach { variant ->
        variant.sourceSets.forEach {
            it.javaDirectories += files("build/generated/ksp/${variant.name}/kotlin")
        }
    }

    ksp {
        arg("KOIN_DEFAULT_MODULE","true")
    }
}

dependencies {

    implementation (libs.androidx.core.ktx)
    implementation (libs.androidx.lifecycle.runtime.ktx)
    implementation (libs.androidx.activity.compose)

    // Compose
    implementation (platform(libs.androidx.compose.bom))
    implementation (libs.androidx.ui)
    implementation (libs.androidx.ui.graphics)
    implementation (libs.androidx.ui.tooling.preview)
    implementation (libs.androidx.material3)
    implementation (libs.androidx.runtime.livedata)
    implementation (libs.androidx.material.icons.core)
    implementation (libs.androidx.material.icons.extended)

    // Compose testing
    testImplementation (libs.junit)
    androidTestImplementation (libs.androidx.junit)
    androidTestImplementation (libs.androidx.espresso.core)
    androidTestImplementation (libs.androidx.ui.test.junit4)
    debugImplementation (libs.androidx.ui.tooling)
    debugImplementation (libs.androidx.ui.test.manifest)

    // Compose Viewmodel
    implementation (libs.androidx.lifecycle.viewmodel.compose)

    // Compose Navigation
    implementation  (libs.androidx.navigation.compose)

    // kotlinx serialization
    implementation (libs.kotlinx.serialization.json)

    // Kotlin reflect
    implementation (libs.kotlin.reflect)

    // Coil
    implementation (libs.coil.compose)

    // Koin
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.compose)
    implementation (libs.koin.android)
    implementation(libs.koin.core.coroutines)
    implementation(libs.koin.compose.viewmodel)
    implementation(libs.koin.compose.viewmodel.navigation)

    implementation(libs.koin.annotations)
    ksp(libs.koin.ksp.compiler)

    // Ktor
    implementation (libs.ktor.client.core)
    implementation (libs.ktor.client.negotiation)
    implementation (libs.ktor.client.okhttp)
    implementation (libs.ktor.serialization)

    // Ksoup
    implementation(libs.ksoup)
}