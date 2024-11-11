plugins {
    id("kotlin-kapt")
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.room")
}

android {
    namespace = "com.example.themovieapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.themovieapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // TODO: Check this...
        // buildConfigField("String", "API_KEY", "\"${project.findProperty("API_KEY") ?: ""}\"")
    }

    buildFeatures {
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.lifeCycle)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converterGson)

    // Koin
    implementation(libs.koin)

    // Glide
    implementation(libs.glide)
    kapt("com.github.bumptech.glide:compiler:4.14.2")

    // ROOM
    implementation(libs.roomRuntime)
    implementation(libs.room)

    kapt("androidx.room:room-compiler:2.5.0")

    implementation(libs.coroutines)

    testImplementation(libs.junit)

    testImplementation(libs.mockito)
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")

    testImplementation("org.koin:koin-test:3.1.4")
    testImplementation("org.koin:koin-test-junit4:3.1.4")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
    testImplementation("androidx.arch.core:core-testing:2.1.0")

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

kapt {
    correctErrorTypes = true
}