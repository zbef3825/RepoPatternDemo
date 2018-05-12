package com.cheesycoder.repositorydemo.ui.list

import android.os.Bundle
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.cheesycoder.repositorydemo.R
import dagger.android.support.DaggerFragment

/**
 * Author: jinwo
 * Date: 2018-05-06
 * Package: com.cheesycoder.repositorydemo.ui
 *
 *\      _..--'''@   @'''--.._
 *\    .'   @_/-//-\/>/>'/ @  '.
 *\   (  @  /_<//<'/----------^-)
 *\   |'._  @     //|',|}}}}}}}}|
 *\   |  ~   ~   |/ | *|./|{{{{{|
 *\    '._ ~ ~ ~ |,/`````````````
 *\       ''--.~.|/
 */
abstract class ListFragment : DaggerFragment() {

    @BindView(R.id.action_button)
    protected lateinit var button: AppCompatButton

    @BindView(R.id.list_view)
    protected lateinit var listView: RecyclerView

    private var unBinder: Unbinder? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)?.apply {
            unBinder = ButterKnife.bind(this@ListFragment, this)
            listView.layoutManager = LinearLayoutManager(this@ListFragment.context)
            listView.adapter = getAdapterFromChildFragment()
            button.text = getButtonText()
        }
    }

    override fun onDestroyView() {
        unBinder?.unbind()
        super.onDestroyView()
    }

    @OnClick(R.id.action_button)
    fun buttonPressed() {
        actionButtonPressed()
    }

    abstract fun actionButtonPressed()

    abstract fun getAdapterFromChildFragment(): ListAdapter

    abstract fun getButtonText(): String
}