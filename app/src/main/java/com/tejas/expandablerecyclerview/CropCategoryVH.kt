package com.tejas.expandablerecyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tejas.expandablerecyclerview.vo.CropCategoryVO

class CropCategoryVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(
        categoryVO: CropCategoryVO,
        addCrops: () -> Unit,
        removeCrops: () -> Unit
    ) {
        (itemView as? TextView)?.text = categoryVO.categoryName

        itemView.setOnClickListener {
            if (categoryVO.isExpanded) {
                // remove crops
                removeCrops()
            } else {
                // add crops
                addCrops()
            }
        }
    }

}