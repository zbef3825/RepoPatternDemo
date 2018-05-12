package com.cheesycoder.repositorydemo

import com.cheesycoder.repositorydemo.di.ActivityScoped
import com.cheesycoder.repositorydemo.di.FragmentScoped
import com.cheesycoder.repositorydemo.ui.list.AListFragment
import com.cheesycoder.repositorydemo.ui.list.BListFragment
import com.cheesycoder.repositorydemo.ui.repot.ReportFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

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
        fun providesFlowManager(activity: MainActivity,
                                aListFragmentProvider: dagger.Lazy<AListFragment>,
                                bListFragmentProvider: dagger.Lazy<BListFragment>,
                                reportFragmentProvider: dagger.Lazy<ReportFragment>): FlowManager {
            return FlowManager(
                    activity,
                    aListFragmentProvider,
                    bListFragmentProvider,
                    reportFragmentProvider,
                    R.id.fragment_container
            )
        }
    }

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun aListFragment(): AListFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bListFragment(): BListFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun reportFragment(): ReportFragment
}