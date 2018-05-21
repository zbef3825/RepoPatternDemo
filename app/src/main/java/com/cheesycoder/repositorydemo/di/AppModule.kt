package com.cheesycoder.repositorydemo.di

import android.content.Context
import com.cheesycoder.repositorydemo.DemoApplication
import dagger.Binds
import dagger.Module
import dagger.Provides

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
abstract class AppModule {
    @Binds
    abstract fun bindContext(application: DemoApplication): Context
}