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
    protected var lastRequestTime: Long = 0
    protected fun isTimeToDownload(): Boolean = System.currentTimeMillis() - lastRequestTime >= getApiThresholdTimer()
    abstract fun getApiThresholdTimer(): Long
}