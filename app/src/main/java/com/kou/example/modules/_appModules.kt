package com.kou.example.modules

import com.kou.example.repository.MyRepository
import com.kou.example.repository.api.BookAPI
import com.kou.example.util.Constants
import com.kou.example.viewModels.MainViewModel
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val repositoryModule = module {
    single { MyRepository(get()) }
}

val networkModule = module {
    single { provideRetrofit(get(), get()) }
    factory { provideOkHttpClient() }
    factory { provideMoshi() }
    single { provideBookAPI(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

/**
 * @return [Retrofit] instance
 */
fun provideRetrofit(
    okHttpClient: OkHttpClient,
    moshi: Moshi
): Retrofit = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .client(okHttpClient)
    .build()

/**
 * @return [OkHttpClient] instance
 */
fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .readTimeout(10L, TimeUnit.SECONDS)
    .addInterceptor (
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    )
    .build()

/**
 * @return [Moshi] instance
 */
fun provideMoshi(): Moshi = Moshi.Builder().build()

/**
 * @return [BookAPI] instance
 */
fun provideBookAPI(retrofit: Retrofit): BookAPI = retrofit.create(BookAPI::class.java)
