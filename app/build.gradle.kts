plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin)
    kotlin("plugin.parcelize")
}

android {
    namespace = "com.example.exercise202"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.exercise202"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        viewBinding = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}



dependencies {
    testImplementation(libs.androidx.core.testing)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.espresso.idling.resource)
    implementation(libs.androidx.espresso.contrib)
    implementation(libs.androidx.junit.ktx)
    implementation(libs.androidx.espresso.intents)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)

    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.espresso.core)

    androidTestImplementation(libs.androidx.rules)
//
//    implementation(libs.work.runtime)
//    implementation(libs.play.services.location)
//    implementation(libs.glide)
//    implementation(libs.retrofit.converter.moshi)
//    implementation(libs.retrofit.converter.scalars)
//    implementation(libs.retrofit)
//    implementation(libs.androidx.navigation.fragment)
//    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.play.services.maps)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}