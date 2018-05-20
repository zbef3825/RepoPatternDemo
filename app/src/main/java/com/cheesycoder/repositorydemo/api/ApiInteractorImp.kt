package com.cheesycoder.repositorydemo.api

import com.cheesycoder.repositorydemo.model.WatchlistDataModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
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
class ApiInteractorImp @Inject constructor(
        val api: Api
): BaseInteractor(), ApiInteractor {

    companion object {
        private const val TIMER_IN_MILI = 120000L
    }

    private var inMemoryWatchlist: List<WatchlistDataModel>? = null

    override fun getApiThresholdTimer(): Long = TIMER_IN_MILI

    override fun getWatchlists(): Observable<List<WatchlistDataModel>>? {
        // Fetch new data
        return if (isTimeToDownload()) {
            api.getWatchlists().subscribeOn(Schedulers.io())
        } else {
            // Retrieve from local DB
            if (inMemoryWatchlist == null) {
                null
            } else {
                inMemoryWatchlist?.run {
                    Observable.just(this).subscribeOn(Schedulers.io())
                }
            }
        }
    }

    override fun getWatchlist(id: Int): Observable<WatchlistDataModel>? {
        TODO("Implement This")
    }

    override fun postWatchlist(id: Int): Observable<Void>? {
        TODO("Implement This")
    }

    override fun deleteWatchlist(id: Int): Observable<Void>? {
        TODO("Implement This")
    }
}

interface ApiInteractor {
    fun getWatchlists(): Observable<List<WatchlistDataModel>>?
    fun getWatchlist(id: Int): Observable<WatchlistDataModel>?
    fun postWatchlist(id: Int): Observable<Void>?
    fun deleteWatchlist(id: Int): Observable<Void>?
}