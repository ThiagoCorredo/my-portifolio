package com.tcorredo.myportifolio.di.module

import com.squareup.moshi.Moshi
import com.tcorredo.myportifolio.BuildConfig
import com.tcorredo.myportifolio.BuildConfig.FOUR_DEVS_URL
import com.tcorredo.myportifolio.rest.representation.MoshiFactory
import com.tcorredo.myportifolio.rest.service.FourDevsService
import com.tcorredo.myportifolio.scheduler.SchedulerProvider
import com.tcorredo.myportifolio.scheduler.SchedulerProviderImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author Thiago Corredo
 * @since 2019-03-06
 */
@Module
internal class AppModule {

  @Provides
  @Singleton
  fun provideMoshi(): Moshi {
    return MoshiFactory.get()
  }

  @Provides
  @Singleton
  internal fun provideOkHttpClientCached(): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)

    if (BuildConfig.DEBUG) {
      val logging = HttpLoggingInterceptor { message ->
        Timber.tag("OkHttp")
            .d(message)
      }
      logging.level = HttpLoggingInterceptor.Level.BODY
      builder.addInterceptor(logging)
    }

    return builder.build()
  }

  @Provides
  @Singleton
  fun provideSchedulerProvider(): SchedulerProvider {
    return SchedulerProviderImpl()
  }

  @Provides
  @Singleton
  fun provideRetrofitBuilder(
    moshi: Moshi,
    okHttpClient: OkHttpClient,
    schedulerProvider: SchedulerProvider
  ): Retrofit.Builder {
    val ioScheduler = schedulerProvider.io()
    return Retrofit.Builder()
        .addConverterFactory(
            MoshiConverterFactory.create(moshi).asLenient()
        )
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(ioScheduler))
        .client(okHttpClient)
  }

  @Provides
  @Singleton
  fun provideFourService(
    retrofitBuilder: Retrofit.Builder
  ): FourDevsService {
    return retrofitBuilder.baseUrl(FOUR_DEVS_URL)
        .build()
        .create(FourDevsService::class.java)
  }
}