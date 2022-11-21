plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

dependencies {

    implementation(project(Modules.core))
    implementation(project(Modules.model))

    implementation(Android.appcompat)
    implementation(Android.material)
    implementation(Android.constraints)
    implementation(Android.liveData)
    implementation(Android.viewModel)
    implementation(Android.fragmentKtx)
    implementation(Kotlin.core)
    implementation(AdapterDelegates.adapter_delegates)
    implementation(AdapterDelegates.view_binding)
    implementation(Glide.glide)
    implementation(Navigation.fragment_ktx)
    implementation(Navigation.ui)
    kapt(Glide.compiler)

    testImplementation(TestImpl.junit)
    androidTestImplementation(TestImpl.ext_junit)
    androidTestImplementation(TestImpl.espresso)
}
