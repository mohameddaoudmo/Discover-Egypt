plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")}

android {
    namespace = "com.example.discoveregypt"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.discoveregypt"
        minSdk = 26
        targetSdk = 34
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
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation ("com.google.android.exoplayer:exoplayer-core:2.17.1")
    implementation ("com.google.android.exoplayer:exoplayer-ui:2.17.1")
    //navigationbar
    implementation("com.exyte:animated-navigation-bar:1.0.0")


    implementation("io.coil-kt:coil-compose:2.6.0")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.1")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    //swipe to refresh
    implementation("com.google.accompanist:accompanist-swiperefresh:0.19.0")

    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("androidx.compose.animation:animation-graphics:1.1.1")
    implementation("com.airbnb.android:lottie-compose:6.3.0")
    //accompanist
    implementation("com.google.accompanist:accompanist-pager:0.24.9-beta")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.24.9-beta")
    //compose navigation
    implementation("androidx.navigation:navigation-compose:2.4.2")
    //livedata
    implementation("androidx.compose.runtime:runtime-livedata:1.6.2")
    implementation("androidx.navigation:navigation-common:2.7.4")
    // biometric
    implementation("androidx.biometric:biometric:1.1.0")
    implementation("com.google.maps.android:maps-compose:1.2.0")
    implementation("com.google.android.gms:play-services-maps:18.0.2")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.24.5-alpha")
// geson for country List spanner
    implementation ("com.google.code.gson:gson:2.8.7")

    //page accompoinst
    implementation ("com.google.accompanist:accompanist-pager:0.13.0")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.13.0")
//extended Icon
    implementation ("androidx.compose.material:material-icons-extended")

    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    //navigationbar
    implementation("com.exyte:animated-navigation-bar:1.0.0")


    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation ("com.google.accompanist:accompanist-insets:0.17.0")
    implementation ("com.google.accompanist:accompanist-flowlayout:0.20.0")


    implementation ("com.google.accompanist:accompanist-permissions:0.26.5-rc")


    implementation ("com.intuit.sdp:sdp-android:1.0.6")
    implementation ("com.intuit.ssp:ssp-android:1.0.6")
    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    //okHttp
    implementation ("com.squareup.okhttp3:okhttp:4.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")

    //Coil Image
    implementation ("com.google.accompanist:accompanist-coil:0.12.0")

    //Pagination
    implementation ("androidx.paging:paging-compose:1.0.0-alpha10")


}