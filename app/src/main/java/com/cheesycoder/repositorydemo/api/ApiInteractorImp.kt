package com.cheesycoder.repositorydemo.api

import com.cheesycoder.repositorydemo.db.AppDatabase
import com.cheesycoder.repositorydemo.db.WatchlistDao
import com.cheesycoder.repositorydemo.model.WatchlistDataModel
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
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
        val api: Api,
        val appDatabase: AppDatabase
): BaseInteractor(), ApiInteractor {

    companion object {
        private const val TIMER_IN_MILI = 120000L
    }

    private val watchDao: WatchlistDao by lazy {
        appDatabase.watchlistDao()
    }

    private var inMemoryWatchlist: List<WatchlistDataModel>? = null

    override fun getApiThresholdTimer(): Long = TIMER_IN_MILI

    override fun getWatchlists(): Single<List<WatchlistDataModel>>? {
        // Fetch new data
        return if (isTimeToDownload()) {
            api.getWatchlists()
                    .subscribeOn(Schedulers.io())
                    .doOnSuccess { inMemoryWatchlist = it } // Save it in-memory
                    .doOnSuccess {  // Save it in db
                        for (watchlist in it) watchDao.insertWatchlist(watchlist)
                    }
                    .doAfterTerminate { lastRequestTime = System.currentTimeMillis()  }
                    .observeOn(AndroidSchedulers.mainThread())
        } else {
            inMemoryWatchlist?.run {
                Single.just(this)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            } ?: watchDao.getAllWatchlists()
                    .subscribeOn(Schedulers.io())
                    .doOnSuccess { inMemoryWatchlist = it } // save it in-memory
                    .observeOn(AndroidSchedulers.mainThread())
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
    fun getWatchlists(): Single<List<WatchlistDataModel>>?
    fun getWatchlist(id: Int): Observable<WatchlistDataModel>?
    fun postWatchlist(id: Int): Observable<Void>?
    fun deleteWatchlist(id: Int): Observable<Void>?
}