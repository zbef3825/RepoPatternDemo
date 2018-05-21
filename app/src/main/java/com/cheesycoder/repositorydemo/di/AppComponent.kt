package com.cheesycoder.repositorydemo.di

import com.cheesycoder.repositorydemo.DemoApplication
import com.cheesycoder.repositorydemo.api.Api
import com.cheesycoder.repositorydemo.api.ApiInteractor
import com.google.gson.Gson
//import com.cheesycoder.repositorydemo.api.ApiInteractor
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Author: jinwo
 * Date: 2018-05-05
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
@Singleton
@Component(modules = [
    NetModule::class,
    AppModule::class,
    DbModule::class,
    ActivityBindingModule::class,
    ViewModelModules::class,
    AndroidSupportInjectionModule::class
])
interface AppComponent: AndroidInjector<DemoApplication> {

    fun getApiInteractor(): ApiInteractor

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: DemoApplication): AppComponent.Builder

        fun build(): AppComponent
    }
}