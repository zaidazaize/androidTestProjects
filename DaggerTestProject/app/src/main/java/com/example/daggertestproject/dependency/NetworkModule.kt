package com.example.daggertestproject.dependency

import com.example.daggertestproject.data.network.LoginRetrofitService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
class NetworkModule {

    @Provides
    fun provideLoginRetrofitService(okHttpClient: OkHttpClient): LoginRetrofitService {
        return Retrofit.Builder()
            .baseUrl("https://example.com")
            .build()
            .create(LoginRetrofitService::class.java)
    }
}