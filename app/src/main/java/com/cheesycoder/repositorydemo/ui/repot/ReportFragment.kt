package com.cheesycoder.repositorydemo.ui.repot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheesycoder.repositorydemo.R
import com.cheesycoder.repositorydemo.di.ActivityScoped
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * Author: jinwo
 * Date: 2018-05-06
 * Package: com.cheesycoder.repositorydemo.ui.repot
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
class ReportFragment @Inject constructor(): DaggerFragment() {

    companion object {
        val TAG = "report.fragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View? = inflater.inflate(R.layout.fragment_report, container, false)
        return view
    }
}