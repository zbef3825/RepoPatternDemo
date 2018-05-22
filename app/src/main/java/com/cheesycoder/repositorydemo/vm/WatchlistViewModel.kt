package com.cheesycoder.repositorydemo.vm

import android.arch.lifecycle.*
import android.util.Log
import com.cheesycoder.repositorydemo.BuildConfig
import com.cheesycoder.repositorydemo.api.Api
import com.cheesycoder.repositorydemo.api.ApiInteractor
import com.cheesycoder.repositorydemo.api.DataWrapper
import com.cheesycoder.repositorydemo.model.WatchlistDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Author: jinwo
 * Date: 2018-05-06
 * Package: com.cheesycoder.repositorydemo.vm
 *
 *\      _..--'''@   @'''--.._
 *\    .'   @_/-//-\/>/>'/ @  '.
 *\   (  @  /_<//<'/----------^-)
 *\   |'._  @     //|',|}}}}}}}}|
 *\   |  ~   ~   |/ | *|./|{{{{{|
 *\    '._ ~ ~ ~ |,/`````````````
 *\       ''--.~.|/
 */
class WatchlistViewModel @Inject constructor(
        val apiInteractor: ApiInteractor
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val watchlist: MutableLiveData<DataWrapper<List<WatchlistDataModel>>> by lazy {
        MutableLiveData<DataWrapper<List<WatchlistDataModel>>>()
    }

    fun start() {
        compositeDisposable.add(
                apiInteractor.getWatchlists()
                        .subscribe(
                                {
                                    watchlist.value = DataWrapper.success(it)
                                },
                                {
                                    if (BuildConfig.DEBUG) it.printStackTrace()
                                    watchlist.value = DataWrapper.error(it as Exception)
                                }
                        )
        )
    }

    fun addWatchlist() {
        TODO("Implement add to watch list")
//        val testWatchlist = WatchlistDataModel(
//                title = "testing_title_with_button",
//                body = "testing_body_with_button",
//                photo_url = "testing_photo_url_with_button"
//        )
//        compositeDisposable.add(
//                apiInteractor.postWatchlist(testWatchlist)
//                        .subscribe(
//                                {
//                                    watchlist.value = DataWrapper.success(it)
//                                },
//                                {
//                                    if (BuildConfig.DEBUG) it.printStackTrace()
//                                    // Nothing happens
//                                }
//                        )
//        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        apiInteractor.saveConfig()
        super.onCleared()
    }
}


