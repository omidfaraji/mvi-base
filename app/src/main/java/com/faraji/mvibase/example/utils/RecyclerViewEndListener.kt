package com.faraji.mvibase.example.utils


import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewEndListener : RecyclerView.OnScrollListener() {

    var disable = false

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        if (disable.not() && view.canScrollVertically(1).not()) {
            onLoadMore()
        }
    }

    abstract fun onLoadMore()

}