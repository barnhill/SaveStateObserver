@file:Suppress("UnstableApiUsage")

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.dokka)
    alias(libs.plugins.toml.version.checker)
}

version = "2.14.0"
group = "com.pnuema.android"

android {
    base.archivesName.set("savestateobserver")
    namespace = "com.pnuema.android.savestateobserver"
    compileSdk = 35
    defaultConfig {
        minSdk = 26
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

val dokkaOutputDir = layout.buildDirectory.dir("dokka")
tasks {
    val sourcesJar by registering(Jar::class, fun Jar.() {
        archiveClassifier.set("sources")
        from(android.sourceSets.getByName("main").java.srcDirs)
    })

    val javadocJar by registering(Jar::class, fun Jar.() {
        dependsOn.add(dokkaGenerate)
        archiveClassifier.set("javadoc")
        from(android.sourceSets.getByName("main").java.srcDirs)
        from(dokkaOutputDir)
    })

    artifacts {
        archives(sourcesJar)
        archives(javadocJar)
    }

    dokka {
        moduleName.set("SaveStateObserver")
        dokkaPublications.html {
            suppressInheritedMembers.set(true)
            failOnWarning.set(true)
            outputDirectory.set(dokkaOutputDir)
        }
        dokkaSourceSets.main {
            sourceLink {
                localDirectory.set(file("src/main/kotlin"))
                remoteUrl("https://example.com/src")
            }
        }
        pluginsConfiguration.html {
            footerMessage.set("(c) Brad Barnhill")
        }
    }

    build {
        dependsOn(dokkaGenerate)
    }
}