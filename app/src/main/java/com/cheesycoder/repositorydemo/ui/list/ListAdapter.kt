package com.cheesycoder.repositorydemo.ui.list

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.cheesycoder.repositorydemo.R
import com.cheesycoder.repositorydemo.model.WatchlistDataModel
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

class ListAdapter(val context: Context?) : RecyclerView.Adapter<SimpleViewHolder>() {
    var internalData: List<WatchlistDataModel> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder = SimpleViewHolder(
            LayoutInflater.from(context).inflate(R.layout.view_simple_holder,
                    parent,
                    false)
    )

    override fun getItemCount(): Int = internalData.size

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.bindData(internalData[position])
    }
}

class SimpleViewHolder(
        itemView: View
) : RecyclerView.ViewHolder(itemView) {
    @BindView(R.id.title)
    lateinit var title: AppCompatTextView
    @BindView(R.id.body)
    lateinit var body: AppCompatTextView
    @BindView(R.id.url)
    lateinit var url: AppCompatTextView

    init {
        ButterKnife.bind(this, itemView)
    }

    fun bindData(watchlistDataModel: WatchlistDataModel) {
        watchlistDataModel.apply {
            title?.let {
                this@SimpleViewHolder.title.text = it
            }
            body?.let {
                this@SimpleViewHolder.body.text = it
            }
            photo_url?.let {
                this@SimpleViewHolder.url.text = it
            }
        }
    }
}