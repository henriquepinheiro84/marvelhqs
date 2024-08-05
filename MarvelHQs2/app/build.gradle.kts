import environments.Dev
import environments.Prod
import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.compose.compiler)
    id("io.realm.kotlin") version "2.1.0"
}

android {
    namespace = "com.pinheiro.marvelhqs"
    compileSdk = 34

    defaultConfig {

        val localPropertiesFile = project.rootProject.file("local.properties")
        val properties = Properties()
        properties.load(localPropertiesFile.inputStream())
        val privateApiKey = properties.getProperty("PRIVATE_KEY")
        val publicApiKey = properties.getProperty("PUBLIC_KEY")

        buildConfigField(
            type = "String",
            name = "PRIVATE_KEY",
            value = privateApiKey
        )

        buildConfigField(
            type = "String",
            name = "PUBLIC_KEY",
            value = publicApiKey
        )


        applicationId = "com.pinheiro.marvelhqs"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"


        testInstrumentationRunner = "com.pinheiro.marvelhqs.di.InstrumentationTestRunner"
        vectorDrawables {
            useSupportLibrary = true
            android.buildFeatures.buildConfig = true
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(
                "String",
                "BASE_URL",
                Prod.NETWORK_BASE_URL

                )

        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(
                "String",
                "BASE_URL",
                Dev.NETWORK_BASE_URL

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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // Koin dependency
    implementation(libs.koin.android)
    // Pagination
    implementation(libs.androidx.paging.runtime)
    // optional - RxJava2 support
    implementation(libs.androidx.paging.rxjava2)
    // optional - RxJava3 support
    implementation(libs.androidx.paging.rxjava3)
    // optional - Guava ListenableFuture support
    implementation(libs.androidx.paging.guava)
    // optional - Jetpack Compose integration
    implementation(libs.androidx.paging.compose)
    //Room database
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)
    // optional - Paging 3 Integration
    implementation(libs.androidx.room.paging)
    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)
    implementation(libs.converter.gson)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.koin.androidx.compose)
    // Coil Compose
    implementation(libs.coil.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.ui.graphics)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.material.icons.extended.android)
    implementation(libs.paperdb)
    implementation(libs.library.base.v210)
    testImplementation(libs.truth)


}