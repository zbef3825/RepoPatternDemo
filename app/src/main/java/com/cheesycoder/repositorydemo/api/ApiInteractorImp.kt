package com.cheesycoder.repositorydemo.api

import android.content.SharedPreferences
import com.cheesycoder.repositorydemo.db.AppDatabase
import com.cheesycoder.repositorydemo.db.WatchlistDao
import com.cheesycoder.repositorydemo.model.WatchlistDataModel
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named
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
        val appDatabase: AppDatabase,
        @Named("vmConfig") val sharedPreferences: SharedPreferences
): BaseInteractor(), ApiInteractor {
    companion object {
        private const val TIMER_IN_MILI = 120000L
        private const val TAG_LAST_REQUEST = "Api.interactor.imp.last.request.time"
    }

    init {
        lastRequestTime = sharedPreferences.getLong(TAG_LAST_REQUEST, 0)
    }

    private val watchDao: WatchlistDao by lazy {
        appDatabase.watchlistDao()
    }

    private var inMemoryWatchlist: List<WatchlistDataModel>? = null

    override fun getApiThresholdTimer(): Long = TIMER_IN_MILI

    override fun getWatchlists(): Single<List<WatchlistDataModel>> {
        return if (isTimeToDownload()) { // Fetch new data if reached threshold
            api.getWatchlists()
                    .subscribeOn(Schedulers.io())
                    .doOnSuccess { inMemoryWatchlist = it } // Save it in-memory
                    .doOnSuccess {  // Save it in db
                        for (watchlist in it) watchDao.insertWatchlist(watchlist)
                    }
                    .doAfterTerminate { lastRequestTime = System.currentTimeMillis()  }
                    .doAfterTerminate { Timber.i("Requested Data from Server") }
                    .observeOn(AndroidSchedulers.mainThread())
        } else {
            inMemoryWatchlist?.run {
                Single.just(this)
                        .subscribeOn(Schedulers.io())
                        .doAfterTerminate { Timber.i("Requested Data from In-Memory") }
                        .observeOn(AndroidSchedulers.mainThread())
            } ?: watchDao.getAllWatchlists()
                    .subscribeOn(Schedulers.io())
                    .doOnSuccess {
                        it?.let {
                            // save it in-memory if there is something
                            if (it.isNotEmpty()) inMemoryWatchlist = it
                        }
                    }
                    .doAfterSuccess { Timber.i("Requested Data from DB") }
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    override fun saveConfig() {
        sharedPreferences.edit().putLong(TAG_LAST_REQUEST, lastRequestTime)
                .apply()
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
    fun getWatchlists(): Single<List<WatchlistDataModel>>
    fun getWatchlist(id: Int): Observable<WatchlistDataModel>?
    fun postWatchlist(id: Int): Observable<Void>?
    fun deleteWatchlist(id: Int): Observable<Void>?
    fun saveConfig()
}