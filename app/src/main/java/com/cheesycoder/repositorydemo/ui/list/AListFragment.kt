package com.cheesycoder.repositorydemo.ui.list

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
class AListFragment @Inject constructor() : ListFragment() {
    companion object {
        val TAG = "a.list.fragment"
    }
    override fun actionButtonPressed() {

    }

    override fun getAdapterFromChildFragment(): ListAdapter = ListAdapter(context)

    override fun getButtonText(): String = "Add More Items"
}