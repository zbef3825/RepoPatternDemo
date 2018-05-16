package com.cheesycoder.repositorydemo

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import butterknife.ButterKnife
import com.cheesycoder.repositorydemo.api.ApiInteractor
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    companion object {
        val POSITION_KEY = "position_key"
    }

    @Inject
    lateinit var flowManager: FlowManager

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                flowManager.displayAListFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                flowManager.displayBListFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                flowManager.displayReportFragment()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        savedInstanceState?.apply {
            val selectedItemId = getInt(POSITION_KEY, -1)
            if (selectedItemId != -1) {
                navigation.selectedItemId = selectedItemId
            } else {
                navigation.selectedItemId = R.id.navigation_home
            }
        }
        if (savedInstanceState == null) {
            navigation.selectedItemId = R.id.navigation_home
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.apply {
            putInt(POSITION_KEY, navigation.selectedItemId)
        }
        super.onSaveInstanceState(outState)
    }
}
