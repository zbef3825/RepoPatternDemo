package com.cheesycoder.repositorydemo.vm

import android.arch.lifecycle.*
import android.util.Log
import com.cheesycoder.repositorydemo.api.Api
import com.cheesycoder.repositorydemo.api.ApiInteractor
import com.cheesycoder.repositorydemo.model.WatchlistDataModel
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

    val watchlist: MutableLiveData<List<WatchlistDataModel>> by lazy {
        MutableLiveData<List<WatchlistDataModel>>()
    }

    init {

    }

    fun start() {

    }

    fun stop() {

    }
}