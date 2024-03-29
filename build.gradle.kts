buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.gradle}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

subprojects {
    apply(from = "$rootDir/config/ktlint.gradle.kts")

    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}