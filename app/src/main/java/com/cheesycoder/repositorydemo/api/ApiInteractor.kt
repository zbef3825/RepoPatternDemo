package com.cheesycoder.repositorydemo.api

import android.util.Log
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Author: jinwo
 * Date: 2018-05-13
 * Package: com.cheesycoder.repositorydemo.api
 *
 *\      _..--'''@   @'''--.._
 *\    .'   @_/-//-\/>/>'/ @  '.
 *\   (  @  /_<//<'/----------^-)
 *\   |'._  @     //|',|}}}}}}}}|
 *\   |  ~   ~   |/ | *|./|{{{{{|
 *\    '._ ~ ~ ~ |,/`````````````
 *\       ''--.~.|/
 */
@Singleton
class ApiInteractor @Inject constructor(
        val api: Api
) {
    init {
        Log.d("Debugger", "Init")
    }
//    fun getWatchlists(): Observable<WatchlistDataModel> = api.getWatchlists()
}