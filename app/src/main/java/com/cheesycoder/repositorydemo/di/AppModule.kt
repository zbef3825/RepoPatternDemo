package com.cheesycoder.repositorydemo.di

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import com.cheesycoder.repositorydemo.DemoApplication
import com.cheesycoder.repositorydemo.db.AppDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
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
@Module
class AppModule {
    @Provides
    @Singleton
    fun bindContext(application: DemoApplication): Context = application

    @Provides
    @Singleton
    fun providesAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_database").build()
    }

    @Provides
    @Singleton
    @Named(value = "vmConfig")
    fun provideSharedPreference(context: Context): SharedPreferences
            = context.getSharedPreferences("vmConfig", 0)
}