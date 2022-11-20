object Config {
    const val application_id = "ru.test.ecommerce"
    const val compile_sdk = 33
    const val min_sdk = 24
    const val target_sdk = 33
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val model = ":model"
    const val repository = ":repository"
    const val utils = ":utils"

    //Features
    const val historyScreen = ":historyScreen"
}

object Versions {

    // Android
    const val appcompat = "1.5.1"
    const val material = "1.7.0"
    const val constraints = "2.1.4"
    const val liveData = "2.5.1"
    const val viewModel = "2.5.1"
    const val viewModels = "1.5.4"

    // Kotlin
    const val core = "1.9.0"
    const val stdlib = "1.5.21"

    // Retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"

    // Dagger
    const val dagger = "2.44.2"

    // Glide
    const val glide = "4.14.2"

    // Adapter Delegates
    const val adapterDelegates = "4.3.2"

    // Test
    const val jUnit = "4.13.2"
    const val extjUnit = "1.1.4"
    const val espressoCore = "3.5.0"
}

object Android {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraints = "androidx.constraintlayout:constraintlayout:${Versions.constraints}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.liveData}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModel}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.viewModels}"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.stdlib}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
}

object Dagger {
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object AdapterDelegates {
    const val adapter_delegates =
        "com.hannesdorfmann:adapterdelegates4-kotlin-dsl:${Versions.adapterDelegates}"
    const val view_binding =
        "com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding:${Versions.adapterDelegates}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val ext_junit = "androidx.test.ext:junit:${Versions.extjUnit}"
    const val espresso =
        "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}


