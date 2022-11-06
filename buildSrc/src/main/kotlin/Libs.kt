object Libs {
    const val coreKtx = "androidx.core:core-ktx:${Versions.AndroidX.coreKtx}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appCompat}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintLayout}"
    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.AndroidX.navigation}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.AndroidX.navigation}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.lifecycle}"

    const val hiltImplementation = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltKapt = "com.google.dagger:hilt-compiler:${Versions.hilt}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    const val glideImplementation = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideAnnotationProcessor = "com.github.bumptech.glide:compiler:${Versions.glide}"

    const val material = "com.google.android.material:material:${Versions.material}"
    const val paging = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
}