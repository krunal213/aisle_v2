package com.app.aisle.discover

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.aisle.R

class DiscoverDecoration: RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val adapterPosition = parent.getChildAdapterPosition(view)
        if (adapterPosition % 2 == 0) {
            outRect.right = view.resources.getDimensionPixelSize(R.dimen.eight_dp)
        }else{
            outRect.left = view.resources.getDimensionPixelSize(R.dimen.eight_dp)
        }
    }
}