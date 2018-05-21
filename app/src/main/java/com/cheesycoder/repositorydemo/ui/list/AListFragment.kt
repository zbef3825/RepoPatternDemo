package com.cheesycoder.repositorydemo.ui.list

import android.arch.lifecycle.Observer
import android.view.View
import com.cheesycoder.repositorydemo.api.Status
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
        watchlistViewModel.watchlist.observe(this, Observer {
            it?.let {
                when (it.status) {
                    Status.LOADING -> {
                        progressView.visibility = View.VISIBLE
                        errorView.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        errorView.visibility = View.GONE
                        progressView.visibility = View.GONE
                        listAdapter.apply {
                            it.data?.let {
                                if (it.isNotEmpty()) this.internalData = it
                                else errorView.visibility = View.VISIBLE
                            }
                        }
                    }
                    Status.ERROR -> {
                        errorView.visibility = View.VISIBLE
                        progressView.visibility = View.GONE
                    }
                }
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