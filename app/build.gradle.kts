plugins {
  id("com.android.application")
  id("kotlin-android")
}

android {
  compileSdk = 30
  buildToolsVersion = "30.0.3"

  defaultConfig {
    applicationId = "com.example.androidcomposesample"
    minSdk = 21
    targetSdk = 30
    versionCode = 1
    versionName = "1.0"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

//    buildTypes {
//        release {
//            minifyEnabled false
//            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
//        }
//    }
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
    kotlinCompilerVersion = "1.5.10"
    kotlinCompilerExtensionVersion = Versions.Androidx.compose
  }
}

dependencies {

  implementation("androidx.core:core-ktx:1.5.0")
  implementation("androidx.appcompat:appcompat:1.3.0")
  implementation(Deps.material)

  implementation(Deps.KotlinX.Coroutines.android)

  implementation(Deps.AndroidX.constraintlayout)
  implementation(Deps.AndroidX.Compose.ui)
  implementation(Deps.AndroidX.Compose.uiTooling)
  implementation(Deps.AndroidX.Compose.foundation)
  implementation(Deps.AndroidX.Compose.foundationLayout)
  implementation(Deps.AndroidX.Compose.material)
  implementation(Deps.AndroidX.Compose.materialIconsCore)
  implementation(Deps.AndroidX.Compose.materialIconExtended)
  implementation(Deps.AndroidX.Compose.activity)
  implementation(Deps.AndroidX.Compose.constraintlayout)
  implementation(Deps.AndroidX.Compose.navigation)
  implementation(Deps.AndroidX.Compose.runtimeLiveData)

  implementation(Deps.Accompanist.coil)

}