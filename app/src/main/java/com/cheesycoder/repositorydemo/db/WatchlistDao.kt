package com.cheesycoder.repositorydemo.db

import android.arch.persistence.room.*
import com.cheesycoder.repositorydemo.model.WatchlistDataModel
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * Author: jinwo
 * Date: 2018-05-20
 * Package: com.cheesycoder.repositorydemo.db
 *
 *\      _..--'''@   @'''--.._
 *\    .'   @_/-//-\/>/>'/ @  '.
 *\   (  @  /_<//<'/----------^-)
 *\   |'._  @     //|',|}}}}}}}}|
 *\   |  ~   ~   |/ | *|./|{{{{{|
 *\    '._ ~ ~ ~ |,/`````````````
 *\       ''--.~.|/
 */
@Dao
interface WatchlistDao {
    @Query("SELECT * FROM watchlist")
    fun getAllWatchlists(): Single<List<WatchlistDataModel>>

    @Query("SELECT * FROM watchlist WHERE id = :uid")
    fun getWatchlist(uid: Int): Single<WatchlistDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWatchlist(watchlistDataModel: WatchlistDataModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWatchlists(vararg watchlistDataModel: WatchlistDataModel)

    @Delete
    fun deleteWatchlists(vararg watchlistDataModel: WatchlistDataModel)

}