package com.cheesycoder.repositorydemo.api

import com.cheesycoder.repositorydemo.model.WatchlistDataModel
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

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
    @GET("watchlists")
    fun getWatchlists(): Single<List<WatchlistDataModel>>

    @POST("watchlists")
    fun postWatchlist(@Body watchlistDataModel: WatchlistDataModel): Single<Void>
}