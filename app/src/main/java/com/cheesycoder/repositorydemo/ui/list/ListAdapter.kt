package com.cheesycoder.repositorydemo.ui.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheesycoder.repositorydemo.R
import com.cheesycoder.repositorydemo.vm.WatchlistViewModel

/**
 * Author: jinwo
 * Date: 2018-05-10
 * Package: com.cheesycoder.repositorydemo.ui.list
 *
 *\      _..--'''@   @'''--.._
 *\    .'   @_/-//-\/>/>'/ @  '.
 *\   (  @  /_<//<'/----------^-)
 *\   |'._  @     //|',|}}}}}}}}|
 *\   |  ~   ~   |/ | *|./|{{{{{|
 *\    '._ ~ ~ ~ |,/`````````````
 *\       ''--.~.|/
 */
fun MutableList<WatchlistViewModel>?.size(): Int {
    return this?.size ?: 0
}

class ListAdapter(val context: Context?): RecyclerView.Adapter<ListAdapter.SimpleViewHolder>() {
    var internalData: MutableList<WatchlistViewModel>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder
            = SimpleViewHolder(
            LayoutInflater.from(context).inflate(R.layout.view_simple_holder,
                    parent,
                    false)
    )

    override fun getItemCount(): Int = internalData.size()

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
    }

    inner class SimpleViewHolder(
            itemView: View
    ): RecyclerView.ViewHolder(itemView) {

    }
}