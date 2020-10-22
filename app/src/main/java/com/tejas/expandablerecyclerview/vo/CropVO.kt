package com.tejas.expandablerecyclerview.vo

abstract class CropVO

class CropCategoryVO(
    val categoryName: String,
    val crops: List<String>,
    var isExpanded: Boolean = false
) : CropVO()

class CropItemVO(val cropName: String) : CropVO()