package com.cheesycoder.repositorydemo.ui.list

import android.arch.lifecycle.Observer
import com.cheesycoder.repositorydemo.di.ActivityScoped
import com.cheesycoder.repositorydemo.model.WatchlistDataModel
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
    private val listAdapter: ListAdapter by lazy {
        ListAdapter(context)
    }
    companion object {
        val TAG = "a.list.fragment"
    }
    override fun actionButtonPressed() {

    }

    override fun onResume() {
        super.onResume()
        watchlistViewModel.watchlist.observe(this, Observer<List<WatchlistDataModel>> {
            it?.apply {
                listAdapter.internalData = this
            }
        })
    }

    override fun onPause() {
        super.onPause()
        watchlistViewModel.watchlist.removeObservers(this)
    }

    override fun getAdapterFromChildFragment(): ListAdapter = listAdapter

    override fun getButtonText(): String = "Add More Items"
}