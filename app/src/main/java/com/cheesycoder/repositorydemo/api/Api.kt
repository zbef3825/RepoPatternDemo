package com.cheesycoder.repositorydemo.api

import com.cheesycoder.repositorydemo.model.WatchlistDataModel
import io.reactivex.Observable
import retrofit2.http.GET

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
interface Api {
    @GET("/watchlists")
    fun getWatchlists(): Observable<WatchlistDataModel>
}