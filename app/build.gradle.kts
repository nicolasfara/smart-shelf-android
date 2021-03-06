plugins {
    id("com.android.application")
    id("kotlin-android")
    id("it.nicolasfarabegoli.conventional-commits") version "1.0.3"
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
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("com.amplifyframework:core-kotlin:0.17.0")
    implementation("com.amplifyframework:aws-auth-cognito:1.33.0")
    implementation("com.amplifyframework:aws-api:1.33.0")
    implementation("androidx.paging:paging-runtime-ktx:3.1.1")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("io.kotest:kotest-runner-junit5:5.2.2")
    androidTestImplementation("io.kotest:kotest-assertions-core-jvm:5.2.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.19.0")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")
}

detekt {
    toolVersion = "1.19.0"
    config = files("detekt.yml")
    ignoredBuildTypes = listOf("release")
    ignoredFlavors = listOf("production")
    ignoredVariants = listOf("productionRelease")
}
