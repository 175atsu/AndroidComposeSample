object Deps {
  const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.0-alpha08"
  const val material = "com.google.android.material:material:${Versions.material}"
  const val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"

  object Accompanist {
    const val coil = "com.google.accompanist:accompanist-coil:${Versions.Accompanist.coil}"
  }

  object Kotlin {
    private const val version = "1.4.30"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
    const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
  }

  object KotlinX {
    object Coroutines {
      const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.KotlinX.coroutines}"
    }
  }

  object JUnit {
    private const val version = "4.13"
    const val junit = "junit:junit:$version"
  }

  object AndroidX {
    object Activity {
      const val ktx = "androidx.activity:activity-ktx:${Versions.Androidx.activity}"
      const val compose = "androidx.activity:activity-compose:${Versions.Androidx.activity}"
    }

    object Fragment {
      const val ktx =
        "androidx.fragment:fragment-ktx:${Versions.Androidx.fragment}"
    }

    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.Androidx.constraintlayout}"

    const val navigation = "androidx.navigation:navigation-compose:1.0.0-alpha08"

    const val livedata ="androidx.lifecycle:lifecycle-livedata-ktx:2.3.1"

    object Compose {
      const val ui = "androidx.compose.ui:ui:${Versions.Androidx.compose}"
      const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.Androidx.compose}"
      const val foundation = "androidx.compose.foundation:foundation:${Versions.Androidx.compose}"
      const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.Androidx.compose}"
      const val material = "androidx.compose.material:material:${Versions.Androidx.compose}"
      const val materialIconsCore = "androidx.compose.material:material-icons-core:${Versions.Androidx.compose}"
      const val materialIconExtended = "androidx.compose.material:material-icons-extended:${Versions.Androidx.compose}"
      const val activity = "androidx.activity:activity-compose:${Versions.Androidx.activity}"
      const val constraintlayout = "androidx.constraintlayout:constraintlayout-compose:${Versions.Androidx.constraintlayout2}"
      const val navigation = "androidx.navigation:navigation-compose:${Versions.Androidx.navigation}"

      const val animation = "androidx.compose.animation:animation:${Versions.Androidx.compose}"
      const val layout = "androidx.compose.foundation:foundation-layout:${Versions.Androidx.compose}"
      const val runtime = "androidx.compose.runtime:runtime:${Versions.Androidx.compose}"
      const val runtimeLiveData = "androidx.compose.runtime:runtime-livedata:${Versions.Androidx.compose}"
      const val uiUtil = "androidx.compose.ui:ui-util:${Versions.Androidx.compose}"
      const val uiTest = "androidx.compose.ui:ui-test-junit4:${Versions.Androidx.compose}"
    }

    object Accompanist {
      const val coil = "com.google.accompanist:accompanist-coil:${Versions.Accompanist.coil}"
    }

    object Test {
      const val core = "androidx.test:core-ktx:${Versions.Androidx.Test.core}"
      const val runner = "androidx.test:runner:${Versions.Androidx.Test.runner}"
      const val rules = "androidx.test:rules:${Versions.Androidx.Test.rules}"
      const val junit = "androidx.test.ext:junit-ktx:${Versions.Androidx.Test.junit}"
      const val truth = "androidx.test.ext:truth:${Versions.Androidx.Test.truth}"
    }
  }
}

