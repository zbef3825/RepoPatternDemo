package com.cheesycoder.repositorydemo.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.cheesycoder.repositorydemo.vm.AppViewModelFactory
import com.cheesycoder.repositorydemo.vm.WatchlistViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Author: jinwo
 * Date: 2018-05-06
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
@Suppress("unused")
@Module
abstract class ViewModelModules {
    @Binds
    @IntoMap
    @ViewModelKey(WatchlistViewModel::class)
    abstract fun bindWatchlistViewModel(watchlistViewModel: WatchlistViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(appViewModelFactory: AppViewModelFactory): ViewModelProvider.Factory
}