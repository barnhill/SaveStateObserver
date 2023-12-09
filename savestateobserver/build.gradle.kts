@file:Suppress("UnstableApiUsage")

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.dokka)
    alias(libs.plugins.toml.version.checker)
}

version = "2.12.0"
group = "com.pnuema.android"

android {
    base.archivesName.set("savestateobserver")
    namespace = "com.pnuema.android.savestateobserver"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    buildTypes {
        named("debug") {
            isMinifyEnabled = false
        }
        named("release") {
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

    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    implementation (libs.androidx.appcompat)
    implementation (libs.androidx.startup)
    testImplementation (libs.junit)
}

val dokkaOutputDir = "${layout.buildDirectory}/dokka"
tasks {
    val sourcesJar by creating(Jar::class) {
        archiveClassifier.set("sources")
        from(android.sourceSets.getByName("main").java.srcDirs)
    }

    val javadocJar by creating(Jar::class) {
        dependsOn.add(dokkaJavadoc)
        archiveClassifier.set("javadoc")
        from(android.sourceSets.getByName("main").java.srcDirs)
        from(dokkaOutputDir)
    }

    artifacts {
        archives(sourcesJar)
        archives(javadocJar)
    }

    dokkaHtml {
        outputDirectory.set(file(dokkaOutputDir))
        dokkaSourceSets {
            named("main") {
                noAndroidSdkLink.set(false)
            }
        }
    }

    build {
        dependsOn(dokkaJavadoc)
    }
}