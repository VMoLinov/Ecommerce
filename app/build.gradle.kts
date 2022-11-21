plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-android")
    id ("kotlin-kapt")
}

android {
    namespace = Config.application_id
    compileSdk = Config.compile_sdk

    defaultConfig {
        applicationId = Config.application_id
        minSdk = Config.min_sdk
        targetSdk = Config.target_sdk
        versionCode = Releases.version_code
        versionName = Releases.version_name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {

    implementation (project(Modules.core))
    implementation (project(Modules.network))
    implementation (project(Modules.model))
    implementation (project(Modules.featureMain))
    implementation (project(Modules.featureDetails))
    implementation (project(Modules.featureCart))

    implementation (Android.appcompat)
    implementation (Android.material)
    implementation (Android.constraints)
    implementation (Android.liveData)
    implementation (Android.viewModel)
    implementation (Android.fragmentKtx)
    implementation (Kotlin.core)
    implementation (AdapterDelegates.adapter_delegates)
    implementation (AdapterDelegates.view_binding)
    implementation (Glide.glide)
    implementation (Navigation.fragment_ktx)
    implementation (Navigation.ui)
    kapt (Glide.compiler)
    implementation (Dagger.dagger)
    kapt (Dagger.compiler)

    testImplementation (TestImpl.junit)
    androidTestImplementation (TestImpl.ext_junit)
    androidTestImplementation (TestImpl.espresso)
}
