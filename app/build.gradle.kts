plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.compose")  // ← Add this line

}

android {
    namespace = "com.example.myapplication"  // ← Add this line

    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.ecommerce"
        minSdk =23
        targetSdk =36
        versionCode = 1
        versionName ="1.0"
    }

    buildFeatures {
        compose =true
    }

    composeOptions {
        kotlinCompilerExtensionVersion ="1.5.3"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // -------------- Compose BOM --------------
    val composeBom = platform("androidx.compose:compose-bom:2024.11.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // -------------- Activity Compose --------------
    implementation("androidx.activity:activity-compose:1.9.2")

    // -------------- Navigation Compose --------------
    implementation("androidx.navigation:navigation-compose:2.8.2")
    // -------------- ViewModel + Lifecycle --------------
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    //coil
    implementation("io.coil-kt:coil-compose:3.0.0")
    //glide
    implementation("com.github.skydoves:landscapist-glide:2.2.13")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.14") // Check Maven for the latest version

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
}
