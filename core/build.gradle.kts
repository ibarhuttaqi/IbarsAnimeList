plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.ibarsanimelist.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "BASE_URL", "\"https://api.jikan.moe/v4/\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
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
        buildConfig = true
    }
}

dependencies {

    //api = bisa digunakan di module lain selama assign module yg menggunakan tag api
    //implementation = cuma berlaku di module ini saja
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    api(libs.androidx.navigation.fragment.ktx)
    api(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.activity)
//    testImplementation(libs.junit)
    api(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
    api(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    api(libs.material)

    //    glide
    api(libs.glide)

    //    retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    //    room
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    //  koin
    api(libs.koin.core)
    api(libs.koin.android)

    //  leak canary
    debugImplementation(libs.leakcanary.android)

    //  encryption
    implementation(libs.android.database.sqlcipher)
    implementation(libs.androidx.sqlite.ktx)
}