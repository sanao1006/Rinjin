buildscript {
    val compose_ui_version by extra("1.2.0")
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")
        classpath("com.diffplug.spotless:spotless-plugin-gradle:6.18.0")
    }
}
plugins {
    id("com.android.application") version "7.4.2" apply false
    id("com.android.library") version "7.4.2" apply false
    id("org.jetbrains.kotlin.android") version "1.7.0" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.3.1"
}

apply(plugin = "com.diffplug.spotless")