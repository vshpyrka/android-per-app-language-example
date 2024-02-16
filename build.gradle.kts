plugins {
    alias(pluginLibs.plugins.android.library)
    alias(pluginLibs.plugins.kotlin.android)
}

android {
    namespace = "com.example.language"
    compileSdk = sdk.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = sdk.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.activity.ktx)
    implementation(libs.fragment.ktx)
    implementation(libs.constraintlayout)
}
