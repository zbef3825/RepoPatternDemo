package com.cheesycoder.repositorydemo

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.cheesycoder.repositorydemo.di.ActivityScoped
import com.cheesycoder.repositorydemo.ui.list.AListFragment
import com.cheesycoder.repositorydemo.ui.list.BListFragment
import com.cheesycoder.repositorydemo.ui.repot.ReportFragment
import dagger.Lazy
import javax.inject.Inject

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
@ActivityScoped
class FlowManager @Inject constructor(
        activity: AppCompatActivity,
        val aListFragmentProvider: Lazy<AListFragment>,
        val bListFragmentProvider: Lazy<BListFragment>,
        val reportFragmentProvider: Lazy<ReportFragment>,
        @IdRes val rootViewId: Int) {

    private val fragmentManager: FragmentManager = activity.supportFragmentManager

    fun displayAListFragment() {
        displayFragmentWithTag(
                AListFragment.TAG,
                fragmentManager.findFragmentByTag(AListFragment.TAG) == null
        )
    }

    fun displayBListFragment() {
        displayFragmentWithTag(
                BListFragment.TAG,
                fragmentManager.findFragmentByTag(BListFragment.TAG) == null
        )
    }

    fun displayReportFragment() {
        displayFragmentWithTag(
                ReportFragment.TAG,
                fragmentManager.findFragmentByTag(ReportFragment.TAG) == null
        )
    }

    private fun displayFragmentWithTag(tag: String, requireGet: Boolean) {
        val fragment: Fragment? = when(tag) {
            AListFragment.TAG -> {
                if (requireGet) aListFragmentProvider.get()
                else null
            }
            BListFragment.TAG -> {
                if (requireGet) bListFragmentProvider.get()
                else null
            }
            else -> {
                if (requireGet) reportFragmentProvider.get()
                else null
            }
        }
        if (fragment != null) {
            fragmentManager.beginTransaction()
                    .replace(rootViewId, fragment, tag)
                    .commit()
        }
    }
}