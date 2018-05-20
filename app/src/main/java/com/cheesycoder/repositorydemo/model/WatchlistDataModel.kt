package com.cheesycoder.repositorydemo.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Author: jinwo
 * Date: 2018-05-10
 * Package: com.cheesycoder.repositorydemo.model
 *
 *\      _..--'''@   @'''--.._
 *\    .'   @_/-//-\/>/>'/ @  '.
 *\   (  @  /_<//<'/----------^-)
 *\   |'._  @     //|',|}}}}}}}}|
 *\   |  ~   ~   |/ | *|./|{{{{{|
 *\    '._ ~ ~ ~ |,/`````````````
 *\       ''--.~.|/
 */
@Entity(tableName = "watchlist")
data class WatchlistDataModel(
        @PrimaryKey
        val id: Int,

        @ColumnInfo(name = "title")
        val title: String?,

        @ColumnInfo(name = "body")
        val body: String?,

        @ColumnInfo(name="photo_url")
        val photo_url: String?
)