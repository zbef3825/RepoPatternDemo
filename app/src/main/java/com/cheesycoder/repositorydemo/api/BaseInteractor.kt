package com.cheesycoder.repositorydemo.api

/**
 * Author: jinwo
 * Date: 2018-05-15
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
abstract class BaseInteractor {
    protected var lastRequestDiff: Long = 0
    protected fun isTimeToDownload(): Boolean = lastRequestDiff >= getApiThresholdTimer()
    abstract fun getApiThresholdTimer(): Long
}