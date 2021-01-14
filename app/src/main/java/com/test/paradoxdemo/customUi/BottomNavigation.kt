package com.test.paradoxdemo.customUi

import android.content.Context
import android.util.AttributeSet
import android.view.MenuItem
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.test.paradoxdemo.R
import kotlinx.android.synthetic.main.view_bottom_navigation.view.*

class BottomNavigation @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationView(context, attrs, defStyleAttr),
    BottomNavigationView.OnNavigationItemSelectedListener {

    var onNavigationClick: ((BottomNavigationType) -> Unit)? = null

    init {
        View.inflate(context, R.layout.view_bottom_navigation, this)
        setListener()
    }

    private fun setListener() {
        navigation_view.setOnNavigationItemSelectedListener(this)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        onNavigationClick?.invoke(BottomNavigationType.valueOf(item.title.toString()))
        return true
    }

    enum class BottomNavigationType {
        PRODUCTS,
        FAVORITE
    }
}