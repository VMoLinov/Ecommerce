plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

dependencies {

    implementation(Dagger.dagger)
    kapt(Dagger.compiler)

    testImplementation(TestImpl.junit)
    androidTestImplementation(TestImpl.ext_junit)
    androidTestImplementation(TestImpl.espresso)
}