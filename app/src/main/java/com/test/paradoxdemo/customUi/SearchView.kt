package com.test.paradoxdemo.customUi

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.test.paradoxdemo.R
import kotlinx.android.synthetic.main.view_search.view.*

class SearchView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), TextView.OnEditorActionListener {

    private var fragment: BaseProductsFragment? = null

    init {
        View.inflate(context, R.layout.view_search, this)
        initViews()
    }

    private fun initViews() {
        search.setOnEditorActionListener(this)
        search_btn.setOnClickListener {
            onSearch(getSearchText())
        }
    }

    private fun getSearchText() = search.text.toString()

    fun setFragment(fragment: BaseProductsFragment) {
        this.fragment = fragment
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            onSearch(getSearchText())
        }
        return false
    }

    fun setTitle(title: String) {
        tv_title.text = title
    }

    private fun onSearch(str: String) {
        fragment?.onSearchProduct(str)
    }


}
