plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.japanesestepbystep.japanese_step_by_step_web"
    compileSdk = 36

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    defaultConfig {
        // v2.3 使用獨立 applicationId，與舊 v2.2（com.japanesestepbystep.japanese_step_by_step_web）並存
        applicationId = "com.japanesestepbystep.japanese_step_by_step_v23"
        minSdk = 24
        targetSdk = 36
        versionCode = 3
        versionName = "2.3.0"
    }

    buildTypes {
        release {
            // 以 debug key 簽署，方便直接安裝（正式發布需另設 signingConfig）
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = false
        }
    }
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}

dependencies {
    implementation("androidx.activity:activity-ktx:1.9.3")
    implementation("androidx.core:core-ktx:1.13.1")
}
