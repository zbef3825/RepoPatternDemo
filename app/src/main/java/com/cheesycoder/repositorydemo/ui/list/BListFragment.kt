package com.cheesycoder.repositorydemo.ui.list

import android.support.v7.widget.RecyclerView
import com.cheesycoder.repositorydemo.di.ActivityScoped
import javax.inject.Inject

/**
 * Author: jinwo
 * Date: 2018-05-10
 * Package: com.cheesycoder.repositorydemo.ui.list
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
class BListFragment @Inject constructor() : ListFragment() {

    companion object {
        val TAG = "b.list.fragment"
    }

    override fun actionButtonPressed() {

    }

    override fun getAdapterFromChildFragment(): ListAdapter = ListAdapter(context)

    override fun getButtonText(): String = "Remove Top Item"
}