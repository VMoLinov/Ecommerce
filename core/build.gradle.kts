plugins {
    id("com.android.library")
    id("kotlin-android")
}

dependencies {

    implementation(Android.fragmentKtx)

    testImplementation(TestImpl.junit)
    androidTestImplementation(TestImpl.ext_junit)
    androidTestImplementation(TestImpl.espresso)
}
