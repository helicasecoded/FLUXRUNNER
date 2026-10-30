plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.fluxrunner.game"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.fluxrunner.game"
        minSdk = 24
        targetSdk = 34
        val vc = (System.getenv("VC") ?: "1").toInt()
        versionCode = vc
        versionName = "1.$vc"
    }

    signingConfigs {
        create("rel") {
            val p = System.getenv("KS_PATH")
            if (p != null) {
                storeFile = file(p)
                storePassword = System.getenv("KSP")
                keyAlias = "flux"
                keyPassword = System.getenv("KSP")
            }
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("rel")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
    lint {
        abortOnError = false
        checkReleaseBuilds = false
    }
}
