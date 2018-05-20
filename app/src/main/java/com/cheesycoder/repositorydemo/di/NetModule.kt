package com.cheesycoder.repositorydemo.di

import com.cheesycoder.repositorydemo.DemoApplication
import com.cheesycoder.repositorydemo.api.Api
import com.cheesycoder.repositorydemo.api.ApiInteractor
import com.cheesycoder.repositorydemo.api.ApiInteractorImp
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Author: jinwo
 * Date: 2018-05-13
 * Package: com.cheesycoder.repositorydemo.di
 *
 *\      _..--'''@   @'''--.._
 *\    .'   @_/-//-\/>/>'/ @  '.
 *\   (  @  /_<//<'/----------^-)
 *\   |'._  @     //|',|}}}}}}}}|
 *\   |  ~   ~   |/ | *|./|{{{{{|
 *\    '._ ~ ~ ~ |,/`````````````
 *\       ''--.~.|/
 */
@Module
class NetModule {
        @Provides
        @Singleton
        fun provideOkHttpCache(application: DemoApplication): Cache = Cache(application.cacheDir, 10 * 1024 * 1024)

        @Provides
        @Singleton
        fun provideGson(): Gson = GsonBuilder().create()

        @Provides
        @Singleton
        fun provideOkHttp(cache: Cache): OkHttpClient = OkHttpClient.Builder().cache(cache).build()

        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
                Retrofit.Builder()
                        .baseUrl("http://10.0.2.2/8889/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(okHttpClient)
                        .build()

        @Provides
        @Singleton
        fun provideApiService(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

        @Provides
        @Singleton
        fun provideApiInteractor(api: Api): ApiInteractor = ApiInteractorImp(api)
}