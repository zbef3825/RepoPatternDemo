package com.cheesycoder.repositorydemo

import com.cheesycoder.repositorydemo.di.ActivityScoped
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Author: jinwo
 * Date: 2018-05-05
 * Package: com.cheesycoder.repositorydemo
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
abstract class MainActivityModule {

    @Module
    companion object {
        @JvmStatic
        @ActivityScoped
        @Provides
        fun providesFlowManager(activity: MainActivity): FlowManager {
            return FlowManager(activity)
        }
    }
}