plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.dev.gm"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.dev.gm"
        minSdk = 28
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation ("androidx.compose.material:material-icons-extended:1.1.1")

    // Integration with ViewModels
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.0")

    // Integration with observables
    implementation ("androidx.compose.runtime:runtime-livedata:1.3.0-alpha01")
    implementation ("androidx.compose.runtime:runtime-rxjava2:1.3.0-alpha01")
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.5.0")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Coroutine Lifecycle Scopes
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.5.0")

    //navigation component
    implementation ("androidx.navigation:navigation-compose:2.5.0")

    //retrofit + Gson
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")


    //  Hilt
    implementation("com.google.dagger:hilt-android:2.47")
    kapt("com.google.dagger:hilt-compiler:2.47")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    //coil for imagery using compose
    implementation("io.coil-kt:coil-compose:2.1.0")

    //constraintLayout
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    //scalable pixel density
    implementation ("com.intuit.sdp:sdp-android:1.1.0")

    //splash screen
    implementation ("androidx.core:core-splashscreen:1.0.0-rc01")

    //Accompanist libs
    //Pager
    implementation ("com.google.accompanist:accompanist-pager:0.18.0")

    //pager-indicator
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.18.0")
}