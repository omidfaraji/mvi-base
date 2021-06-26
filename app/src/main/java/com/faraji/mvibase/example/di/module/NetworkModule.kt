package com.faraji.mvibase.example.di.module

import android.content.Context
import com.faraji.mvibase.BuildConfig
import com.faraji.mvibase.example.data.source.remote.CatRestService
import com.faraji.mvibase.example.utils.EnumRetrofitConverterFactory
import com.google.gson.*
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        const val BASE_URL = "https://api.thecatapi.com/"
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(
                Date::class.java,
                JsonDeserializer { json, _, _ -> Date(json.asJsonPrimitive.asLong) })
            .registerTypeAdapter(
                Date::class.java,
                JsonSerializer<Date> { src, _, _ -> JsonPrimitive(src.time) })
            .create()
    }


    @Singleton
    @Provides
    fun provideChuckInterceptor(
        @ApplicationContext
        context: Context
    ): ChuckInterceptor {
        return ChuckInterceptor(context)
    }


    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }


    @Singleton
    @Provides
    fun provideOkHttpClient(
        chuckInterceptor: ChuckInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            addInterceptor(chuckInterceptor)
            if (BuildConfig.DEBUG)
                addInterceptor(httpLoggingInterceptor)
        }.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(EnumRetrofitConverterFactory())
            .baseUrl(BASE_URL)
            .client(okHttpClient).build()
    }

    @Singleton
    @Provides
    fun provideCatRestService(retrofit: Retrofit): CatRestService =
        retrofit.create(CatRestService::class.java)

}
