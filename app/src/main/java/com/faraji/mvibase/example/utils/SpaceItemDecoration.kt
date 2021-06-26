package com.faraji.mvibase.example.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.faraji.mvibase.example.utils.extension.dp

class SpaceItemDecoration(private val space: Int, private var layoutManager: Int) :
    ItemDecoration() {
    private var endAdditionalOffset = 0

    constructor(space: Int, layoutManager: Int, endAdditionalOffset: Int) : this(
        space,
        layoutManager
    ) {
        this.endAdditionalOffset = endAdditionalOffset
    }

    fun setLayoutManager(layoutManager: Int) {
        this.layoutManager = layoutManager
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val count = getTotalItemCount(parent)
        when (layoutManager) {
            LINEAR_LAYOUT_MANAGER -> {
                if (space <= 0) {
                    if (endAdditionalOffset > 0) {
                        addAdditionalSpace(outRect, view, parent)
                    }
                    return
                }
                val gap = space / 2
                if (getOrientation(parent) == LinearLayoutManager.VERTICAL) {
                    if (position > 0) {
                        outRect.top = gap
                    }
                    if (position < count - 1) {
                        outRect.bottom = gap
                    }
                    if (endAdditionalOffset > 0) {
                        addAdditionalSpace(outRect, view, parent)
                    }
                } else {
                    if (position > 0) {
                        outRect.left = gap
                    }
                    if (position < count - 1) {
                        outRect.right = gap
                    }
                }
            }
            GRID_LAYOUT_MANAGER -> {
                val horizontalExtra = 2.dp.toInt()
                val gap = space / 2
                if (space <= 0) {
                    outRect.left = gap + horizontalExtra
                    outRect.right = gap + horizontalExtra
                    if (endAdditionalOffset > 0) {
                        addAdditionalSpace(outRect, view, parent)
                    }
                    return
                }
                //                int columnCount = getGrigColumnCount(parent);
                outRect.left = gap
                outRect.right = gap
                outRect.top = gap
                outRect.bottom = gap

//                }
                if (endAdditionalOffset > 0) {
                    addAdditionalSpace(outRect, view, parent)
                }
            }
        }
    }

    private fun addAdditionalSpace(outRect: Rect, view: View, parent: RecyclerView) {
        if (layoutManager == LINEAR_LAYOUT_MANAGER) {
            if (getOrientation(parent) == LinearLayoutManager.VERTICAL) {
                if (parent.getChildAdapterPosition(view) == getTotalItemCount(parent) - 1) {
                    outRect.bottom += outRect.bottom + endAdditionalOffset
                }
            }
        } else if (layoutManager == GRID_LAYOUT_MANAGER) {
            val spaceCount = getGrigColumnCount(parent)
            val count = getTotalItemCount(parent)
            var lastColumns = count % spaceCount
            if (lastColumns == 0) {
                lastColumns = spaceCount
            }
            val position = parent.getChildAdapterPosition(view)
            if (position <= count - 1 && position >= count - lastColumns) {
                outRect.bottom += outRect.bottom + endAdditionalOffset
            }
        }
    }

    private fun getTotalItemCount(parent: RecyclerView): Int {
        return parent.adapter!!.itemCount
    }

    private fun getOrientation(parent: RecyclerView): Int {
        return (parent.layoutManager as LinearLayoutManager?)!!.orientation
    }

    private fun getGrigColumnCount(parent: RecyclerView): Int {
        return (parent.layoutManager as GridLayoutManager?)!!.spanCount
    }

    companion object {
        const val LINEAR_LAYOUT_MANAGER = 0
        const val GRID_LAYOUT_MANAGER = 1
    }

}