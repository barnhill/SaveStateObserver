buildscript {
    extra["gradleVersion"] = "8.5"
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }
}
val gradleVersion: String by extra

plugins {
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.maven.publish).apply(false)
    alias(libs.plugins.gradle.cachefix).apply(false)
}

tasks {
    wrapper {
        gradleVersion = gradleVersion
        distributionType = Wrapper.DistributionType.BIN
    }
}