@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.dokka)
    alias(libs.plugins.toml.version.checker)
}

android {
    namespace = "com.pnuema.android.savestateobserver.app"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.pnuema.android.savestateobserver.app"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            setProguardFiles(listOf(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro"))
        }
        debug {
            isMinifyEnabled = false
            setProguardFiles(listOf(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro"))
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation (project(":savestateobserver"))
    implementation (libs.android.material)
    implementation (libs.androidx.fragment.ktx)
    implementation (libs.androidx.appcompat)
    implementation (libs.androidx.startup)
}
