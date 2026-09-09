plugins {

    alias(libs.plugins.android.application)

    alias(libs.plugins.kotlin.android)

    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.ksp)

    alias(libs.plugins.hilt.plugin)

}


android {

    namespace = "com.jookmax.v7"

    compileSdk = 36


    defaultConfig {

        applicationId = "com.jookmax.v7"

        minSdk = 26

        targetSdk = 36

        versionCode = 1

        versionName = "1.0"

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"

    }


    buildTypes {

        release {

            isMinifyEnabled = false

            proguardFiles(

                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),

                "proguard-rules.pro"

            )

        }

    }


    compileOptions {

        sourceCompatibility = JavaVersion.VERSION_21

        targetCompatibility = JavaVersion.VERSION_21

    }


    buildFeatures {

        compose = true

        buildConfig = true

}


}


kotlin {

    jvmToolchain(21)

}



dependencies {


    // =========================
    // Android Core
    // =========================

    implementation(
        libs.androidx.core.ktx
    )


    implementation(
        libs.androidx.lifecycle.runtime.ktx
    )



    // =========================
    // Compose
    // =========================

    implementation(
        libs.androidx.activity.compose
    )


    implementation(
        platform(
            libs.androidx.compose.bom
        )
    )


    implementation(
        libs.androidx.compose.ui
    )


    implementation(
        libs.androidx.compose.ui.graphics
    )


    implementation(
        libs.androidx.compose.ui.tooling.preview
    )


    implementation(
        libs.androidx.compose.material3
    )



    // =========================
    // Navigation Compose
    // =========================

    implementation(
        libs.androidx.navigation.compose
    )


    implementation(
        libs.hilt.navigation.compose
    )



    // =========================
    // Hilt
    // =========================

    implementation(
        libs.hilt.android
    )


    ksp(
        libs.hilt.compiler
    )



    // =========================
    // Network
    // =========================

    implementation(
        libs.retrofit
    )


    implementation(
        libs.retrofit.converter.gson
    )


    implementation(
        libs.okhttp
    )


    implementation(
        libs.okhttp.logging
    )



    // =========================
    // Room
    // =========================

    implementation(
        "androidx.room:room-runtime:2.7.2"
    )


    implementation(
        "androidx.room:room-ktx:2.7.2"
    )


    ksp(
        "androidx.room:room-compiler:2.7.2"
    )



    // =========================
    // Testing
    // =========================

    testImplementation(
        libs.junit
    )


    androidTestImplementation(
        libs.androidx.junit
    )


    androidTestImplementation(
        libs.androidx.espresso.core
    )


    androidTestImplementation(
        platform(
            libs.androidx.compose.bom
        )
    )


    androidTestImplementation(
        libs.androidx.compose.ui.test.junit4
    )


    debugImplementation(
        libs.androidx.compose.ui.tooling
    )


    debugImplementation(
        libs.androidx.compose.ui.test.manifest
    )

}


