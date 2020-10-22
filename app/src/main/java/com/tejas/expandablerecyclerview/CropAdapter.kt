package com.tejas.expandablerecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tejas.expandablerecyclerview.vo.CropCategoryVO
import com.tejas.expandablerecyclerview.vo.CropItemVO
import com.tejas.expandablerecyclerview.vo.CropVO

class CropAdapter(crops: List<CropModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var adapterList: List<CropVO>? = null

    init {
        val categories = crops.map {
            CropCategoryVO(
                it.category,
                it.crops
            )
        }

        val initialList = mutableListOf<CropVO>()
        initialList.addAll(categories)

        adapterList = initialList
    }


    companion object {
        private const val VIEW_TYPE_CATEGORY = 1
        private const val VIEW_TYPE_CROP = 2
    }

    override fun getItemViewType(position: Int) =
        when (val item = adapterList?.getOrNull(position)) {
            is CropCategoryVO -> VIEW_TYPE_CATEGORY
            is CropItemVO -> VIEW_TYPE_CROP
            else -> throw IllegalArgumentException("No view found for : " + item?.javaClass?.name)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            VIEW_TYPE_CATEGORY -> {
                CropCategoryVH(
                    layoutInflater.inflate(R.layout.item_crop_category, parent, false)
                )
            }
            VIEW_TYPE_CROP -> {
                CropVH(
                    layoutInflater.inflate(R.layout.item_crop, parent, false)
                )
            }
            else -> throw IllegalArgumentException("Unknown view type : $viewType")
        }

    }

    override fun getItemCount() = adapterList?.size ?: 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CropCategoryVH -> (adapterList?.getOrNull(position) as? CropCategoryVO)?.let {
                holder.bind(
                    it,
                    addCrops = {


                        val list = adapterList?.toMutableList() ?: mutableListOf()

                        val categoryIndex = list.indexOf(it)

                        if (categoryIndex >= 0) {
                            list.addAll(categoryIndex + 1, it.crops.map { CropItemVO(it) })

                            adapterList = list
                            notifyItemRangeInserted(categoryIndex + 1, it.crops.size)
                        }
                        it.isExpanded = true
                    },
                    removeCrops = {
                        val list = adapterList?.toMutableList() ?: mutableListOf()

                        val categoryCrops = it.crops

                        list.removeAll {
                            it is CropItemVO && categoryCrops.contains(it.cropName)
                        }

                        adapterList = list


                        val categoryIndex = list.indexOf(it)

                        notifyItemRangeRemoved(categoryIndex+1, it.crops.size)
                        it.isExpanded = false
                    }
                )
            }
            is CropVH -> (adapterList?.getOrNull(position) as? CropItemVO)?.let {
                holder.bind(
                    it
                )
            }
        }
    }
}