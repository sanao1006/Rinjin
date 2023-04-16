val compose_ui_version: String by rootProject.extra
val retrofit_version = "2.9.0"
val moshi_version = "1.14.0"
val hilt_version = "2.44"
val hiltNavigation_version = "1.0.0"
val coroutines_version = "1.3.9"
val lifeCycleViewModel_version = "2.5.1"

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("com.diffplug.spotless")
}

android {
    namespace = "com.example.rinjin"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.rinjin"
        minSdk = 26
        targetSdk = 33
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
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.ui:ui:$compose_ui_version")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose_ui_version")
    implementation("androidx.compose.material:material:1.2.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_ui_version")
    debugImplementation("androidx.compose.ui:ui-tooling:$compose_ui_version")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$compose_ui_version")
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-compiler:$hilt_version")

    implementation("androidx.lifecycle:lifecycle-viewmodel:$lifeCycleViewModel_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifeCycleViewModel_version")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofit_version")

    implementation("com.squareup.moshi:moshi-kotlin:$moshi_version")
    implementation("androidx.hilt:hilt-navigation-compose:$hiltNavigation_version")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    ratchetFrom("origin/main")
    java {
        target("**/*.java")
        targetExclude("**/*.java")
        googleJavaFormat("1.16.0")
    }
    kotlin {
        target("**/*.kt")
        targetExclude("$buildDir/**/*.kt")
        targetExclude("bin/**/*.kt")
        ktfmt()
        ktlint("0.48.2").userData(mapOf("disabled_rules" to "no-wildcard-imports"))
    }

}
