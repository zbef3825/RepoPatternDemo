package com.cheesycoder.repositorydemo.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.cheesycoder.repositorydemo.model.WatchlistDataModel

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
@Database(entities = [WatchlistDataModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun watchlistDao(): WatchlistDao
}