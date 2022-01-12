import io.gitlab.arturbosch.detekt.Detekt
import java.io.File
import java.net.URL
import java.nio.file.Files

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("it.nicolasfarabegoli.conventional-commits") version "1.0.1"
    id("io.gitlab.arturbosch.detekt") version "1.19.0"
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "it.unibo.sc"
        minSdk = 26
        targetSdk = 31
        versionCode = 1
        versionName = "0.1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_11)
        targetCompatibility(JavaVersion.VERSION_11)
    }

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.2")
    testImplementation("io.kotest:kotest-runner-junit5:5.0.2")
    androidTestImplementation("io.kotest:kotest-assertions-core-jvm:5.0.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.19.0")
}

detekt {
    config = files(File(projectDir, "detekt.yml"))
}
