package com.cheesycoder.repositorydemo

import android.support.v7.app.AppCompatActivity
import com.cheesycoder.repositorydemo.di.ActivityScoped
import javax.inject.Inject

/**
 * Author: jinwo
 * Date: 2018-05-05
 * Package: com.cheesycoder.repositorydemo
 *
 *\      _..--'''@   @'''--.._
 *\    .'   @_/-//-\/>/>'/ @  '.
 *\   (  @  /_<//<'/----------^-)
 *\   |'._  @     //|',|}}}}}}}}|
 *\   |  ~   ~   |/ | *|./|{{{{{|
 *\    '._ ~ ~ ~ |,/`````````````
 *\       ''--.~.|/
 */
@ActivityScoped
class FlowManager @Inject constructor(val activity: AppCompatActivity) {
}