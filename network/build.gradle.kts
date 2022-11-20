plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

dependencies {

    implementation(Android.fragmentKtx)
    implementation(Dagger.dagger)
    kapt(Dagger.compiler)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.converter_gson)

    testImplementation(TestImpl.junit)
    androidTestImplementation(TestImpl.ext_junit)
    androidTestImplementation(TestImpl.espresso)
}
