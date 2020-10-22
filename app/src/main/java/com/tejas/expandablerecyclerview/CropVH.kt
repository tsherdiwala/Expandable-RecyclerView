package com.tejas.expandablerecyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tejas.expandablerecyclerview.vo.CropItemVO

class CropVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(cropVO: CropItemVO) {
        (itemView as? TextView)?.text = cropVO.cropName
    }
}