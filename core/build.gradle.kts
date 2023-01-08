plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

dependencies {

    implementation(project(Modules.domain))
    implementation(project(Modules.repository))

    implementation(Android.material)
    implementation(Android.fragmentKtx)
    implementation(Dagger.dagger)
    kapt(Dagger.compiler)

    testImplementation(TestImpl.junit)
    androidTestImplementation(TestImpl.ext_junit)
    androidTestImplementation(TestImpl.espresso)
}
