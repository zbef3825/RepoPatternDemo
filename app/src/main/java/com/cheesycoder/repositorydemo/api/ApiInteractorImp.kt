package com.cheesycoder.repositorydemo.api

import android.content.SharedPreferences
import com.cheesycoder.repositorydemo.db.AppDatabase
import com.cheesycoder.repositorydemo.db.WatchlistDao
import com.cheesycoder.repositorydemo.model.WatchlistDataModel
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
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
) : BaseInteractor(), ApiInteractor {
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

    @Volatile
    private var inMemoryWatchlist: List<WatchlistDataModel> = listOf()
        set(value) {
            field = value
            staleInMemory = false
        }

    @Volatile
    private var staleInMemory: Boolean = false

    override fun getApiThresholdTimer(): Long = TIMER_IN_MILI

    override fun getWatchlists(): Single<List<WatchlistDataModel>> {
        return if (isTimeToDownload()) { // Fetch new data if reached threshold
            getWatchlistsDataModelFromServer()
        } else {
            if (staleInMemory) {
                getWatchlistsDataModelFromDB()
            } else {
                if (inMemoryWatchlist.isNotEmpty()) {
                    getWatchlistsDataModelFromInMemory(inMemoryWatchlist)
                } else {
                    getWatchlistsDataModelFromDB()
                }
            }
        }
    }

    override fun saveConfig() {
        sharedPreferences.edit().putLong(TAG_LAST_REQUEST, lastRequestTime)
                .apply()
    }

    override fun getWatchlist(id: Int): Observable<WatchlistDataModel>? {
        TODO("Implement This")
    }

    override fun postWatchlist(watchlistDataModel: WatchlistDataModel): Single<List<WatchlistDataModel>> {
        return Single.zip(
                api.postWatchlist(watchlistDataModel)
                        .subscribeOn(Schedulers.io())
                        .doAfterTerminate { Timber.i("Sent Data to Server") }
                        .map { inMemoryWatchlist }
                        .observeOn(AndroidSchedulers.mainThread()),
                saveAWatchlistDataModel(watchlistDataModel),
                BiFunction { t1, t2 ->
                    return@BiFunction t1
                }
        )
    }

    override fun deleteWatchlist(id: Int): Observable<Void>? {
        TODO("Implement This")
    }

    private fun saveAWatchlistDataModel(watchlistDataModel: WatchlistDataModel): Single<WatchlistDataModel> {
        return Single.fromCallable {
            watchDao.insertWatchlist(watchlistDataModel)
            staleInMemory = true
        }.subscribeOn(Schedulers.io())
                .map { watchlistDataModel }
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getWatchlistsDataModelFromServer(): Single<List<WatchlistDataModel>> {
        return api.getWatchlists()
                .subscribeOn(Schedulers.io())
                .doOnSuccess { inMemoryWatchlist = it } // Save it in-memory
                .doOnSuccess {
                    // Save it in db
                    for (watchlist in it) watchDao.insertWatchlist(watchlist)
                }
                .doAfterTerminate { lastRequestTime = System.currentTimeMillis() }
                .doAfterTerminate { Timber.i("Requested Data from Server") }
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getWatchlistsDataModelFromDB(): Single<List<WatchlistDataModel>> {
        return watchDao.getAllWatchlists()
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

    private fun getWatchlistsDataModelFromInMemory(inMemory: List<WatchlistDataModel>): Single<List<WatchlistDataModel>> {
        return Single.just(inMemory)
                .subscribeOn(Schedulers.io())
                .doAfterTerminate { Timber.i("Requested Data from In-Memory") }
                .observeOn(AndroidSchedulers.mainThread())
    }
}

interface ApiInteractor {
    fun getWatchlists(): Single<List<WatchlistDataModel>>
    fun getWatchlist(id: Int): Observable<WatchlistDataModel>?
    fun postWatchlist(watchlistDataModel: WatchlistDataModel): Single<List<WatchlistDataModel>>
    fun deleteWatchlist(id: Int): Observable<Void>?
    fun saveConfig()
}