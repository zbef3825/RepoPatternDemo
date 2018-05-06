package com.cheesycoder.repositorydemo.di

import com.cheesycoder.repositorydemo.MainActivity
import com.cheesycoder.repositorydemo.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

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
@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity(): MainActivity
}