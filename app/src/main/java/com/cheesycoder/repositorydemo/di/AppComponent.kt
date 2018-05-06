package com.cheesycoder.repositorydemo.di

import com.cheesycoder.repositorydemo.DemoApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
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
    AppModule::class,
    ActivityBindingModule::class,
    ViewModelModules::class,
    AndroidSupportInjectionModule::class
])
interface AppComponent: AndroidInjector<DemoApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: DemoApplication): AppComponent.Builder

        fun build(): AppComponent
    }
}