package com.cheesycoder.repositorydemo.api

/**
 * Author: jinwo
 * Date: 2018-05-20
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
enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
class DataWrapper<T>(
        val status: Status,
        val data: T?,
        val exception: Exception?
) {
    companion object {
        fun <T> success(data: T): DataWrapper<T>
                = DataWrapper(
                status = Status.SUCCESS,
                data = data,
                exception = null
        )

        fun <T> error(exception: Exception): DataWrapper<T>
                = DataWrapper(
                status = Status.ERROR,
                data = null,
                exception = exception
        )

        fun <T> loading(): DataWrapper<T>
                = DataWrapper(
                status = Status.LOADING,
                data = null,
                exception = null
        )
    }

}